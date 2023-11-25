package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm;

import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormSamplingPointFormViewMainViewModel extends BaseViewModel implements SamplingPointFormViewMvvm.ViewModel {

    private final SamplingPointFormViewMvvm.View view;

    public FormSamplingPointFormViewMainViewModel(AppView view) {
        super(view);
        this.view = (SamplingPointFormViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {

    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        SamplingPointForm samplingPointForm = (SamplingPointForm) parcelable;
        if (samplingPointForm != null) {
            Feature feature = samplingPointForm.getFeature();
            if (feature != null && feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                prediosManager.saveSamplingPointForm((SamplingPointForm) parcelable, view.getTaskObjectId());
            }
        }
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
