//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.maintenanceControl;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormMaintenanceControlPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormMaintenanceControlFragmentPart1 extends FormMaintenanceControlBaseFragment {

    public static FormMaintenanceControlFragmentPart1 getInstance(Bundle data) {
        FormMaintenanceControlFragmentPart1 fragment = new FormMaintenanceControlFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_maintenance_control_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormMaintenanceControlPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormMaintenanceControlPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormMaintenanceControlPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2;
    }
}
