package com.orquitech.development.cuencaVerde.presentation.views.activities.form;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseLocationActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.interfaces.NavigationFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.utils.ViewUtil;

public abstract class BaseActivityForm extends BaseLocationActivity implements ListDialogListener {

    protected static final String TASK_BUNDLE = "taskBundle";
    protected static final String PARCELABLE = "parcelable";
    protected static final String PROGRESS_BAR_POSITION = "progressBarPosition";

    protected Bundle bundle;
    protected int progressBarPosition = 0;

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bundle = savedInstanceState.getParcelable(TASK_BUNDLE);
        progressBarPosition = savedInstanceState.getInt(PROGRESS_BAR_POSITION);
        setProgressBarViews(progressBarPosition, false);
    }

    protected void setUpProgressBar() {
        ViewUtil.measureView(getNavigationFlowViewGetter().getBottomNavigation(), () -> {

            int[] startLocation = new int[2];
            getNavigationFlowViewGetter().getCircle1().getLocationOnScreen(startLocation);

            int[] endLocation = new int[2];
            getNavigationFlowViewGetter().getCircle2().getLocationOnScreen(endLocation);

            int[] endLocationTop = new int[2];
            getNavigationFlowViewGetter().getCircle2().getLocationOnScreen(endLocationTop);

            boolean isPortrait = ViewUtil.isPortrait(getResources());
            int navigationBarHeight = isPortrait ? 0 : (int) getResources().getDimension(R.dimen.navigation_bar_height);
            int offset = isNavigationBarRightOfContent() ? -navigationBarHeight / 3 : navigationBarHeight;

            RelativeLayout.LayoutParams progressBarLayoutParams = (RelativeLayout.LayoutParams) getNavigationFlowViewGetter().getProgressBar().getLayoutParams();
            progressBarLayoutParams.width = endLocation[0] - startLocation[0];
            getNavigationFlowViewGetter().getProgressBar().setLayoutParams(progressBarLayoutParams);
            getNavigationFlowViewGetter().getProgressBar().setX(startLocation[0] - offset);

            RelativeLayout.LayoutParams progressBarTopLayoutParams = (RelativeLayout.LayoutParams) getNavigationFlowViewGetter().getProgressBarTop().getLayoutParams();
            progressBarTopLayoutParams.width = endLocationTop[0] - startLocation[0];
            getNavigationFlowViewGetter().getProgressBarTop().setLayoutParams(progressBarTopLayoutParams);
            getNavigationFlowViewGetter().getProgressBarTop().setX(startLocation[0] - offset);
        });
    }

    protected boolean isNavigationBarRightOfContent() {
        int[] rootLocation = new int[2];
        getNavigationFlowViewGetter().getRoot().getLocationOnScreen(rootLocation);
        return rootLocation[0] == 0;
    }

    protected void setProgressBarViews(int newPosition) {
        setProgressBarViews(newPosition, true);
    }

    protected void setProgressBarViews(int newPosition, boolean animate) {
        animateProgressBarTo(newPosition, animate);
        this.progressBarPosition = newPosition;
    }

    protected void animateProgressBarTo(int scaleFactor, boolean animate) {
        if (getNavigationFlowViewGetter().getBottomNavigation().getWidth() == 0 || getNavigationFlowViewGetter().getBottomNavigation().getHeight() == 0) {
            ViewUtil.measureView(getNavigationFlowViewGetter().getBottomNavigation(), () -> finishProgressBarAnimation(scaleFactor, animate));
        } else {
            finishProgressBarAnimation(scaleFactor, animate);
        }
    }

    protected void finishProgressBarAnimation(int scaleFactor, boolean animate) {
        getNavigationFlowViewGetter().getProgressBarTop().setPivotX(0);
        getNavigationFlowViewGetter().getProgressBarTop().animate()
                .scaleX(scaleFactor)
                .setDuration(animate ? 400 : 0)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    protected void onLocationResult(Location location) {

    }

    protected abstract NavigationFlowViewGetter getNavigationFlowViewGetter();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(TASK_BUNDLE, bundle);
        savedInstanceState.putInt(PROGRESS_BAR_POSITION, progressBarPosition);
    }

    public void onFormSent(Bundle bundle) {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void showMessage(String string, int color) {
        showSnackBar(string, color, Snackbar.LENGTH_SHORT, getNavigationFlowViewGetter().getMainContainer());
    }

    @Override
    public void onItemClick(Bundle bundle) {
    }
}
