package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.databinding.ActivityStardMonitorAndMaintenanceBinding;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.StardMonitorAndMaintenanceViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyBaseViewMvvm;

import javax.inject.Inject;

public class StardMonitorAndMaintenanceActivityForm extends BaseActivityForm implements StardMonitorAndMaintenanceViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityStardMonitorAndMaintenanceBinding binding;
    private StardMonitorAndMaintenanceViewMvvm.ViewModel viewModel;

    @Inject
    PrediosManager prediosManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stard_monitor_and_maintenance);
        getComponent().inject(this);

        viewModel = (StardMonitorAndMaintenanceViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_1, bundle);
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
        FormStardMonitorAndMaintenanceViewMvvm.View currentFragment = (FormStardMonitorAndMaintenanceViewMvvm.View) getCurrentFragment();
        StardMonitorAndMaintenance stardMonitorAndMaintenance = currentFragment.getStardMonitorAndMaintenance();
        if (stardMonitorAndMaintenance != null) {
            prediosManager.saveStardMonitorAndMaintenance(stardMonitorAndMaintenance);
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormStardMonitorAndMaintenanceBaseViewMvvm.View currentFragment = (FormStardMonitorAndMaintenanceBaseViewMvvm.View) getCurrentFragment();
        StardMonitorAndMaintenance stardMonitorAndMaintenance = currentFragment.getStardMonitorAndMaintenance();
        bundle.putParcelable(PARCELABLE, stardMonitorAndMaintenance);
        super.changeToView(viewId, bundle);
        viewModel.saveParcelable(stardMonitorAndMaintenance);
        setProgressBarViews(newPosition);
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
                return binding.circle2;
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
                return binding.mainContainer;
            }
        };
    }
}
