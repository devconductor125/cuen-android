package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.databinding.ActivityStardSheetFormBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.StardSheetFormViewMvvm;

public class StardSheetFormActivityForm extends BaseActivityForm implements StardSheetFormViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityStardSheetFormBinding binding;
    private StardSheetFormViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stard_sheet_form);
        getComponent().inject(this);

        viewModel = (StardSheetFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_STARD_SHEET_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_STARD_SHEET_FORM_VIEW_PART_1, bundle);
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
        FormStardSheetFormViewMvvm.View currentFragment = (FormStardSheetFormViewMvvm.View) getCurrentFragment();
        StardSheetForm stardSheetForm = currentFragment.getStardSheetForm();
        if (stardSheetForm != null) {
            viewModel.saveParcelable(stardSheetForm);
        }
    }

    @Override
    public String getTaskObjectId() {
        FormStardSheetFormViewMvvm.View currentFragment = (FormStardSheetFormViewMvvm.View) getCurrentFragment();
        Task task = currentFragment.getTask();
        return task.getId();
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormStardSheetFormBaseViewMvvm.View currentFragment = (FormStardSheetFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            StardSheetForm stardSheetForm = currentFragment.getStardSheetForm();
            bundle.putParcelable(PARCELABLE, stardSheetForm);
            super.changeToView(viewId, bundle);
            viewModel.saveParcelable(stardSheetForm);
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
