package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhotographyRegistryViewModel extends BaseSingleListViewModel implements PhotographyRegistryViewMvvm.ViewModel {

    private String itemId;
    private String itemName;
    private com.orquitech.development.cuencaVerde.data.model.BaseItem item;
    private Set<PhotographicRegistryPhoto> photographicRegistriesToDelete;
    private boolean hasItemsToDelete;
    private PhotographicRegistryPhoto pendingNewPhoto;

    @Inject
    App app;

    @Inject
    ProceduresManager proceduresManager;

    @Inject
    ApiManager apiManager;

    public PhotographyRegistryViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
        photographicRegistriesToDelete = new HashSet<>();
    }

    @Override
    public boolean isHasItemsToDelete() {
        return hasItemsToDelete;
    }

    @Override
    public void setHasItemsToDelete(boolean hasItemsToDelete) {
        this.hasItemsToDelete = hasItemsToDelete;
    }

    @Override
    public void setItem(com.orquitech.development.cuencaVerde.data.model.BaseItem item) {
        this.item = item;
        this.itemId = item.getId();
        this.itemName = getTaskTypeTitle();
    }

    private String getTaskTypeTitle() {
        String result = "Registro fotográfico";
        if (item instanceof Task) {
            result = ((Task) this.item).getTaskTypeTitle();
        } else if (item instanceof SamplingPointForm || item instanceof ErosiveProcessForm) {
            result = "Registro fotográfico de formulario";
        }
        return result;
    }

    @Override
    public String getProjectName() {
        if (TextUtils.isEmpty(itemName)) {
            itemName = TextUtil.getString(view.getContext(), R.string.tasks);
        }
        return itemName;
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
        getPhotographicRegistries();
    }

    private void subscribeToObservables() {
        subscribeToItemsListObservable(proceduresManager.getTaskPhotographicRegistriesSubject(), AppViewsFactory.PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW);
    }

    @Override
    public void getPhotographicRegistries(boolean refresh) {
        if (refresh) {
            pendingNewPhoto = null;
        }
        hasItemsToDelete = false;
        ((PhotographyRegistryViewMvvm.View) view).onViewModelUpdated();
        getPhotographicRegistries();
    }

    @Override
    public void getPhotographicRegistries() {
        photographicRegistriesToDelete = new HashSet<>();
        proceduresManager.getPhotographicRegistries(item.getId());
    }

    @Override
    public void onBackClick(View view) {
        ((PhotographyRegistryViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasProject() {
        return item != null;
    }

    @Override
    public void onNewPhoto(View view) {
        pendingNewPhoto = null;
        ((PhotographyRegistryViewMvvm.View) this.view).onNewPhoto();
    }

    @Override
    public void addPhotographicRegistryToDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto) {
        photographicRegistriesToDelete.add(photographicRegistryPhoto);
        notifyDeletionStateChange();

        hasItemsToDelete = true;
        ((PhotographyRegistryViewMvvm.View) view).onViewModelUpdated();

        Bundle bundle = new Bundle();
        bundle.putInt(RxBus.CODE, RxBus.IN_PHOTO_FOR_DELETION_STATE);
        bundle.putBoolean(RxBus.PAYLOAD, true);
        bus.publish(bundle);
    }

    @Override
    public void removePhotographicRegistryFromDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto) {
        photographicRegistriesToDelete.remove(photographicRegistryPhoto);

        if (photographicRegistriesToDelete.size() == 0) {
            hasItemsToDelete = false;
            ((PhotographyRegistryViewMvvm.View) view).onViewModelUpdated();
        }

        notifyDeletionStateChange();
    }

    private void notifyDeletionStateChange() {
        Bundle bundle = new Bundle();
        bundle.putInt(RxBus.CODE, RxBus.IN_PHOTO_FOR_DELETION_STATE);
        if (photographicRegistriesToDelete.size() == 0) {
            bundle.putBoolean(RxBus.PAYLOAD, false);
        } else {
            bundle.putBoolean(RxBus.PAYLOAD, true);
        }
        bus.publish(bundle);
    }

    @Override
    public void deletePhotos(View view) {
        List<PhotographicRegistryPhoto> photos = new ArrayList<>(photographicRegistriesToDelete);
        proceduresManager.deletePhotographicRegistry(photos);

        for (PhotographicRegistryPhoto photo : photos) {
            FilesUtils.deleteFile(this.view.getContext(), photo.getPhotoName());
            adapter.removeItem(photo);
        }

        photographicRegistriesToDelete = new HashSet<>();
        hasItemsToDelete = false;
        ((PhotographyRegistryViewMvvm.View) this.view).onViewModelUpdated();

        notifyDeletionStateChange();
    }

    @Override
    public void addNewPhoto(Bitmap bitmap) {
        if (bitmap != null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_" + item.getId() + ".jpg";

            Observable.just(bitmap)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .flatMap(emittedBitmap -> {
                        Uri uri = FilesUtils.saveImageFileFromBitmap(app.getApplicationContext(), bitmap, imageFileName);
                        return Observable.just(uri);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(uri -> {
                        PhotographicRegistryPhoto photographicRegistryPhoto = new PhotographicRegistryPhoto();
                        photographicRegistryPhoto.setPhotoName(imageFileName);
                        photographicRegistryPhoto.setTaskId(item.getId());
                        photographicRegistryPhoto.setUri(uri);
                        photographicRegistryPhoto.setType(AppViewsFactory.PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW);

                        pendingNewPhoto = photographicRegistryPhoto;
                        addPendingNewPhoto();

                        proceduresManager.savePhotographicRegistry(photographicRegistryPhoto, null);
                    })
                    .doOnComplete(bitmap::recycle)
                    .subscribe();
        }
    }

    @Override
    protected List<Item> getPendingItemsToAdd() {
        List<Item> items = new ArrayList<>();
        if (pendingNewPhoto != null) {
            items.add(pendingNewPhoto);
        }
        return items;
    }

    @Override
    public void addPendingNewPhoto() {
        if (pendingNewPhoto != null) {
            List<Item> items = adapter.getItems();
            if (items.size() > 0) {
                items.add(0, pendingNewPhoto);
            } else {
                items.add(pendingNewPhoto);
            }
            adapter.setItems(items);
        }
    }
}
