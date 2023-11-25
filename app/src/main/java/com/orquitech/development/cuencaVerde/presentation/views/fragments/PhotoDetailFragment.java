//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import androidx.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.databinding.FragmentPhotoDetailBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotoDetailViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class PhotoDetailFragment extends BaseFragment implements PhotoDetailViewMvvm.View {

    private FragmentPhotoDetailBinding binding;
    private PhotographicRegistryPhoto photographicRegistryPhoto;
    private PhotoDetailViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static PhotoDetailFragment getInstance(Bundle data) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            photographicRegistryPhoto = bundle.getParcelable(RxBus.PAYLOAD);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_detail, container, false);
        viewModel = (PhotoDetailViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.PHOTO_DETAIL_VIEW_MODEL);
        viewModel.setPhotographicRegistry(photographicRegistryPhoto);
        binding.setViewModel(viewModel);
        viewModel.onReadyForSubscriptions();
        setUpToolBar(binding.mainToolbar);
        binding.mainToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.clearSubscriptions();
    }

    @Override
    public boolean canGoBack() {
        return true;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onCommentSaved() {
        activity.onBackPressed();
    }

    @Override
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
    }
}
