package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey;

import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormSurveyMainViewViewModel extends BaseViewModel implements SurveyViewMvvm.ViewModel {

    private final SurveyViewMvvm.View view;

    public FormSurveyMainViewViewModel(AppView view) {
        super(view);
        this.view = (SurveyViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_1;
                break;
            case 1:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_2;
                break;
            case 2:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_3;
                break;
            case 3:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_4;
                break;
            case 4:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_5;
                break;
            case 5:
                viewId = AppViewsFactory.FORM_SURVEY_VIEW_PART_6;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveSurvey(Survey survey) {
        projectsManager.saveSurvey(survey);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
