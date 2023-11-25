package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm;

import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormErosiveProcessFormViewMainViewModel extends BaseViewModel implements ErosiveProcessFormViewMvvm.ViewModel {

    private final ErosiveProcessFormViewMvvm.View view;

    public FormErosiveProcessFormViewMainViewModel(AppView view) {
        super(view);
        this.view = (ErosiveProcessFormViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {

    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        ErosiveProcessForm erosiveProcessForm = (ErosiveProcessForm) parcelable;
        if (erosiveProcessForm != null) {
            Feature feature = erosiveProcessForm.getFeature();
            if (feature != null && feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                prediosManager.saveErosiveProcessForm((ErosiveProcessForm) parcelable, view.getTaskObjectId());
            }
        }
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
