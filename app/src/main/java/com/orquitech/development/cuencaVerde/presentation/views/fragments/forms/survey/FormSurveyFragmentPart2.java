//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormCartaIntencionPart1Binding;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart2Binding;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart6Binding;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SurveyActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.AutoCompleteFormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormSurveyFragmentPart2 extends FormSurveyBaseFragment implements AdapterView.OnItemSelectedListener {


    public static FormSurveyFragmentPart2 getInstance(Bundle data) {
        FormSurveyFragmentPart2 fragment = new FormSurveyFragmentPart2();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_survey_part2, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormSurveyPart2Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });

        String[] embalseData = new String[] {"Seleccione un embalse","La Fe", "Rio Grande", "Rio Aburra Medellin", "Valle de Sin nicolas"};
        List<String> embalseList = new ArrayList<>(Arrays.asList(embalseData));

        Spinner propertyReservoir = ((FragmentFormSurveyPart2Binding) binding).propertyReservoir;
        propertyReservoir.setOnItemSelectedListener(this);

        ArrayAdapter embalseAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, embalseList);
        embalseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertyReservoir.setAdapter(embalseAdapter);

        String[] sectorData = new String[] {"Seleccione un sector", "Urbano", "Rural"};
        List<String> sectorList = new ArrayList<>(Arrays.asList(sectorData));

        Spinner propertySector = ((FragmentFormSurveyPart2Binding) binding).propertySector;
        propertySector.setOnItemSelectedListener(this);
        ArrayAdapter sectorAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, sectorList);
        sectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertySector.setAdapter(sectorAdapter);

        String[] municipioData = new String[]{"Seleccione un municipio", "Envigado", "El Retiro", "La Ceja", "La Union", "Abejorral", "Belmira", "San Pedro De Los Milagros", "Entrerrios", "Don Matias", "Santa Rosa De Osos", "San Jose De La Montaña", "Yarumal", "EL CARMEN", "Guarne", "Marinilla", "La Ceja", "el Retiro", "Llanogrande", "Carmen de Viboral", "Area Metropolitana - Barbosa", "Area Metropolitana - Copacabana", "Area Metropolitana - Bello", "Area Metropolitana - Medellin", "Area Metropolitana - Girardota", "Area Metropolitana - Caldas", "Area Metropolitana - La Estrella", "Area Metropolitana - Itagüí", "Area Metropolitana - Envigado", "Area Metropolitana - Sabaneta"};

        Spinner municipioSpin = ((FragmentFormSurveyPart2Binding) binding).municipality;
        municipioSpin.setOnItemSelectedListener(this);
        ArrayAdapter municipioAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, municipioData);
        municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpin.setAdapter(municipioAdapter);

        return binding.getRoot();


    }



    @Override
    protected void setViewModel() {
        ((FragmentFormSurveyPart2Binding) binding).setViewModel(viewModel);
    }


    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormSurveyPart2Binding) binding).setViewModel(viewModel);
        if (viewModel.getSurvey().isNew()) {
            ((FragmentFormSurveyPart2Binding) binding).mainToolbar.setTitle(getString(R.string.new_form));
        }
    }

    @Override
    public void changeToView(int viewId) {
        ((SurveyActivityForm) activity).changeToView(viewId, 2);
    }

    @Override
    public int getNextViewId() {
        return AppViewsFactory.FORM_SURVEY_VIEW_PART_3;
    }

//    @Override
//    protected void setMunicipalities(String[] municipalitiesArray) {
//        ((FragmentFormSurveyPart2Binding) binding).municipality.setData(municipalitiesArray);
//    }

    @Override
    public boolean canGoToNextScreen() {
        return viewModel.canGoToNextScreen(2);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0,
                               View arg1,
                               int position,
                               long id)
    {
        String selectedItem = arg0.getItemAtPosition(position).toString();
        Spinner selectedSpinner = (Spinner) arg0;
        int selectedId = selectedSpinner.getId();
        switch (selectedId) {
            case R.id.propertySector:
                setPropertySectorDropdown(selectedItem);
                break;
            case R.id.property_reservoir:
                setPropertyReservDropdown(selectedItem);
                break;
            case R.id.municipality:
                setMunicipioDropdown(selectedItem);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        // Auto-generated method stub
    }

}
