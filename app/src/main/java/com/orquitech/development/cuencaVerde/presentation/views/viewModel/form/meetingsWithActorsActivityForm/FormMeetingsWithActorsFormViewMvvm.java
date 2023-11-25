package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormMeetingsWithActorsFormViewMvvm {

    interface View extends FormMeetingsWithActorsFormBaseViewMvvm.View {

        void showDatePicker(AppDate date, int viewId);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        MeetingsWithActorsForm getMeetingsWithActorsForm();

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day, int viewId);
    }
}
