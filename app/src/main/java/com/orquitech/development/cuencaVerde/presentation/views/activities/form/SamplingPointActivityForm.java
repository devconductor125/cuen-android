package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.databinding.ActivitySamplingPointFormBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm.FormSamplingPointFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm.FormSamplingPointFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm.SamplingPointFormViewMvvm;

public class SamplingPointActivityForm extends BaseActivityForm implements SamplingPointFormViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivitySamplingPointFormBinding binding;
    private SamplingPointFormViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sampling_point_form);

        viewModel = (SamplingPointFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_SAMPLING_POINT_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_SAMPLING_POINT_FORM_VIEW_PART_1, bundle);
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
        FormSamplingPointFormViewMvvm.View currentFragment = (FormSamplingPointFormViewMvvm.View) getCurrentFragment();
        Task task = currentFragment.getTask();
        SamplingPointForm samplingPointForm = currentFragment.getSamplingPointForm();
        if (!TextUtils.isEmpty(samplingPointForm.getId())) {
            prediosManager.saveSamplingPointForm(samplingPointForm, task.getId());
        }
    }

    @Override
    public String getHash() {
        FormSamplingPointFormViewMvvm.View currentFragment = (FormSamplingPointFormViewMvvm.View) getCurrentFragment();
        SamplingPointForm samplingPointForm = currentFragment.getSamplingPointForm();
        return samplingPointForm.getFeature().getProperties().getHash();
    }

    @Override
    public String getTaskObjectId() {
        FormSamplingPointFormViewMvvm.View currentFragment = (FormSamplingPointFormViewMvvm.View) getCurrentFragment();
        return currentFragment.getTask().getId();
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormSamplingPointFormBaseViewMvvm.View currentFragment = (FormSamplingPointFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            SamplingPointForm samplingPointForm = currentFragment.getSamplingPointForm();
            bundle.putParcelable(PARCELABLE, samplingPointForm);
            super.changeToView(viewId, bundle);
            viewModel.saveParcelable(samplingPointForm);
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
