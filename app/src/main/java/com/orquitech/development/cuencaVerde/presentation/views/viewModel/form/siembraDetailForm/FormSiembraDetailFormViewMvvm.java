package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormSiembraDetailFormViewMvvm {

    interface View extends FormSiembraDetailFormBaseViewMvvm.View {

        void showDatePicker(AppDate date, int viewId);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        SiembraDetailForm getSiembraDetailForm();

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day, int viewId);

        void setSiembraDetailForm(SiembraDetailForm siembraDetailForm);
    }
}
