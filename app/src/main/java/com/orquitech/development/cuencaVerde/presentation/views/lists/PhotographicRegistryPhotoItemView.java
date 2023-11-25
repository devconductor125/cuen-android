package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.OvershootInterpolator;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.databinding.PhotographicRegistryPhotoItemBinding;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographicRegistryPhotoItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographyRegistryViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PhotographicRegistryPhotoItemView extends BaseListView implements ItemView, PhotographicRegistryPhotoItemViewMvvm.View {

    private PhotographicRegistryPhotoItemBinding binding;
    private PhotographicRegistryPhotoItemViewMvvm.ViewModel viewModel;
    private boolean isInSelectionForDeletionState;

    @Inject
    ViewModelsFactory viewModelFactory;

    @Inject
    Bus bus;

    public PhotographicRegistryPhotoItemView(Context context) {
        super(context);
        init(context);
    }

    public PhotographicRegistryPhotoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PhotographicRegistryPhotoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.photographic_registry_photo_item, this, true);
        disposables = new CompositeDisposable();
    }

    @Override
    public void bind(Item item, int position) {
        viewModel = (PhotographicRegistryPhotoItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW_MODEL, item);
        binding.setViewModel(viewModel);
        scaleUpPhoto();
        isInSelectionForDeletionState = false;
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.IN_PHOTO_FOR_DELETION_STATE:
                            isInSelectionForDeletionState = bundle.getBoolean(RxBus.PAYLOAD);
                            break;
                    }
                })
                .subscribe());
    }

    @Override
    protected void onDetachedFromWindow() {
        disposables.clear();
        super.onDetachedFromWindow();
    }

    @Override
    public void onClick() {
        ((ItemList) parentView).onItemClicked(binding.photo, viewModel.getPhotographicRegistryPhoto());
        if (isInSelectionForDeletionState) {
            addPhotographicRegistryToDeleteList(viewModel.getPhotographicRegistryPhoto());
        }
    }

    @Override
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
    }

    @Override
    public void addPhotographicRegistryToDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto) {
        if (parentView instanceof PhotographyRegistryViewMvvm.View) {
            ((PhotographyRegistryViewMvvm.View) parentView).getViewModel().addPhotographicRegistryToDeleteList(photographicRegistryPhoto);
        }
        viewModel.setDeleteActive(true);
        scaleDownPhoto();
    }

    @Override
    public void removePhotographicRegistryFromDeleteList(PhotographicRegistryPhoto photographicRegistryPhoto) {
        if (parentView instanceof PhotographyRegistryViewMvvm.View) {
            ((PhotographyRegistryViewMvvm.View) parentView).getViewModel().removePhotographicRegistryFromDeleteList(photographicRegistryPhoto);
        }
        viewModel.setDeleteActive(false);
        scaleUpPhoto();
    }

    private void scaleDownPhoto() {
        binding.photoContainer.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(400)
                .setInterpolator(new OvershootInterpolator(2f))
                .start();
    }

    private void scaleUpPhoto() {
        binding.photoContainer.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(400)
                .setInterpolator(new OvershootInterpolator(2f))
                .start();
    }
}
