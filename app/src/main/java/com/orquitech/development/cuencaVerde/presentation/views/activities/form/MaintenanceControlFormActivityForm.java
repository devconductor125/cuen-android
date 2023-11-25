package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.databinding.ActivityMaintenanceControlBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.MaintenanceControlViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyBaseViewMvvm;

public class MaintenanceControlFormActivityForm extends BaseActivityForm implements MaintenanceControlViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityMaintenanceControlBinding binding;
    private MaintenanceControlViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maintenance_control);

        viewModel = (MaintenanceControlViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_MONITOR_CONTROL_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_MAINTENANCE_CONTROL_MAIN_VIEW_PART_1, bundle);
        }

        setUpProgressBar();
        setProgressBarViews(progressBarPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onReadyForSubscriptions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.clearSubscriptions();
        FormMaintenanceControlViewMvvm.View currentFragment = (FormMaintenanceControlViewMvvm.View) getCurrentFragment();
        MaintenanceControl maintenanceControl = currentFragment.getMaintenanceControl();
        MonitorAndMaintenance monitorAndMaintenance = (MonitorAndMaintenance) currentFragment.getTask();
        if (maintenanceControl != null) {
            prediosManager.saveMaintenanceControl(maintenanceControl, monitorAndMaintenance.getId());
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormMaintenanceControlBaseViewMvvm.View currentFragment = (FormMaintenanceControlBaseViewMvvm.View) getCurrentFragment();
        MaintenanceControl maintenanceControl = currentFragment.getMaintenanceControl();
        bundle.putParcelable(PARCELABLE, maintenanceControl);
        super.changeToView(viewId, bundle);
        viewModel.saveParcelable(maintenanceControl);
        setProgressBarViews(newPosition);
    }

    @Override
    public String getMonitorAndMaintenanceId() {
        FormMaintenanceControlBaseViewMvvm.View currentFragment = (FormMaintenanceControlBaseViewMvvm.View) getCurrentFragment();
        MonitorAndMaintenance monitorAndMaintenance = (MonitorAndMaintenance) currentFragment.getTask();
        return monitorAndMaintenance.getId();
    }

    @Override
    public void onItemClick(Bundle bundle) {
        FormSurveyBaseViewMvvm.View currentFragment = (FormSurveyBaseViewMvvm.View) getCurrentFragment();
        Action action = bundle.getParcelable(RxBus.PAYLOAD);
        if (action != null && currentFragment instanceof FormSurveyFragmentPart1) {
            ((FormSurveyFragmentPart1) currentFragment).setPropertyCorrelation(action.getTitle());
        }
    }

    @Override
    protected NavigationFlowViewGetter getNavigationFlowViewGetter() {
        return new NavigationFlowViewGetter() {
            @Override
            public View getRoot() {
                return binding.getRoot();
            }

            @Override
            public View getBottomNavigation() {
                return binding.bottomNavigation;
            }

            @Override
            public View getCircle1() {
                return binding.circle1;
            }

            @Override
            public View getCircle2() {
                return binding.circle1;
            }

            @Override
            public View getProgressBar() {
                return binding.progressBar;
            }

            @Override
            public View getProgressBarTop() {
                return binding.progressBarTop;
            }

            @Override
            public ViewGroup getMainContainer() {
                return binding.container;
            }
        };
    }
}
