//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardMonitorAndMaintenance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.ViewDataBinding;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewMvvm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public abstract class FormStardMonitorAndMaintenanceBaseFragment extends BaseFragment implements FormStardMonitorAndMaintenanceViewMvvm.View {

    protected static final String MONITOR_AND_MAINTENANCE = "stardMonitorAndMaintenance";
    private static final String SEND_STATUS = "sendStatus";
    protected static final String DATE_PICKER = "datePicker";
    protected FormFragmentListener activity;
    protected Bundle savedInstanceState;
    protected MonitorAndMaintenance monitorAndMaintenance;
    public Bundle argumentsBundle;
    protected FormStardMonitorAndMaintenanceViewMvvm.ViewModel viewModel;
    protected ViewDataBinding binding;
    private boolean isSending;

    @Inject
    App app;

    @Inject
    ViewModelsFactory viewModelFactory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        this.savedInstanceState = savedInstanceState;
        Bundle argumentsBundle = getArguments();
        if (argumentsBundle != null) {
            this.argumentsBundle = argumentsBundle;
            monitorAndMaintenance = argumentsBundle.getParcelable(RxBus.PAYLOAD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (FormStardMonitorAndMaintenanceViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_VIEW_MODEL);
        viewModel.setTask(monitorAndMaintenance);
        setViewModel();
        viewModel.onReadyForSubscriptions();
        return binding.getRoot();
    }

    private void initListener(Context context) {
        try {
            activity = (FormFragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MONITOR_AND_MAINTENANCE, viewModel.getStardMonitorAndMaintenance());
        outState.putBoolean(SEND_STATUS, isSending);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogManager.dismissDialog();
        viewModel.clearSubscriptions();
    }

    protected abstract void setViewModel();

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
    }

    @Override
    public void showDatePicker(AppDate date) {

    }

    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void changeToView(int viewId) {

    }

    @Override
    public int getNextViewId() {
        return 0;
    }

    @Override
    public StardMonitorAndMaintenance getStardMonitorAndMaintenance() {
        return viewModel.getStardMonitorAndMaintenance();
    }

    @Override
    public void setStardMonitorAndMaintenanceLocation(Location location) {
        if (viewModel.getStardMonitorAndMaintenance() != null && viewModel.getStardMonitorAndMaintenance().isNew()) {
            if (location != null) {
                viewModel.getStardMonitorAndMaintenance().setLocation(location);
            }
            onViewModelUpdated();
        }
    }

    @Override
    public Task getTask() {
        return null;
    }

    @Override
    public boolean canGoToNextScreen() {
        return false;
    }

    @Override
    public void onSendParcelableTriggered() {
        if (getActivity() != null) {
            Intent resultIntent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();
        }
    }

    @Override
    public void showErrorMessage(int messageResource) {
        activity.showMessage(getString(messageResource), R.color.colorAccent);
    }

    @Override
    public void onMunicipalities(List<Municipality> municipalities) {
        String[] municipalitiesArray = new String[]{};
        ArrayList<String> municipalitiesArrayList = new ArrayList<>();

        for (Municipality municipality : municipalities) {
            municipalitiesArrayList.add(municipality.getName());
        }
        municipalitiesArray = municipalitiesArrayList.toArray(municipalitiesArray);
        setMunicipalities(municipalitiesArray);
    }

    protected void setMunicipalities(String[] municipalitiesArray) {
    }
}
