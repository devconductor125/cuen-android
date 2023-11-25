//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentTracksBinding;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TracksViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class TracksFragment extends BaseFragment implements TracksViewMvvm.View {

    private FragmentTracksBinding binding;
    private TracksViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static TracksFragment getInstance(Bundle data) {
        TracksFragment fragment = new TracksFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracks, container, false);
            viewModel = (TracksViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.TRACKS_VIEW_MODEL);
            viewModel.getPredios();
        }
        viewModel.onReadyForSubscriptions();
        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((BaseViewModel) viewModel).clearSubscriptions();
    }

    private void initListener(Context context) {
        try {
            activity = (FragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public boolean isFragmentAnimate() {
        return true;
    }

    @Override
    public void onResetState() {

    }
}
