package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.disposables.CompositeDisposable;

public class SamplingPointListViewModel extends BaseSingleListViewModel implements SamplingPointDetailViewMvvm.ViewModel {

    protected String contractSiembraId;
    protected String samplingPointName;
    protected Feature feature;
    private CompositeDisposable viewModelDisposable;

    public SamplingPointListViewModel(AppView view) {
        super((AppListView) view);
        viewModelDisposable = new CompositeDisposable();
    }

    @Override
    public void setFeature(Feature feature) {
        if (this.feature == null) {
            this.feature = feature;
            this.contractSiembraId = this.feature.getProperties().getHash();
            this.samplingPointName = TextUtil.getString(view.getContext(), R.string.sampling_point_detail);
        }
    }

    @Override
    public String getSamplingPointName() {
        if (TextUtils.isEmpty(samplingPointName)) {
            samplingPointName = TextUtil.getString(view.getContext(), R.string.tasks);
        }
        return samplingPointName;
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
        getSamplingPoints();
    }

    private void subscribeToObservables() {
        subscribeToItemsListObservable(prediosManager.getSamplingPointsObservable(), AppViewsFactory.SAMPLING_POINT_LIST_ITEM_VIEW);
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        viewModelDisposable.clear();
    }

    @Override
    public void getSamplingPoints(boolean refresh) {
        getSamplingPoints();
    }

    @Override
    public void getSamplingPoints() {
        prediosManager.getSamplingPoints(feature.getProperties().getHash()).subscribe();
    }

    @Override
    public void onBackClick(View view) {
        ((SamplingPointDetailViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasTask() {
        return feature != null;
    }

    @Override
    public void onNewSamplingPoint(View view) {
        Bundle bundle = new Bundle();
        SamplingPointForm samplingPointForm = new SamplingPointForm();
        samplingPointForm.setFeature(feature);
        bundle.putParcelable(RxBus.PAYLOAD, samplingPointForm);
        ((SamplingPointDetailViewMvvm.View) this.view).openSamplingPointForm(bundle);
    }
}
