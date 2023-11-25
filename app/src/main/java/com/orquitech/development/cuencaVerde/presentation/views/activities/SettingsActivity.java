package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.databinding.ActivitySettingsBinding;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ConfirmationDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.OfflineDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SettingsViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;
import com.onesignal.OneSignal;

import java.io.Serializable;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SettingsActivity extends BaseActivity implements DialogListener, SettingsViewMvvm.View, ToolBarListener {

    protected static final String LOG_OUT_DIALOG = "logOutDialog";
    protected static final String OFFLINE_DIALOG = "offlineDialog";
    private ActivitySettingsBinding binding;
    private SettingsViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        getComponent().inject(this);

        viewModel = (SettingsViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.SETTINGS_VIEW_VIEW_MODEL);
        binding.setViewModel(viewModel);
        binding.toolBar.setListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onReadyForSubscriptions();
    }

    @Override
    protected void onPause() {
        viewModel.clearSubscriptions();
        dismissProgressDialog();
        super.onPause();
    }

    @Override
    public void onToolbarLeftIconClick() {
        finish();
    }

    @Override
    public void onToolbarMidIconClick() {

    }

    @Override
    public void onToolbarRightEndIconClick() {

    }

    @Override
    public void onLogOut() {
        ConfirmationDialogFragment fragment = (ConfirmationDialogFragment) dialogManager.getDialogFragment(DialogsFactory.CONFIRMATION, new Bundle());
        fragment.setTitle(getString(R.string.log_out_question));
        fragment.show(getSupportFragmentManager(), LOG_OUT_DIALOG);
    }

    @Override
    public void getOfflineData() {
        OfflineDialogFragment fragment = (OfflineDialogFragment) dialogManager.getDialogFragment(DialogsFactory.OFFLINE, new Bundle());
        fragment.show(getSupportFragmentManager(), OFFLINE_DIALOG);
    }

    @Override
    public void onOfflineDataReady() {
        dismissProgressDialog();
        showMessage(getString(R.string.success_offline_data), R.color.colorSecondary);
    }

    private void dismissProgressDialog() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(OFFLINE_DIALOG);
        if (fragment != null && fragment instanceof OfflineDialogFragment) {
            ((OfflineDialogFragment) fragment).dismiss();
        }
    }

    @Override
    public void onButtonOne() {

    }

    @Override
    public void onButtonTwo(Bundle bundle) {
        preferencesManager.clearUser();
        accountManager.clearAccessToken();
        FilesUtils.clearApplicationData(getFilesDir());
        OneSignal.setSubscription(false);
        disposables.add(persistenceManager.getOnClearedDatabaseObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    Intent stopIntent = new Intent(UserLocationService.FORCE_STOP_TRACKING);
                    sendBroadcast(stopIntent);

                    Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }).subscribe());
        persistenceManager.clearTables();
    }

    @Override
    public void onItemClick(Bundle bundle) {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onMidButtonClick(Parcelable data) {

    }

    @Override
    public void onMidButtonClick(Serializable data) {

    }
}
