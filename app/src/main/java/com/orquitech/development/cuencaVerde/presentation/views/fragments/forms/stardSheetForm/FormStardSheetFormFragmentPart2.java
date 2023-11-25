//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardSheetForm;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormStardSheetFormPart2Binding;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormStardSheetFormFragmentPart2 extends FormStardSheetFormBaseFragment {

    public static FormStardSheetFormFragmentPart2 getInstance(Bundle data) {
        FormStardSheetFormFragmentPart2 fragment = new FormStardSheetFormFragmentPart2();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_stard_sheet_form_part2, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormStardSheetFormPart2Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormStardSheetFormPart2Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormStardSheetFormPart2Binding) binding).setViewModel(viewModel);
    }
}
