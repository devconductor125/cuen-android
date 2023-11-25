//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardSheetForm;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormStardSheetFormPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardSheetFormActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormStardSheetFormFragmentPart1 extends FormStardSheetFormBaseFragment {

    public static FormStardSheetFormFragmentPart1 getInstance(Bundle data) {
        FormStardSheetFormFragmentPart1 fragment = new FormStardSheetFormFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_stard_sheet_form_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormStardSheetFormPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormStardSheetFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormStardSheetFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_SHEET_FORM_VIEW_PART_2;
    }

    @Override
    public void changeToView(int viewId) {
        ((StardSheetFormActivityForm) activity).changeToView(viewId, 1);
    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(1);
    }

    @Override
    protected void setMunicipalities(String[] municipalitiesArray) {
        ((FragmentFormStardSheetFormPart1Binding) binding).municipality.setData(municipalitiesArray);
    }
}
