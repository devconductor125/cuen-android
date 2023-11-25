package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormVegetalMaintenanceViewMainViewModel extends BaseViewModel implements VegetalMaintenanceViewMvvm.ViewModel {

    private final VegetalMaintenanceViewMvvm.View view;

    public FormVegetalMaintenanceViewMainViewModel(AppView view) {
        super(view);
        this.view = (VegetalMaintenanceViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_PART_1;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.saveVegetalMaintenance((VegetalMaintenance) parcelable);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
