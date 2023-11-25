//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.vegetalMaintenance;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormVegetalMaintenancePart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.DatePickerFragment;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormVegetalMaintenanceFragmentPart1 extends FormVegetalMaintenanceBaseFragment {

    public static FormVegetalMaintenanceFragmentPart1 getInstance(Bundle data) {
        FormVegetalMaintenanceFragmentPart1 fragment = new FormVegetalMaintenanceFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_vegetal_maintenance_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormVegetalMaintenancePart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormVegetalMaintenancePart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void showExecutionStartDatePicker(AppDate date) {
        Bundle bundle = new Bundle();
        if (date != null) {
            bundle.putString(DatePickerFragment.YEAR, date.getYear());
            bundle.putString(DatePickerFragment.MONTH, date.getMonth());
            bundle.putString(DatePickerFragment.DAY, date.getDay());
        }
        DatePickerFragment fragment = DatePickerFragment.getInstance(bundle);
        fragment.setListener((datePicker, newYear, newMonth, newDay) -> viewModel.setExecutionStartDate(newYear, (newMonth + 1), newDay));
        fragment.show(activity.getSupportFragmentManager(), DATE_PICKER);
    }

    @Override
    public void showExecutionEndDatePicker(AppDate date) {
        Bundle bundle = new Bundle();
        if (date != null) {
            bundle.putString(DatePickerFragment.YEAR, date.getYear());
            bundle.putString(DatePickerFragment.MONTH, date.getMonth());
            bundle.putString(DatePickerFragment.DAY, date.getDay());
        }
        DatePickerFragment fragment = DatePickerFragment.getInstance(bundle);
        fragment.setListener((datePicker, newYear, newMonth, newDay) -> viewModel.setExecutionEndDate(newYear, (newMonth + 1), newDay));
        fragment.show(activity.getSupportFragmentManager(), DATE_PICKER);
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormVegetalMaintenancePart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2;
    }

    @Override
    protected void setMunicipalities(String[] municipalitiesArray) {
        ((FragmentFormVegetalMaintenancePart1Binding) binding).municipality.setData(municipalitiesArray);
    }
}
