package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormErosiveProcessFormViewMvvm {

    interface View extends FormErosiveProcessFormBaseViewMvvm.View {

        void showDatePicker(AppDate date);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        ErosiveProcessForm getErosiveProcessForm();

        void setErosiveProcessForm(ErosiveProcessForm erosiveProcessForm);

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day);
    }
}
