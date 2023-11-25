package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey;

import android.location.Location;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormSurveyBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        Survey getSurvey();

        void setSurveyLocation(Location location);

        void onMunicipalities(List<Municipality> municipalities);

        String getPredioPotencialId(long remoteId);
    }
}
