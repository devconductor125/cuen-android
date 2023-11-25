package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey;

import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface SurveyViewMvvm {

    interface View extends AppView {

        void changeToView(int viewId, int newPosition);
    }

    interface ViewModel extends AppViewModel {

        void onBarButtonClick(int position);

        void saveSurvey(Survey survey);
    }
}
