package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormMeetingsWithActorsFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        MeetingsWithActorsForm getMeetingsWithActorsForm();

        void onMunicipalities(List<Municipality> municipalities);
    }
}
