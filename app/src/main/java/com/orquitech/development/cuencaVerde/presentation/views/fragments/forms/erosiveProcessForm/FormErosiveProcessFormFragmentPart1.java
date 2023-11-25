//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.erosiveProcessForm;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormErosiveProcessFormPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ContractorActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormErosiveProcessFormFragmentPart1 extends FormErosiveProcessFormBaseFragment {

    public static FormErosiveProcessFormFragmentPart1 getInstance(Bundle data) {
        FormErosiveProcessFormFragmentPart1 fragment = new FormErosiveProcessFormFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_erosive_process_form_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormErosiveProcessFormPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormErosiveProcessFormPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormErosiveProcessFormPart1Binding) binding).setViewModel(viewModel);
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
