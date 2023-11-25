package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.databinding.ActivityMeetingsWithActorsFormBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.MeetingsWithActorsFormViewMvvm;

public class MeetingsWithActorsActivityForm extends BaseActivityForm implements MeetingsWithActorsFormViewMvvm.View, ListDialogListener, FormFragmentListener {

    private ActivityMeetingsWithActorsFormBinding binding;
    private MeetingsWithActorsFormViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meetings_with_actors_form);
        getComponent().inject(this);

        viewModel = (MeetingsWithActorsFormViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_MEETINGS_WITH_ACTORS_FORM_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_MEETINGS_WITH_ACTORS_FORM_VIEW_PART_1, bundle);
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
        FormMeetingsWithActorsFormViewMvvm.View currentFragment = (FormMeetingsWithActorsFormViewMvvm.View) getCurrentFragment();
        MeetingsWithActorsForm meetingsWithActorsForm = currentFragment.getMeetingsWithActorsForm();
        if (meetingsWithActorsForm != null) {
            viewModel.saveParcelable(meetingsWithActorsForm);
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormMeetingsWithActorsFormBaseViewMvvm.View currentFragment = (FormMeetingsWithActorsFormBaseViewMvvm.View) getCurrentFragment();
        if (currentFragment.canGoToNextScreen()) {
            MeetingsWithActorsForm meetingsWithActorsForm = currentFragment.getMeetingsWithActorsForm();
            bundle.putParcelable(PARCELABLE, meetingsWithActorsForm);
            super.changeToView(viewId, bundle);
            viewModel.saveParcelable(meetingsWithActorsForm);
            setProgressBarViews(newPosition);
        }
    }

    @Override
    public Task getTask() {
        FormMeetingsWithActorsFormBaseViewMvvm.View currentFragment = (FormMeetingsWithActorsFormBaseViewMvvm.View) getCurrentFragment();
        return currentFragment.getTask();
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
