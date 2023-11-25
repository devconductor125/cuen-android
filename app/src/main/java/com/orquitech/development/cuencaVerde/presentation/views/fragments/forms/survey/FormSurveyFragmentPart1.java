//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SurveyActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.DatePickerFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormSurveyFragmentPart1 extends FormSurveyBaseFragment {

    public static FormSurveyFragmentPart1 getInstance(Bundle data) {
        FormSurveyFragmentPart1 fragment = new FormSurveyFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_survey_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormSurveyPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormSurveyPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void showDatePicker(AppDate date) {
        Bundle bundle = new Bundle();
        if (date != null) {
            bundle.putString(DatePickerFragment.YEAR, date.getYear());
            bundle.putString(DatePickerFragment.MONTH, date.getMonth());
            bundle.putString(DatePickerFragment.DAY, date.getDay());
        }
        DatePickerFragment fragment = DatePickerFragment.getInstance(bundle);
        fragment.setListener((datePicker, newYear, newMonth, newDay) -> viewModel.setDate(newYear, (newMonth + 1), newDay));
        fragment.show(activity.getSupportFragmentManager(), DATE_PICKER);
    }

    @Override
    public void showCorrelationDialog() {
        ItemsListFragment fragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.PROPERTY_CORRELATION_PICKER, new Bundle());
        fragment.setItemsType(DialogsFactory.PROPERTY_CORRELATION_PICKER);
        fragment.show(activity.getSupportFragmentManager(), LIST_OF_PROPERTY_CORRELATIONS);
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormSurveyPart1Binding) binding).setViewModel(viewModel);
        if (viewModel.getSurvey().isNew()) {
            ((FragmentFormSurveyPart1Binding) binding).mainToolbar.setTitle(getString(R.string.new_form));
        }
    }

    @Override
    public void changeToView(int viewId) {
        ((SurveyActivityForm) activity).changeToView(viewId, 1);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_SURVEY_VIEW_PART_2;
    }

    public void setPropertyCorrelation(String propertyCorrelation) {
        viewModel.setPropertyCorrelation(propertyCorrelation);
    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(1);
    }
}
