package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.databinding.ActivitySiembraDetailFormBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.SiembraDetailFormViewMvvm;

public class SiembraDetailActivityForm extends BaseActivityForm implements SiembraDetailFormViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivitySiembraDetailFormBinding binding;
    private SiembraDetailFormViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_siembra_detail_form);
        getComponent().inject(this);

        viewModel = (SiembraDetailFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_SIEMBRA_DETAIL_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_SIEMBRA_DETAIL_FORM_VIEW_PART_1, bundle);
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
        FormSiembraDetailFormViewMvvm.View currentFragment = (FormSiembraDetailFormViewMvvm.View) getCurrentFragment();
        Task task = currentFragment.getTask();
        SiembraDetailForm siembraDetailForm = currentFragment.getSiembraDetailForm();
        if (!TextUtils.isEmpty(siembraDetailForm.getId())) {
            prediosManager.saveSiembraDetailForm(siembraDetailForm, task.getId());
        }
    }

    @Override
    public String getHash() {
        FormSiembraDetailFormViewMvvm.View currentFragment = (FormSiembraDetailFormViewMvvm.View) getCurrentFragment();
        SiembraDetailForm siembraDetailForm = currentFragment.getSiembraDetailForm();
        return siembraDetailForm.getFeature().getProperties().getHash();
    }

    @Override
    public String getTaskObjectId() {
        FormSiembraDetailFormViewMvvm.View currentFragment = (FormSiembraDetailFormViewMvvm.View) getCurrentFragment();
        return currentFragment.getTask().getId();
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormSiembraDetailFormBaseViewMvvm.View currentFragment = (FormSiembraDetailFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            SiembraDetailForm siembraDetailForm = currentFragment.getSiembraDetailForm();
            bundle.putParcelable(PARCELABLE, siembraDetailForm);
            super.changeToView(viewId, bundle);
            viewModel.saveParcelable(siembraDetailForm);
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
