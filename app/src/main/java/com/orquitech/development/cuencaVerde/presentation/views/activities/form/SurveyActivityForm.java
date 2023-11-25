package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.databinding.ActivitySurveyBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.SurveyFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.utils.ViewUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyBaseViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.SurveyViewMvvm;

public class SurveyActivityForm extends BaseActivityForm implements SurveyFragmentListener, SurveyViewMvvm.View, ListDialogListener {

    private ActivitySurveyBinding binding;
    private SurveyViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey);

        viewModel = (SurveyViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_SURVEY_MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            bundle = getIntent().getExtras();
            changeToView(AppViewsFactory.FORM_SURVEY_VIEW_PART_1, bundle);
        }

        setUpProgressBar();
        setProgressBarViews(progressBarPosition);
        setLocationTimeInterval(10000);
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
        FormSurveyBaseViewMvvm.View currentFragment = (FormSurveyBaseViewMvvm.View) getCurrentFragment();
        Survey survey = currentFragment.getSurvey();
        if (survey != null) {
            projectsManager.saveSurvey(survey);
        }
    }

    @Override
    public void initLocationRequest() {
        locationManager.stopGoogleApiClient();
        locationManager.initGoogleApiClient();
    }

    @Override
    protected void onLocationResult(Location location) {
        FormSurveyBaseViewMvvm.View currentFragment = (FormSurveyBaseViewMvvm.View) getCurrentFragment();
        currentFragment.setSurveyLocation(location);
    }

    @Override
    protected void setUpProgressBar() {
        ViewUtil.measureView(binding.bottomNavigation, () -> {

            int[] startLocation = new int[2];
            binding.circle1.getLocationOnScreen(startLocation);

            int[] endLocation = new int[2];
            binding.circle6.getLocationOnScreen(endLocation);

            int[] endLocationTop = new int[2];
            binding.circle2.getLocationOnScreen(endLocationTop);

            boolean isPortrait = ViewUtil.isPortrait(getResources());
            int navigationBarHeight = isPortrait ? 0 : (int) getResources().getDimension(R.dimen.navigation_bar_height);
            int offset = isNavigationBarRightOfContent() ? -navigationBarHeight / 3 : navigationBarHeight;

            RelativeLayout.LayoutParams progressBarLayoutParams = (RelativeLayout.LayoutParams) binding.progressBar.getLayoutParams();
            progressBarLayoutParams.width = endLocation[0] - startLocation[0];
            binding.progressBar.setLayoutParams(progressBarLayoutParams);
            binding.progressBar.setX(startLocation[0] - offset);

            RelativeLayout.LayoutParams progressBarTopLayoutParams = (RelativeLayout.LayoutParams) binding.progressBarTop.getLayoutParams();
            progressBarTopLayoutParams.width = endLocationTop[0] - startLocation[0];
            binding.progressBarTop.setLayoutParams(progressBarTopLayoutParams);
            binding.progressBarTop.setX(startLocation[0] - offset);
        });
    }

    @Override
    protected void animateProgressBarTo(int scaleFactor, boolean animate) {
        if (binding.bottomNavigation.getWidth() == 0 || binding.bottomNavigation.getHeight() == 0) {
            ViewUtil.measureView(binding.bottomNavigation, () -> finishProgressBarAnimation(scaleFactor, animate));
        } else {
            finishProgressBarAnimation(scaleFactor, animate);
        }
    }

    @Override
    public void changeToView(int viewId, int newPosition) {
        FormSurveyBaseViewMvvm.View currentFragment = (FormSurveyBaseViewMvvm.View) getCurrentFragment();
        Survey survey = currentFragment.getSurvey();
        if (currentFragment.canGoToNextScreen()) {
            bundle.putParcelable(PARCELABLE, survey);
            super.changeToView(viewId, bundle);
            viewModel.saveSurvey(survey);
            setProgressBarViews(newPosition);
        }
    }

    @Override
    protected void setProgressBarViews(int newPosition, boolean animate) {
        animateProgressBarTo(newPosition, animate);
        this.progressBarPosition = newPosition;
        switch (newPosition) {
            case 0:
                fadeOutButton(binding.circle2Top, binding.circle3Top, binding.circle4Top, binding.circle5Top, binding.circle6Top);
                break;
            case 1:
                fadeInButton(binding.circle2Top);
                fadeOutButton(binding.circle3Top, binding.circle4Top, binding.circle5Top, binding.circle6Top);
                break;
            case 2:
                fadeInButton(binding.circle2Top, binding.circle3Top);
                fadeOutButton(binding.circle4Top, binding.circle5Top, binding.circle6Top);
                break;
            case 3:
                fadeInButton(binding.circle2Top, binding.circle3Top, binding.circle4Top);
                fadeOutButton(binding.circle5Top, binding.circle6Top);
                break;
            case 4:
                fadeInButton(binding.circle2Top, binding.circle3Top, binding.circle4Top, binding.circle5Top);
                fadeOutButton(binding.circle6Top);
                break;
            case 5:
                fadeInButton(binding.circle2Top, binding.circle3Top, binding.circle4Top, binding.circle5Top, binding.circle6Top);
                break;
        }
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
