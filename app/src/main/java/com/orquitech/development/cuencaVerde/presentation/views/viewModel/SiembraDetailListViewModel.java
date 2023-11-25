package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SiembraDetailListViewModel extends BaseSingleListViewModel implements SiembraListDetailViewMvvm.ViewModel {

    private String contractSiembraId;
    private String contractSiembraName;
    private Feature feature;
    private CompositeDisposable viewModelDisposable;

    @Inject
    App app;

    @Inject
    PrediosManager prediosManager;

    @Inject
    ApiManager apiManager;

    public SiembraDetailListViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
        viewModelDisposable = new CompositeDisposable();
    }

    @Override
    public void setFeature(Feature feature) {
        if (this.feature == null) {
            this.feature = feature;
            this.contractSiembraId = this.feature.getProperties().getHash();
            this.contractSiembraName = TextUtil.getString(view.getContext(), R.string.siembra_detail);
        }
    }

    @Override
    public String getContractSiembraName() {
        if (TextUtils.isEmpty(contractSiembraName)) {
            contractSiembraName = TextUtil.getString(view.getContext(), R.string.tasks);
        }
        return contractSiembraName;
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
        getSiembraDetails();
    }

    private void subscribeToObservables() {
        subscribeToItemsListObservable(prediosManager.getSiembraDetailsObservable(), AppViewsFactory.SIEMBRA_DETAIL_LIST_ITEM_VIEW);
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        viewModelDisposable.clear();
    }

    @Override
    public void getSiembraDetails(boolean refresh) {
        getSiembraDetails();
    }

    @Override
    public void getSiembraDetails() {
        prediosManager.getSiembraDetails(feature.getProperties().getHash()).subscribe();
    }

    @Override
    public void onBackClick(View view) {
        ((SiembraListDetailViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasContractSiembra() {
        return feature != null;
    }

    @Override
    public void onNewSiembraDetail(View view) {
        Bundle bundle = new Bundle();
        SiembraDetailForm siembraDetailForm = new SiembraDetailForm();
        siembraDetailForm.setFeature(feature);
        bundle.putParcelable(RxBus.PAYLOAD, siembraDetailForm);
        ((SiembraListDetailViewMvvm.View) this.view).openSiembraDetailForm(bundle);
    }
}
