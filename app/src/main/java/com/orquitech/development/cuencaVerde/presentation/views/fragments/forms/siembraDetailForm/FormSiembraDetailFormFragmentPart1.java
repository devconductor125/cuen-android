//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.siembraDetailForm;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSiembraDetailFormPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ContractorActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormSiembraDetailFormFragmentPart1 extends FormSiembraDetailFormBaseFragment {

    public static FormSiembraDetailFormFragmentPart1 getInstance(Bundle data) {
        FormSiembraDetailFormFragmentPart1 fragment = new FormSiembraDetailFormFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_siembra_detail_form_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormSiembraDetailFormPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormSiembraDetailFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormSiembraDetailFormPart1Binding) binding).setViewModel(viewModel);
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
}
