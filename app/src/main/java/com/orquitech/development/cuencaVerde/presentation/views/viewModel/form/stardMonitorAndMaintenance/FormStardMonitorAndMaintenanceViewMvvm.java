package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormStardMonitorAndMaintenanceViewMvvm {

    interface View extends FormStardMonitorAndMaintenanceBaseViewMvvm.View {

        void onViewModelUpdated();

        void showDatePicker(AppDate date);

        void onBackPressed();

        void changeToView(int viewId);

        int getNextViewId();
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        StardMonitorAndMaintenance getStardMonitorAndMaintenance();

        void setStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance);

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day);
    }
}
