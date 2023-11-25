//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.vegetalMaintenance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.base.FormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewMvvm;

import java.util.ArrayList;
import java.util.List;

public abstract class FormVegetalMaintenanceBaseFragment extends FormBaseFragment implements FormVegetalMaintenanceViewMvvm.View {

    protected static final String DATE_PICKER = "datePicker";
    protected FormVegetalMaintenanceViewMvvm.ViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (FormVegetalMaintenanceViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_VEGETAL_MAINTENANCE_VIEW_MODEL);

        Task task = argumentsBundle.getParcelable(RxBus.TASK);
        viewModel.setTask(task);

        setViewModel();
        viewModel.onReadyForSubscriptions();
        return binding.getRoot();
    }

    @Override
    protected Parcelable getParcelable() {
        return viewModel.getVegetalMaintenance();
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
    public void onDetach() {
        super.onDetach();
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
    public void showExecutionStartDatePicker(AppDate date) {

    }

    @Override
    public void showExecutionEndDatePicker(AppDate date) {

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
    public void onSendParcelableTriggered() {
        if (getActivity() != null) {
            Intent resultIntent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();
        }
    }

    @Override
    public VegetalMaintenance getVegetalMaintenance() {
        return viewModel.getVegetalMaintenance();
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
