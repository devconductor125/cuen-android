//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.meetingsWithActorsForm;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormMeetingsWithActorsFormPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ContractorActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormMeetingsWithActorsFormFragmentPart1 extends FormMeetingsWithActorsFormBaseFragment {

    public static FormMeetingsWithActorsFormFragmentPart1 getInstance(Bundle data) {
        FormMeetingsWithActorsFormFragmentPart1 fragment = new FormMeetingsWithActorsFormFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_meetings_with_actors_form_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormMeetingsWithActorsFormPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormMeetingsWithActorsFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormMeetingsWithActorsFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_SHEET_FORM_VIEW_PART_2;
    }

    @Override
    public void changeToView(int viewId) {
        ((ContractorActivityForm) activity).changeToView(viewId, 1);
    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(1);
    }

    @Override
    protected void setMunicipalities(String[] municipalitiesArray) {
        ((FragmentFormMeetingsWithActorsFormPart1Binding) binding).municipality.setData(municipalitiesArray);
    }
}
