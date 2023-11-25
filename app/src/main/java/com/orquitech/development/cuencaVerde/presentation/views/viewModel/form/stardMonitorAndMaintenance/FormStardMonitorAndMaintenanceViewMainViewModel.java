package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormStardMonitorAndMaintenanceViewMainViewModel extends BaseViewModel implements StardMonitorAndMaintenanceViewMvvm.ViewModel {

    private final StardMonitorAndMaintenanceViewMvvm.View view;

    public FormStardMonitorAndMaintenanceViewMainViewModel(AppView view) {
        super(view);
        this.view = (StardMonitorAndMaintenanceViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_1;
                break;
            case 1:
                viewId = AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.saveStardMonitorAndMaintenance((StardMonitorAndMaintenance) parcelable);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
