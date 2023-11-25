package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormMaintenanceControlViewMainViewModel extends BaseViewModel implements MaintenanceControlViewMvvm.ViewModel {

    private final MaintenanceControlViewMvvm.View view;

    public FormMaintenanceControlViewMainViewModel(AppView view) {
        super(view);
        this.view = (MaintenanceControlViewMvvm.View) view;
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
        prediosManager.saveMaintenanceControl((MaintenanceControl) parcelable, view.getMonitorAndMaintenanceId());
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
