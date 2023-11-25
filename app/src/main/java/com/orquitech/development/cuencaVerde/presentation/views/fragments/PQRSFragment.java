//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.databinding.FragmentPqrsBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PQRSViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class PQRSFragment extends BaseFragment implements PQRSViewMvvm.View {

    private FragmentPqrsBinding binding;
    private PQRSViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static PQRSFragment getInstance(Bundle data) {
        PQRSFragment fragment = new PQRSFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pqrs, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        viewModel = (PQRSViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.PQRS_VIEW_MODEL);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.savePQRS();
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
    public void showDependencyDialog() {
        ItemsListFragment fragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.DEPENDENCY_PICKER, new Bundle());
        fragment.setItemsType(DialogsFactory.DEPENDENCY_PICKER);
        fragment.show(activity.getSupportFragmentManager(), LIST_OF_DEPENDENCIES);
    }

    @Override
    public void showPQRSTypeDialog(Dependency dependency) {
        viewModel.setDependency(dependency);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, dependency);
        ItemsListFragment fragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.PQRS_TYPE_PICKER, bundle);
        fragment.setItemsType(DialogsFactory.PQRS_TYPE_PICKER);
        fragment.show(activity.getSupportFragmentManager(), LIST_OF_PQRS_TYPE);
    }

    @Override
    public void setPqrsType(PQRSType pqrsType) {
        viewModel.setPqrsType(pqrsType);
    }

    @Override
    public void setSubscribeAgreementVisibility(boolean shouldShow) {
        binding.subscribeAgreement.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPredioPotencialVisibility(boolean isChecked) {
        // binding.predioPotencialFields.setVisibility(isChecked ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }

    @Override
    public void showErrorMessage(int messageResource) {
        activity.showMessage(getString(messageResource), R.color.colorAccent);
    }

    @Override
    public void onSendPQRSTriggered() {
        activity.showMessage(getString(R.string.success), R.color.colorSecondary);
    }

    @Override
    public void setLocation(Location location) {
        viewModel.setLocation(location);
    }
}
