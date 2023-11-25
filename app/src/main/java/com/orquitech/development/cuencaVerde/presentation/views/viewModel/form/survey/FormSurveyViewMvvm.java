package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormSurveyViewMvvm {

    interface View extends FormSurveyBaseViewMvvm.View {

        void onViewModelUpdated();

        void showDatePicker(AppDate date);

        void showCorrelationDialog();

        void onBackPressed();

        void changeToView(int viewId);

        int getNextViewId();

        void initLocationRequest();
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        void setPredioPotencial(PredioPotencial predioPotencial);

        Survey getSurvey();

        String getPredioPotencialId();

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day);

        void showCorrelationDialog(android.view.View view);

        void setPropertyCorrelation(String propertyCorrelation);

        int customTitleResource();
    }
}
