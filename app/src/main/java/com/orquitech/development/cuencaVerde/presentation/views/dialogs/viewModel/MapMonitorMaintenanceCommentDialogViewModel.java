package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.graphics.Bitmap;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapMonitorMaintenanceCommentDialogViewModel extends BaseViewModel implements MapMonitorMaintenanceCommentDialogMvvm.ViewModel {

    private final MapMonitorMaintenanceCommentDialogMvvm.View view;
    private MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint;
    private ListAdapter adapter;

    public MapMonitorMaintenanceCommentDialogViewModel(AppView view) {
        super(view);
        this.view = (MapMonitorMaintenanceCommentDialogMvvm.View) view;
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(proceduresManager.getMonitorAndMaintenancePhotosSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::initAdapterAndSendToView)
                .subscribe());

        disposables.add(proceduresManager.getMonitorAndMaintenanceCommentPointSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(monitorAndMaintenanceCommentPoint -> this.monitorAndMaintenanceCommentPoint = monitorAndMaintenanceCommentPoint)
                .subscribe());

        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.ON_IMAGES_RESULT:
                            Bitmap bitmap = bundle.getParcelable(RxBus.PAYLOAD);
                            if (bitmap != null) {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                                String imageFileName = "JPEG_" + timeStamp + "_" + monitorAndMaintenanceCommentPoint.getId() + ".jpg";

                                MonitorAndMaintenancePhoto monitorAndMaintenancePhoto = new MonitorAndMaintenancePhoto();
                                monitorAndMaintenancePhoto.setPhotoName(imageFileName);
                                monitorAndMaintenancePhoto.setMonitorAndMaintenanceCommentPointId(monitorAndMaintenanceCommentPoint.getId());
                                monitorAndMaintenancePhoto.setBitmap(bitmap);
                                monitorAndMaintenancePhoto.setType(AppViewsFactory.MONITOR_MAINTENANCE_PHOTO_ITEM_VIEW);

                                adapter.addItemToIndex(1, monitorAndMaintenancePhoto);

                                proceduresManager.saveMonitorAndMaintenancePhoto(monitorAndMaintenancePhoto);
                                FilesUtils.saveImageFileFromBitmap(app.getApplicationContext(), bitmap, imageFileName);
                            }
                            break;
                    }
                })
                .subscribe());
    }

    private void initAdapterAndSendToView(List<Item> items) {
        if (adapter == null) {
            adapter = listAdapterFactory.getListAdapter((AppListView) view);
            adapter.setItems(items);
            view.onListAdapterReady(adapter);
        }
    }

    @Override
    public String getComment() {
        return monitorAndMaintenanceCommentPoint.getComment();
    }

    @Override
    public void setComment(String comment) {
        monitorAndMaintenanceCommentPoint.setComment(comment);
    }

    public void setMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        this.monitorAndMaintenanceCommentPoint = monitorAndMaintenanceCommentPoint;
        proceduresManager.getMonitorAndMaintenancePhotos(monitorAndMaintenanceCommentPoint.getId());
        proceduresManager.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint);
    }

    @Override
    public void saveMonitorAndMaintenanceComment(View view) {
        monitorAndMaintenanceCommentPoint.setPendingForSave();
        this.view.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint);
    }

    @Override
    public void deleteMonitorAndMaintenanceCommentPoint() {
        if (monitorAndMaintenanceCommentPoint.isNotGoingToBeSaved() && monitorAndMaintenanceCommentPoint.isNew()) {
            proceduresManager.deleteMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint);
        }
    }

    @Override
    public void onPhotographyRegistry(View view) {
        this.view.onPhotographyRegistry(monitorAndMaintenanceCommentPoint);
    }
}
