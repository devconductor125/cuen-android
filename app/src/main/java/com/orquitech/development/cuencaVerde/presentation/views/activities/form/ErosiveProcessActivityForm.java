package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.databinding.ActivityErosiveProcessFormBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm.ErosiveProcessFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm.FormErosiveProcessFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm.FormErosiveProcessFormViewMvvm;

public class ErosiveProcessActivityForm extends BaseActivityForm implements ErosiveProcessFormViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityErosiveProcessFormBinding binding;
    private ErosiveProcessFormViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_erosive_process_form);

        viewModel = (ErosiveProcessFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_EROSIVE_PROCESS_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_EROSIVE_PROCESS_FORM_VIEW_PART_1, bundle);
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
        FormErosiveProcessFormViewMvvm.View currentFragment = (FormErosiveProcessFormViewMvvm.View) getCurrentFragment();
        Task task = currentFragment.getTask();
        ErosiveProcessForm erosiveProcessForm = currentFragment.getErosiveProcessForm();
        if (!TextUtils.isEmpty(erosiveProcessForm.getId())) {
            prediosManager.saveErosiveProcessForm(erosiveProcessForm, task.getId());
        }
    }

    @Override
    public String getHash() {
        FormErosiveProcessFormViewMvvm.View currentFragment = (FormErosiveProcessFormViewMvvm.View) getCurrentFragment();
        ErosiveProcessForm erosiveProcessForm = currentFragment.getErosiveProcessForm();
        return erosiveProcessForm.getFeature().getProperties().getHash();
    }

    @Override
    public String getTaskObjectId() {
        FormErosiveProcessFormViewMvvm.View currentFragment = (FormErosiveProcessFormViewMvvm.View) getCurrentFragment();
        return currentFragment.getTask().getId();
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormErosiveProcessFormBaseViewMvvm.View currentFragment = (FormErosiveProcessFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            ErosiveProcessForm erosiveProcessForm = currentFragment.getErosiveProcessForm();
            bundle.putParcelable(PARCELABLE, erosiveProcessForm);
            super.changeToView(viewId, bundle);
            viewModel.saveParcelable(erosiveProcessForm);
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
