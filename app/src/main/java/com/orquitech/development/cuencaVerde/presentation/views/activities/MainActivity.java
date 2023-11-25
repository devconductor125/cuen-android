package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.databinding.ActivityMainBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MainViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PQRSViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.dasboardHeader.DashBoardHeaderListener;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

public class MainActivity extends BaseLocationActivity implements MainViewMvvm.View, DashBoardHeaderListener, ListDialogListener, OSSubscriptionObserver {

    private static String ACTIVE_BOTTOM_VIEW = "activeBottomView";

    private ActivityMainBinding binding;
    private MainViewMvvm.ViewModel viewModel;
    private int currentActiveBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getComponent().inject(this);

        viewModel = (MainViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAIN_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);

        initNavigationListener();

        if (savedInstanceState == null) {
            BaseFragment fragment;
            if (accountManager.hasAccessToken()) {
                fragment = viewsFactory.getAppFragmentView(AppViewsFactory.DASHBOARD_VIEW, new Bundle());
                fragment.animate(false);
                animateBottomBarIcons(AppViewsFactory.DASHBOARD_VIEW);
                sendOneSignalId();
                evaluateRole();
            } else {
                fragment = viewsFactory.getAppFragmentView(AppViewsFactory.LOGIN_VIEW, new Bundle());
            }
            replaceFragment(fragment);
        }

        checkForUpdates();

        OneSignal.addSubscriptionObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(ACTIVE_BOTTOM_VIEW, currentActiveBottomView);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.currentActiveBottomView = savedInstanceState.getInt(ACTIVE_BOTTOM_VIEW);
        animateBottomBarIcons(currentActiveBottomView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (accountManager.hasAccessToken() && !stateChanges.getFrom().getSubscribed() && stateChanges.getTo().getSubscribed()) {
            sendOneSignalId(stateChanges.getTo().getUserId());
            viewModel.setOneSignalId(stateChanges.getTo().getUserId());
        }
    }

    @Override
    public void openPredioPotencialView() {
        changeToActivity(AppViewsFactory.PREDIO_POTENCIAL_VIEW, new Bundle());
    }

    @Override
    public void onPrediosClick() {
        Bundle bundle = new Bundle();
        changeToActivity(AppViewsFactory.PREDIOS_VIEW, bundle);
    }

    @Override
    public void onSettingsClick() {
        changeToActivity(AppViewsFactory.SETTINGS_VIEW, new Bundle());
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // TODO Remove this for store builds!
        // UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }

    /**
     * BottomNavigationView listener
     **/

    private void initNavigationListener() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_map:
                    replaceFragment(viewsFactory.getAppFragmentView(AppViewsFactory.MAP_TASK_VIEW, new Bundle()));
                    break;
                case R.id.action_tracks:
                    replaceFragment(viewsFactory.getAppFragmentView(AppViewsFactory.TRACKS_VIEW, new Bundle()));
                    break;
            }
            return true;
        });
    }

    public void hideNavigationBar() {
        binding.bottomNavigation2.setVisibility(android.view.View.GONE);
    }

    private void showNavigationBar() {
        binding.bottomNavigation2.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void changeToView(int viewType, Bundle bundle, boolean shouldReplace) {
        super.changeToView(viewType, bundle, shouldReplace);
        showNavigationBar();
        animateBottomBarIcons(viewType);
    }

    private void animateBottomBarIcons(int viewId) {
        this.currentActiveBottomView = viewId;
        switch (viewId) {
            case AppViewsFactory.DASHBOARD_VIEW:
                scaleUp(binding.bottomNavigationLeftIcon, binding.bottomNavigationLeftText);
                scaleDown(binding.bottomNavigationMidIcon, binding.bottomNavigationMidText);
                scaleDown(binding.bottomNavigationRightIcon, binding.bottomNavigationRightText);
                break;
            case AppViewsFactory.TASKS_VIEW:
                scaleDown(binding.bottomNavigationLeftIcon, binding.bottomNavigationLeftText);
                scaleUp(binding.bottomNavigationMidIcon, binding.bottomNavigationMidText);
                scaleDown(binding.bottomNavigationRightIcon, binding.bottomNavigationRightText);
                break;
            case AppViewsFactory.PQRS_VIEW:
                scaleDown(binding.bottomNavigationLeftIcon, binding.bottomNavigationLeftText);
                scaleDown(binding.bottomNavigationMidIcon, binding.bottomNavigationMidText);
                scaleUp(binding.bottomNavigationRightIcon, binding.bottomNavigationRightText);
                break;
        }
    }

    private void scaleUp(android.view.View... views) {
        for (android.view.View view : views) {
            view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(400)
                    .setInterpolator(new OvershootInterpolator(2f))
                    .start();
        }
    }

    private void scaleDown(android.view.View... views) {
        for (android.view.View view : views) {
            view.animate()
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(400)
                    .setInterpolator(new OvershootInterpolator(2f))
                    .start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.PREDIO_POTENCIAL_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        showCoordinatedSnackBar(TextUtil.getString(this, R.string.send_predio_potencial_success), R.color.colorSecondary);
                        break;
                }
                break;
            case AppViewsFactory.SURVEY_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        showMessage(TextUtil.getString(this, R.string.sent_survey_predio_potencial_success), R.color.colorSecondary);
                        break;
                }
                break;
            case AppViewsFactory.MAP_MONITOR_AND_MAINTENANCE_VIEW:
            case AppViewsFactory.MAP_CONTRACT_SIEMBRA_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        showMessage(TextUtil.getString(this, R.string.send_monitor_maintenance_success), R.color.colorSecondary);
                        break;
                }
            case AppViewsFactory.CARTA_INTENCION_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        showMessage(TextUtil.getString(this, R.string.send_monitor_maintenance_success), R.color.colorSecondary);
                        break;
                }
            case AppViewsFactory.SETTINGS_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.LOGIN_VIEW, new Bundle());
                        replaceFragment(fragment);
                        break;
                }
                break;
        }
    }

    @Override
    public void onItemClick(Bundle bundle) {
        int itemsType = bundle.getInt(ItemsListFragment.ITEMS_TYPE);
        BaseItem item = bundle.getParcelable(RxBus.PAYLOAD);
        if (item instanceof Dependency) {
            Dependency dependency = (Dependency) item;
            switch (itemsType) {
                case DialogsFactory.DEPENDENCY_PICKER:
                    if (getCurrentFragment() != null && getCurrentFragment() instanceof PQRSViewMvvm.View) {
                        PQRSViewMvvm.View currentFragment = (PQRSViewMvvm.View) getCurrentFragment();
                        currentFragment.showPQRSTypeDialog(dependency);
                    }
                    break;
            }
        } else if (item instanceof PQRSType) {
            PQRSType pqrsType = (PQRSType) item;
            if (getCurrentFragment() != null && getCurrentFragment() instanceof PQRSViewMvvm.View) {
                PQRSViewMvvm.View currentFragment = (PQRSViewMvvm.View) getCurrentFragment();
                currentFragment.setPqrsType(pqrsType);
            }
        }
    }

    @Override
    protected void onLocationResult(Location location) {
        if (getCurrentFragment() != null && getCurrentFragment() instanceof PQRSViewMvvm.View) {
            PQRSViewMvvm.View currentFragment = (PQRSViewMvvm.View) getCurrentFragment();
            currentFragment.setLocation(location);
        }
    }

    public void evaluateRole() {
        viewModel.evaluateRole();
    }

    @Override
    public void hidePqrsButton() {
        binding.pqrsContainer.setVisibility(View.GONE);
    }
}
