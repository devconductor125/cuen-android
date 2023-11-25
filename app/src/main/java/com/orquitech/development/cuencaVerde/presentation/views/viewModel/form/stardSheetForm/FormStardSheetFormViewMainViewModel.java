package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormStardSheetFormViewMainViewModel extends BaseViewModel implements StardSheetFormViewMvvm.ViewModel {

    private final StardSheetFormViewMvvm.View view;

    public FormStardSheetFormViewMainViewModel(AppView view) {
        super(view);
        this.view = (StardSheetFormViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_STARD_SHEET_FORM_VIEW_PART_1;
                break;
            case 1:
                viewId = AppViewsFactory.FORM_STARD_SHEET_FORM_VIEW_PART_2;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.saveStardSheetForm((StardSheetForm) parcelable, view.getTaskObjectId());
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
