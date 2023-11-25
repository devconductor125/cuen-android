package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PredioPotencialTasksViewModel extends BaseSingleListViewModel implements PredioPotencialTasksViewMvvm.ViewModel {

    private String predioId;
    private String predioName;
    private PredioPotencial predio;
    private CompositeDisposable viewModelDisposable;
    private boolean sendingPredio;

    @Inject
    App app;

    @Inject
    ApiManager apiManager;

    @Inject
    PrediosManager prediosManager;

    public PredioPotencialTasksViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
        viewModelDisposable = new CompositeDisposable();
    }

    @Override
    public void setPredio(PredioPotencial predio) {
        if (predio != null) {
            this.predio = predio;
            this.predioId = String.valueOf(predio.getId());
            this.predioName = TextUtil.getString(view.getContext(), R.string.project_tasks, predio.getName());
        }
    }

    @Override
    public String getPredioName() {
        if (TextUtils.isEmpty(predioName)) {
            predioName = TextUtil.getString(view.getContext(), R.string.predio_potencial_tasks);
        }
        return predioName;
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(prediosManager.getSentPredioPotencialObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(id -> {
                    sendingPredio = false;
                    if (id > 0) {
                        predio.setRemoteId(id);
                        ((PredioPotencialTasksViewMvvm.View) this.view).onViewModelUpdated();
                        ((PredioPotencialTasksViewMvvm.View) this.view).showMessage(R.string.send_predio_potencial_success, R.color.colorSecondary);
                    } else {
                        ((PredioPotencialTasksViewMvvm.View) this.view).showMessage(R.string.bad_response, R.color.colorAccent);
                    }
                }).subscribe());
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        viewModelDisposable.clear();
    }

    @Override
    public void onBackClick(View view) {
        ((PredioPotencialTasksViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasProject() {
        return predio != null;
    }

    @Override
    public boolean hasRemoteId() {
        return predio.getRemoteId() > 0;
    }

    @Override
    public void onSurvey(View view) {
        ((PredioPotencialTasksViewMvvm.View) this.view).changeToView(AppViewsFactory.SURVEY_VIEW);
    }

    @Override
    public void onCartaIntencion(View view) {
        ((PredioPotencialTasksViewMvvm.View) this.view).changeToView(AppViewsFactory.CARTA_INTENCION_VIEW);
    }

    @Override
    public void onSendPredio(View view) {
        if (!sendingPredio) {
            if (connectivityStatusManager.isConnected()) {
                prediosManager.sendPredioPotencial(predio);
            } else {
                ((PredioPotencialTasksViewMvvm.View) this.view).showMessage(R.string.cant_send_task_message, R.color.colorAccent);
            }
        }
    }

    @Override
    public void onSendDocuments(View view) {
        if (predio.getRemoteId() > 0) {
            prediosManager.sendDocuments(predio);
        } else {
            ((PredioPotencialTasksViewMvvm.View) this.view).showMessage(R.string.cant_send_predio_documents, R.color.colorAccent);
        }
    }

    @Override
    public PredioPotencial getPredioPotencial() {
        return predio;
    }
}
