package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.databinding.ActivityVegetalMaintenanceBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.VegetalMaintenanceViewMvvm;

public class VegetalMaintenanceActivityForm extends BaseActivityForm implements VegetalMaintenanceViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityVegetalMaintenanceBinding binding;
    private VegetalMaintenanceViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vegetal_maintenance);

        viewModel = (VegetalMaintenanceViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_PART_1, bundle);
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
        FormVegetalMaintenanceViewMvvm.View currentFragment = (FormVegetalMaintenanceViewMvvm.View) getCurrentFragment();
        VegetalMaintenance vegetalMaintenance = currentFragment.getVegetalMaintenance();
        if (vegetalMaintenance != null) {
            prediosManager.saveVegetalMaintenance(vegetalMaintenance);
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormVegetalMaintenanceBaseViewMvvm.View currentFragment = (FormVegetalMaintenanceBaseViewMvvm.View) getCurrentFragment();
        VegetalMaintenance vegetalMaintenance = currentFragment.getVegetalMaintenance();
        bundle.putParcelable(PARCELABLE, vegetalMaintenance);
        super.changeToView(viewId, bundle);
        viewModel.saveParcelable(vegetalMaintenance);
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
                return binding.mainContainer;
            }
        };
    }
}
