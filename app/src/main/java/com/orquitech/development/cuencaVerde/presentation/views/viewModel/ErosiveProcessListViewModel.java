package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.disposables.CompositeDisposable;

public class ErosiveProcessListViewModel extends BaseSingleListViewModel implements ErosiveProcessDetailViewMvvm.ViewModel {

    protected String contractSiembraId;
    protected String samplingPointName;
    protected Feature feature;
    private CompositeDisposable viewModelDisposable;

    public ErosiveProcessListViewModel(AppView view) {
        super((AppListView) view);
        viewModelDisposable = new CompositeDisposable();
    }

    @Override
    public void setFeature(Feature feature) {
        if (this.feature == null) {
            this.feature = feature;
            this.contractSiembraId = this.feature.getProperties().getHash();
            this.samplingPointName = TextUtil.getString(view.getContext(), R.string.erosive_process_detail);
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
        getErosiveProcessForms();
    }

    private void subscribeToObservables() {
        subscribeToItemsListObservable(prediosManager.getErosiveFormsObservable(), AppViewsFactory.EROSIVE_PROCESS_LIST_ITEM_VIEW);
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        viewModelDisposable.clear();
    }

    @Override
    public void getErosiveProcessForms(boolean refresh) {
        getErosiveProcessForms();
    }

    @Override
    public void getErosiveProcessForms() {
        prediosManager.getErosiveProcessForms(feature.getProperties().getHash()).subscribe();
    }

    @Override
    public void onBackClick(View view) {
        ((ErosiveProcessDetailViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasTask() {
        return feature != null;
    }

    @Override
    public void onNewErosiveProcessForm(View view) {
        Bundle bundle = new Bundle();
        ErosiveProcessForm erosiveProcessForm = new ErosiveProcessForm();
        erosiveProcessForm.setFeature(feature);
        bundle.putParcelable(RxBus.PAYLOAD, erosiveProcessForm);
        ((ErosiveProcessDetailViewMvvm.View) this.view).openErosiveProcessForm(bundle);
    }
}
