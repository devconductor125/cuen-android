//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.predioFollowUp;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormPredioFollowUpPart1Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

public class FormPredioFollowUpFragmentPart1 extends FormPredioFollowUpBaseFragment {

    public static FormPredioFollowUpFragmentPart1 getInstance(Bundle data) {
        FormPredioFollowUpFragmentPart1 fragment = new FormPredioFollowUpFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_predio_follow_up_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormPredioFollowUpPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormPredioFollowUpPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormPredioFollowUpPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2;
    }

    @Override
    protected void setMunicipalities(String[] municipalitiesArray) {
        ((FragmentFormPredioFollowUpPart1Binding) binding).municipality.setData(municipalitiesArray);
    }
}
