package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.databinding.ActivityContractorFormBinding;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.ContractorFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormViewMvvm;

import javax.inject.Inject;

public class ContractorActivityForm extends BaseActivityForm implements ContractorFormViewMvvm.View, FormFragmentListener {

    private ActivityContractorFormBinding binding;
    private ContractorFormViewMvvm.ViewModel viewModel;

    @Inject
    PrediosManager prediosManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contractor_form);
        getComponent().inject(this);

        viewModel = (ContractorFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_CONTRACTOR_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_CONTRACTOR_FORM_VIEW_PART_1, bundle);
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
        FormContractorFormViewMvvm.View currentFragment = (FormContractorFormViewMvvm.View) getCurrentFragment();
        ContractorForm contractorForm = currentFragment.getContractorForm();
        Task task = currentFragment.getTask();
        if (contractorForm != null) {
            prediosManager.saveContractorForm(contractorForm, task.getId());
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormContractorFormBaseViewMvvm.View currentFragment = (FormContractorFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            ContractorForm contractorForm = currentFragment.getContractorForm();
            bundle.putParcelable(PARCELABLE, contractorForm);
            super.changeToView(viewId, bundle);
            viewModel.saveContractorForm(contractorForm, contractorForm.getContractorSiembraId());
            setProgressBarViews(newPosition);
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
