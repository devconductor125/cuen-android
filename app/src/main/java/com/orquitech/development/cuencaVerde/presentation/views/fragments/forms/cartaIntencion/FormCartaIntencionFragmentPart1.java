//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.cartaIntencion;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormCartaIntencionPart1Binding;
import com.orquitech.development.cuencaVerde.databinding.FragmentFormSurveyPart2Binding;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FormCartaIntencionFragmentPart1 extends FormCartaIntencionBaseFragment implements AdapterView.OnItemSelectedListener {

    public static FormCartaIntencionFragmentPart1 getInstance(Bundle data) {
        FormCartaIntencionFragmentPart1 fragment = new FormCartaIntencionFragmentPart1();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_carta_intencion_part1, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentFormCartaIntencionPart1Binding) binding).mainToolbar.setListener(new ToolBarListenerAdapter() {
            @Override
            public void onToolbarLeftIconClick() {
                activity.onBackPressed();
            }
        });
        initList();
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = ((FragmentFormCartaIntencionPart1Binding) binding).emailSpinner;
        spin.setOnItemSelectedListener(this);

        String[] municipioData = new String[]{"Seleccione un municipio", "Envigado", "El Retiro", "La Ceja", "La Union", "Abejorral", "Belmira", "San Pedro De Los Milagros", "Entrerrios", "Don Matias", "Santa Rosa De Osos", "San Jose De La Montaña", "Yarumal", "EL CARMEN", "Guarne", "Marinilla", "La Ceja", "el Retiro", "Llanogrande", "Carmen de Viboral", "Area Metropolitana - Barbosa", "Area Metropolitana - Copacabana", "Area Metropolitana - Bello", "Area Metropolitana - Medellin", "Area Metropolitana - Girardota", "Area Metropolitana - Caldas", "Area Metropolitana - La Estrella", "Area Metropolitana - Itagüí", "Area Metropolitana - Envigado", "Area Metropolitana - Sabaneta"};

        String[] embalseData = new String[] {"Seleccione un embalse", "La Fe", "Rio Grande", "Rio Aburra Medellin", "Valle de Sin nicolas"};

        Spinner municipioSpin = ((FragmentFormCartaIntencionPart1Binding) binding).municipality;
        municipioSpin.setOnItemSelectedListener(this);
        ArrayAdapter municipioAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, municipioData);
        municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpin.setAdapter(municipioAdapter);

        Spinner embalseSpinner = ((FragmentFormCartaIntencionPart1Binding) binding).embalse;
        embalseSpinner.setOnItemSelectedListener(this);
        ArrayAdapter embalseAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, embalseData);
        embalseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        embalseSpinner.setAdapter(embalseAdapter);

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

    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method
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
            case R.id.municipality:
                Log.d("this is survey", selectedItem);
                setMunicipioDropdown(selectedItem);
                break;
            case R.id.embalse:
                Log.d("this is survey", selectedItem);
                setEmbalseDropdown(selectedItem);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        // Auto-generated method stub
    }

    private void initList() {
        RecyclerView.LayoutManager layoutManager = LayoutManagerUtil.getGridListLayoutManager(getContext(), savedInstanceState, LIST_STATE);
        ((FragmentFormCartaIntencionPart1Binding) binding).programsList.setLayoutManager(layoutManager);
        ((FragmentFormCartaIntencionPart1Binding) binding).programsList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void setViewModel() {
        ((FragmentFormCartaIntencionPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
        ((FragmentFormCartaIntencionPart1Binding) binding).setViewModel(viewModel);
    }

    @Override
    public void showCorrelationDialog() {
        ItemsListFragment fragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.PROPERTY_CORRELATION_PICKER, new Bundle());
        fragment.setItemsType(DialogsFactory.PROPERTY_CORRELATION_PICKER);
        fragment.show(activity.getSupportFragmentManager(), LIST_OF_PROPERTY_CORRELATIONS);
    }

    public void setPropertyCorrelation(String propertyCorrelation) {
        viewModel.setPropertyCorrelation(propertyCorrelation);
    }

//    @Override
//    protected void setMunicipalities(String[] municipalitiesArray) {
//        ((FragmentFormCartaIntencionPart1Binding) binding).municipality.setData(municipalitiesArray);
//    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        ((FragmentFormCartaIntencionPart1Binding) binding).programsList.setAdapter((RecyclerView.Adapter) adapter);
    }
}
