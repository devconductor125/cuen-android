package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormSiembraDetailFormViewMainViewModel extends BaseViewModel implements SiembraDetailFormViewMvvm.ViewModel {

    private final SiembraDetailFormViewMvvm.View view;

    public FormSiembraDetailFormViewMainViewModel(AppView view) {
        super(view);
        this.view = (SiembraDetailFormViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {

    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.saveSiembraDetailForm((SiembraDetailForm) parcelable, view.getTaskObjectId());
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
