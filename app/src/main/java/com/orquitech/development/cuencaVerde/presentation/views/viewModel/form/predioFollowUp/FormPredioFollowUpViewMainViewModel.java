package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormPredioFollowUpViewMainViewModel extends BaseViewModel implements PredioFollowUpViewMvvm.ViewModel {

    private final PredioFollowUpViewMvvm.View view;

    public FormPredioFollowUpViewMainViewModel(AppView view) {
        super(view);
        this.view = (PredioFollowUpViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_PREDIO_FOLLOW_UP_MAIN_VIEW_PART_1;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.savePredioFollowUp((PredioFollowUp) parcelable);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
