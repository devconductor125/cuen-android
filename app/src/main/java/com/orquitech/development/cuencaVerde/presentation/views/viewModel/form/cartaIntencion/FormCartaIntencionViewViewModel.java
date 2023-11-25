package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.MainActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.CartaIntencionActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseSingleListFormViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormCartaIntencionViewViewModel extends BaseSingleListFormViewModel implements FormCartaIntencionViewMvvm.ViewModel {

    private FormCartaIntencionViewMvvm.View view;
    private PredioPotencial predioPotencial;
    private CartaIntencion cartaIntencion;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormCartaIntencionViewViewModel(AppView view) {
        super(view);
        this.view = (FormCartaIntencionViewMvvm.View) view;
        monitorAndMaintenanceDisposable = new CompositeDisposable();
    }

    @Override
    public void setCartaIntención(PredioPotencial predioPotencial) {
        this.predioPotencial = predioPotencial;
        getCartaIntencionFromManager();
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        monitorAndMaintenanceDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();

        subscribeToItemsListObservable(projectsManager.getProgramsObservable(), AppViewsFactory.CHECKBOX_ITEM_VIEW);
        projectsManager.getPrograms();
    }

    @Override
    public CartaIntencion getCartaIntencion() {
        return cartaIntencion;
    }

    @Override
    public CartaIntencion getCartaIntencionForSave() {
        List<Item> items = adapter.getItems();
        List<Map> programs = new ArrayList<>();
        for (Item item : items) {
            Map<String, Serializable> program = new HashMap<>();
            program.put("id", ((Program) item).getId());
            program.put("name", ((Program) item).getName());
            program.put("checked", ((Program) item).isChecked());
            programs.add(program);
        }
        cartaIntencion.setPrograms(programs);
        return cartaIntencion;
    }

    @Override
    public String getPredioPotencialId() {
        return this.view.getPredioPotencialId(predioPotencial.getRemoteId());
    }

    @Override
    public void setCartaIntención(CartaIntencion cartaIntencion) {
        if (cartaIntencion.getPredioPotencialId() < 0) {
            cartaIntencion.setAsNew();
        }
        cartaIntencion.setPropertyName(predioPotencial.getName());
        this.cartaIntencion = cartaIntencion;
        this.cartaIntencion.setPredioPotencialId(Integer.parseInt(this.predioPotencial.getId() != null ? this.predioPotencial.getId() : "0"));
        view.onViewModelUpdated();
    }

    @Override
    public void onBackPressed(View view) {
        this.view.onBackPressed();
    }

    @Override
    public void onNextStep(View view) {
        this.view.changeToView(this.view.getNextViewId());
    }

    @Override
    public void onSendCartaIntencion(View view) {
        String value = ((Spinner)view.getRootView().findViewById(R.id.email_spinner)).getSelectedItem().toString();;
        cartaIntencion.setReceiverEmail(value);

        if (!cartaIntencion.isValid()) {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
            return;
        }
        if (connectivityStatusManager.isConnected()) {
            if (predioPotencial.getRemoteId() > 0) {
                prediosManager.sendCartaIntencion(predioPotencial, cartaIntencion);
                this.view.onSendParcelableTriggered();
            } else {
                this.view.showErrorMessage(R.string.missing_remote_id);
            }
        } else {
            this.view.showErrorMessage(R.string.cant_send_task_message);
        }
    }

    @Override
    public void onTakePicture(View view) {
        if (!cartaIntencion.isValid()) {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, cartaIntencion);
        cartaIntencion.setId(cartaIntencion.getPropertyName());
        try {
            ((CartaIntencionActivityForm) view.getContext()).changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSendCartaIntencionCancelled() {
        monitorAndMaintenanceDisposable.clear();
    }

    private void getCartaIntencionFromManager() {
        monitorAndMaintenanceDisposable.add(prediosManager.getCartaIntencionObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setCartaIntención)
                .subscribe());
        prediosManager.getCartaIntencionByPredioPotencialId(predioPotencial.getId());
    }

    @Override
    public void showCorrelationDialog(View view) {
        this.view.showCorrelationDialog();
    }

    @Override
    public void setPropertyCorrelation(String propertyCorrelation) {
        this.cartaIntencion.setPropertyCorrelation(propertyCorrelation);
        view.onViewModelUpdated();
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = cartaIntencion.getPropertyVisitDate();
        this.view.showDatePicker(date);
    }

    @Override
    public void setDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        cartaIntencion.setPropertyVisitDate(date);
        view.onViewModelUpdated();
    }

    @Override
    public void refreshView(View view) {
        this.view.onViewModelUpdated();
    }

    @Override
    public void setPrograms() {
        List<Map> programs = cartaIntencion.getPrograms();

        List<Item> items = new ArrayList<>(adapter.getItems());
        adapter.removeAllItems();

        List<Item> newItems = new ArrayList<>();

        if (programs != null && programs.size() > 0) {
            for (Item item : items) {
                for (Map map : programs) {
                    if (map.get("id").equals(((Program) item).getId())) {
                        ((Program) item).setChecked((Boolean) map.get("checked"));
                        newItems.add(item);
                    }
                }
            }
            adapter.setItems(newItems);
        } else {
            adapter.setItems(items);
        }
    }
}
