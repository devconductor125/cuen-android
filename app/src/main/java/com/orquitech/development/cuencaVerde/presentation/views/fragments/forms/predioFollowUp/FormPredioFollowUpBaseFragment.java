//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.predioFollowUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp.FormPredioFollowUpViewMvvm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public abstract class FormPredioFollowUpBaseFragment extends BaseFragment implements FormPredioFollowUpViewMvvm.View {

    protected static final String MONITOR_AND_MAINTENANCE = "stardMonitorAndMaintenance";
    private static final String SEND_STATUS = "sendStatus";
    protected static final String DATE_PICKER = "datePicker";
    protected FormFragmentListener activity;
    protected Bundle savedInstanceState;
    protected MonitorAndMaintenance monitorAndMaintenance;
    public Bundle argumentsBundle;
    protected FormPredioFollowUpViewMvvm.ViewModel viewModel;
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
        viewModel = (FormPredioFollowUpViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_PREDIO_FOLLOW_UP_VIEW_MODEL);
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
    public void onDetach() {
        super.onDetach();
        viewModel.clearSubscriptions();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MONITOR_AND_MAINTENANCE, viewModel.getPredioFollowUpForm());
        outState.putBoolean(SEND_STATUS, isSending);
    }

    protected abstract void setViewModel();

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
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
    public PredioFollowUp getPredioFollowUp() {
        return viewModel.getPredioFollowUpForm();
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
