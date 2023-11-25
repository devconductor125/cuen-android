//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart3Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SurveyActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.FormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;
import android.content.Context;
public class FormSurveyFragmentPart3 extends FormSurveyBaseFragment {

    public static FormSurveyFragmentPart3 getInstance(Bundle data) {
        FormSurveyFragmentPart3 fragment = new FormSurveyFragmentPart3();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_survey_part3, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        ((FragmentFormSurveyPart3Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormSurveyPart3Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormSurveyPart3Binding) binding).setViewModel(viewModel);
        if (viewModel.getSurvey().isNew()) {
            ((FragmentFormSurveyPart3Binding) binding).mainToolbar.setTitle(getString(R.string.new_form));
        }
    }

    @Override
    public void changeToView(int viewId) {
        ((SurveyActivityForm) activity).changeToView(viewId, 3);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_SURVEY_VIEW_PART_4;
    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(3);
    }
}
