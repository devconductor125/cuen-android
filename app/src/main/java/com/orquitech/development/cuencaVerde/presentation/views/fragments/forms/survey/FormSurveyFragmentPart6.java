//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart6Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FormSurveyFragmentPart6 extends FormSurveyBaseFragment implements AdapterView.OnItemSelectedListener {

    public static FormSurveyFragmentPart6 getInstance(Bundle data) {
        FormSurveyFragmentPart6 fragment = new FormSurveyFragmentPart6();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_survey_part6, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormSurveyPart6Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = ((FragmentFormSurveyPart6Binding) binding).emailSpinner;
        spin.setOnItemSelectedListener(this);

        BaseActivity.getInstacnce().apiManager.getAllUsers()
                .subscribe(usersResponse -> {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Stuff that updates the UI
                            List<String> emails = new ArrayList<>();
                            for (User user :  usersResponse)
                            {
                                emails.add(user.email);
                            }
                            ArrayAdapter a1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, emails);
                            a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spin.setAdapter(a1);
                        }
                    });
                });
        return binding.getRoot();
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormSurveyPart6Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormSurveyPart6Binding) binding).setViewModel(viewModel);
        if (viewModel.getSurvey().isNew()) {
            ((FragmentFormSurveyPart6Binding) binding).mainToolbar.setTitle(getString(R.string.new_form));
        }
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_SURVEY_VIEW_PART_6;
    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(6);
    }

    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method
    @Override
    public void onItemSelected(AdapterView<?> arg0,
                               View arg1,
                               int position,
                               long id)
    {

        // make toastof name of course
        // which is selected in spinner


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        // Auto-generated method stub
    }
}
