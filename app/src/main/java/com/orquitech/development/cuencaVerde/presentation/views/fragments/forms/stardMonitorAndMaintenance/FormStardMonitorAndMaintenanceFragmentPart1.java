//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardMonitorAndMaintenance;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormStardMonitorAndMaintenancePart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardMonitorAndMaintenanceActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.DatePickerFragment;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormStardMonitorAndMaintenanceFragmentPart1 extends FormStardMonitorAndMaintenanceBaseFragment {

    public static FormStardMonitorAndMaintenanceFragmentPart1 getInstance(Bundle data) {
        FormStardMonitorAndMaintenanceFragmentPart1 fragment = new FormStardMonitorAndMaintenanceFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_stard_monitor_and_maintenance_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormStardMonitorAndMaintenancePart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormStardMonitorAndMaintenancePart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void showDatePicker(AppDate date) {
        Bundle bundle = new Bundle();
        if (date != null) {
            bundle.putString(DatePickerFragment.YEAR, date.getYear());
            bundle.putString(DatePickerFragment.MONTH, date.getMonth());
            bundle.putString(DatePickerFragment.DAY, date.getDay());
        }
        DatePickerFragment fragment = DatePickerFragment.getInstance(bundle);
        fragment.setListener((datePicker, newYear, newMonth, newDay) -> viewModel.setDate(newYear, (newMonth + 1), newDay));
        fragment.show(activity.getSupportFragmentManager(), DATE_PICKER);
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormStardMonitorAndMaintenancePart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public void changeToView(int viewId) {
        ((StardMonitorAndMaintenanceActivityForm) activity).changeToView(viewId, 1);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2;
    }

    @Override
    protected void setMunicipalities(String[] municipalitiesArray) {
        ((FragmentFormStardMonitorAndMaintenancePart1Binding) binding).municipality.setData(municipalitiesArray);
    }
}
