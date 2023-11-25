package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.location.Location;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PQRSViewModel extends BaseViewModel implements PQRSViewMvvm.ViewModel {

    private final PQRSViewMvvm.View view;
    private ObservableField<PQRS> pqrsObservableField = new ObservableField<>();

    private CompositeDisposable pqrsDisposable;

    public PQRSViewModel(AppView view) {
        super(view);
        this.view = (PQRSViewMvvm.View) view;
        PQRS pqrs = (PQRS) preferencesManager.get(AppPreferencesObjectFactory.PQRS, PQRS.class);
        setPqrs(pqrs != null ? pqrs : new PQRS());
        pqrsDisposable = new CompositeDisposable();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        pqrsDisposable.clear();
    }

    @Override
    public void onReadyForSubscriptions() {
        pqrsDisposable.add(prediosManager.getSentPQRSObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(pqrs -> {
                    if (this.view != null) {
                        clearPQRS();
                        this.view.onSendPQRSTriggered();
                    }
                })
                .subscribe());
    }

    private void clearPQRS() {
        this.pqrsObservableField.set(new PQRS());
        this.view.onViewModelUpdated();
        preferencesManager.remove(AppPreferencesObjectFactory.PQRS);
    }

    @Override
    public PQRS getPqrs() {
        return this.pqrsObservableField.get();
    }

    @Override
    public void setPqrs(PQRS pqrs) {
        this.pqrsObservableField.set(pqrs);
    }

    @Override
    public void switchSubscribeAgreementVisibility(View view, boolean isChecked) {
        this.view.setSubscribeAgreementVisibility(!isChecked);
    }

    @Override
    public void switchPredioPotencialVisibility(View view, boolean isChecked) {
        this.view.setPredioPotencialVisibility(isChecked);
    }

    @Override
    public void showDependencyPicker(View view) {
        this.view.showDependencyDialog();
    }

    @Override
    public void savePQRS() {
        preferencesManager.set(AppPreferencesObjectFactory.PQRS, pqrsObservableField.get());
    }

    @Override
    public void onSendPQRS(View view) {
        PQRS pqrs = pqrsObservableField.get();
        if (!pqrs.hasValidIdNumber()) {
            this.view.showErrorMessage(R.string.pqrs_missing_id_numer);
            return;
        }
        if (!pqrs.hasValidName()) {
            this.view.showErrorMessage(R.string.pqrs_missing_name);
            return;
        }
        if (!pqrs.hasValidEmailAddress()) {
            this.view.showErrorMessage(R.string.invalid_email_address);
            return;
        }
        if (pqrs.isCreatePredioPotencial() && !pqrs.hasValidPredioName()) {
            this.view.showErrorMessage(R.string.predio_potencial_missing_name);
            return;
        }
        if (pqrs.isCreatePredioPotencial() && !pqrs.hasValidCoordinates()) {
            this.view.showErrorMessage(R.string.predio_potencial_missing_location);
            return;
        }
        if (!pqrs.hasDependency()) {
            this.view.showErrorMessage(R.string.pqrs_missing_dependency);
            return;
        }
        if (!pqrs.hasPqrsType()) {
            this.view.showErrorMessage(R.string.pqrs_missing_pqrs_type);
            return;
        }
        if (!pqrs.hasDescription()) {
            this.view.showErrorMessage(R.string.pqrs_missing_description);
            return;
        }
        if (connectivityStatusManager.isConnected()) {
            prediosManager.sendPQRS(pqrsObservableField.get());
            clearPQRS();
        } else {
            prediosManager.savePQRS(pqrsObservableField.get());
            clearPQRS();
        }
        this.view.onSendPQRSTriggered();
    }

    @Override
    public void setDependency(Dependency dependency) {
        pqrsObservableField.get().setDependency(dependency);
        this.view.onViewModelUpdated();
    }

    @Override
    public void setPqrsType(PQRSType pqrsType) {
        pqrsObservableField.get().setPqrsType(pqrsType);
        this.view.onViewModelUpdated();
    }

    @Override
    public void setLocation(Location location) {
        pqrsObservableField.get().setLocation(location);
        this.view.onViewModelUpdated();
    }
}
