package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormViewMvvm;

public interface FormVegetalMaintenanceViewMvvm {

    interface View extends FormVegetalMaintenanceBaseViewMvvm.View {

        void showExecutionStartDatePicker(AppDate date);

        void showExecutionEndDatePicker(AppDate date);
    }

    interface ViewModel extends FormViewMvvm.ViewModel {

        VegetalMaintenance getVegetalMaintenance();

        void setVegetalMaintenance(VegetalMaintenance stardMonitorAndMaintenance);

        void showExecutionStartDatePicker(android.view.View view);

        void setExecutionStartDate(int year, int month, int day);

        void showExecutionEndDatePicker(android.view.View view);

        void setExecutionEndDate(int year, int month, int day);
    }
}
