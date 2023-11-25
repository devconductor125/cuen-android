//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.ViewDataBinding;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.SurveyFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyViewMvvm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public abstract class FormSurveyBaseFragment extends BaseFragment implements FormSurveyViewMvvm.View {

    protected static final String SURVEY = "survey";
    private static final String SEND_STATUS = "sendStatus";
    protected static final String DATE_PICKER = "datePicker";
    protected SurveyFragmentListener activity;
    protected Bundle savedInstanceState;
    protected PredioPotencial predioPotencial;
    public Bundle argumentsBundle;
    protected FormSurveyViewMvvm.ViewModel viewModel;
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
            predioPotencial = argumentsBundle.getParcelable(RxBus.PAYLOAD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (FormSurveyViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_SURVEY_VIEW_MODEL);
        viewModel.setPredioPotencial(predioPotencial);
        setViewModel();
        viewModel.onReadyForSubscriptions();
        return binding.getRoot();
    }

    private void initListener(Context context) {
        try {
            activity = (SurveyFragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SURVEY, viewModel.getSurvey());
        outState.putBoolean(SEND_STATUS, isSending);
    }

    public void setPropertySectorDropdown(String sector) {
        if (viewModel.getSurvey() != null) {
            viewModel.getSurvey().setPropertySector(sector);
            onViewModelUpdated();
        } else {
            Log.d("this is survey", "survey is null");
        }
    }

    public void setPropertyReservDropdown(String reservoir) {
        if (viewModel.getSurvey() != null) {
            viewModel.getSurvey().setPropertyReservoir(reservoir);
            onViewModelUpdated();
        } else {
            Log.d("this is survey", "survey is null");
        }
    }

    public void setMunicipioDropdown(String municipio) {
        if (viewModel.getSurvey() != null) {
            viewModel.getSurvey().setMunicipality(municipio);
            onViewModelUpdated();
        } else {
            Log.d("this is survey", "survey is null");
        }
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

    @Override
    public void showCorrelationDialog() {

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
    public void initLocationRequest() {
        activity.initLocationRequest();
    }

    @Override
    public int getNextViewId() {
        return 0;
    }

    @Override
    public Survey getSurvey() {
        return viewModel.getSurvey();
    }

    @Override
    public Task getTask() {
        return null;
    }

    @Override
    public void setSurveyLocation(Location location) {
        if (viewModel.getSurvey() != null) {
            if (location != null) {
                viewModel.getSurvey().setLocation(location);
            }
            onViewModelUpdated();
        }
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

    @Override
    public abstract boolean canGoToNextScreen();

    @Override
    public String getPredioPotencialId(long remoteId) {
        int resourceId = R.string.predio_unique_id;
        String predioId = String.valueOf(remoteId);
        if (remoteId == 0) {
            predioId = getString(R.string.no_unique_id);
        }
        return getString(resourceId, predioId);
    }
}
