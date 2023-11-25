//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.meetingsWithActorsForm;

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
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.DatePickerFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewMvvm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public abstract class FormMeetingsWithActorsFormBaseFragment extends BaseFragment implements FormMeetingsWithActorsFormViewMvvm.View {

    protected static final String STARD_SHEET_FORM = "stardSheetForm";
    private static final String SEND_STATUS = "sendStatus";
    protected static final String DATE_PICKER = "datePicker";
    protected FormFragmentListener activity;
    protected Bundle savedInstanceState;
    private Bundle argumentsBundle;
    protected FormMeetingsWithActorsFormViewMvvm.ViewModel viewModel;
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
        this.argumentsBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (FormMeetingsWithActorsFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_MEETING_WITH_ACTORS_FORM_VIEW_VIEW_MODEL);
        if (argumentsBundle != null) {
            Task task = argumentsBundle.getParcelable(RxBus.PAYLOAD);
            viewModel.setTask(task);
            setViewModel();
            viewModel.onReadyForSubscriptions();
        }
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
        outState.putParcelable(STARD_SHEET_FORM, viewModel.getMeetingsWithActorsForm());
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

    public void onBackPressed() {
        if (getActivity() != null) {
            Intent resultIntent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();
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
    public MeetingsWithActorsForm getMeetingsWithActorsForm() {
        return viewModel.getMeetingsWithActorsForm();
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
    public boolean canGoToNextScreen() {
        return true;
    }

    @Override
    public void showDatePicker(AppDate date, final int viewId) {
        Bundle bundle = new Bundle();
        if (date != null) {
            bundle.putString(DatePickerFragment.YEAR, date.getYear());
            bundle.putString(DatePickerFragment.MONTH, date.getMonth());
            bundle.putString(DatePickerFragment.DAY, date.getDay());
        }
        DatePickerFragment fragment = DatePickerFragment.getInstance(bundle);
        fragment.setListener((datePicker, newYear, newMonth, newDay) -> viewModel.setDate(newYear, (newMonth + 1), newDay, viewId));
        fragment.show(activity.getSupportFragmentManager(), DATE_PICKER);
    }

    @Override
    public Task getTask() {
        return viewModel.getTask();
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
