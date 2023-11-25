package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Role;
import com.orquitech.development.cuencaVerde.logic.UserInfoManager;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DashboardHeaderViewModel extends BaseViewModel implements DashBoardHeaderMvvm.ViewModel {

    private final DashBoardHeaderMvvm.View view;

    @Inject
    UserInfoManager userInfoManager;

    @Inject
    PreferencesManager preferencesManager;

    public DashboardHeaderViewModel(AppView view) {
        super(view);
        this.view = (DashBoardHeaderMvvm.View) view;
        getComponent().inject(this);
        evaluateRole();
    }

    private void evaluateRole() {
        Role role = (Role) preferencesManager.get(AppPreferencesObjectFactory.ROLE, Role.class);
        if (role.hasNoQuota()) {
            view.hideQuota();
        }
        if (role.cantViewPredios()) {
            view.hidePrediosButtons();
        }
    }

    @Override
    public void onReadyForSubscriptions() {
        disposables.add(userInfoManager.getQuotaObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(view::setQuota)
                .subscribe());
    }

    @Override
    public void onCreatePredioPotencialClick(android.view.View view) {
        this.view.openPredioPotencialView();
    }

    @Override
    public void onPrediosClick(android.view.View view) {
        this.view.onPrediosClick();
    }

    @Override
    public void onSettingsClick(View view) {
        this.view.onSettingsClick();
    }

    public void getQuota() {
        userInfoManager.getQuota();
    }
}
