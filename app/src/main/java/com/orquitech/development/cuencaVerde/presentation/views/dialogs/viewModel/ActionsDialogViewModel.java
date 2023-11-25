package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ActionsDialogViewModel extends BaseViewModel implements ActionsDialogMvvm.ViewModel {

    private final ActionsDialogMvvm.View view;
    private ListAdapter adapter;

    public ActionsDialogViewModel(AppView view) {
        super(view);
        this.view = (ActionsDialogMvvm.View) view;
        subscribeToObservables();
        getItems(((ActionsDialogMvvm.View) view).getItemsType());
    }

    private void subscribeToObservables() {
        disposables.add(accionesManager.getItemsSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::initAdapterAndSendToView)
                .subscribe());
    }

    private void initAdapterAndSendToView(List<Item> items) {
        if (adapter == null) {
            adapter = listAdapterFactory.getListAdapter(view);
            adapter.setItems(items);
            view.onListAdapterReady(adapter);
        }
    }

    public void getItems(int itemsType) {
        Parcelable parcelable;
        switch (itemsType) {
            case DialogsFactory.DEPENDENCY_PICKER:
                accionesManager.getDependencies().subscribe();
                break;
            case DialogsFactory.PQRS_TYPE_PICKER:
                parcelable = view.getPayload();
                if (parcelable != null && parcelable instanceof Dependency) {
                    accionesManager.getPqrsTypes(((Dependency) parcelable).getId()).subscribe();
                }
                break;
            case DialogsFactory.MAP_AREAS:
                accionesManager.getAccionesAreas().subscribe();
                break;
            case DialogsFactory.MAP_ACTIONS:
                accionesManager.getAcciones().subscribe();
                break;
            case DialogsFactory.MAP_ACTIONS_POINTS:
                accionesManager.getAccionesPunto().subscribe();
                break;
            case DialogsFactory.MAP_MATERIALS:
                parcelable = view.getPayload();
                if (parcelable != null && parcelable instanceof Action) {
                    accionesManager.getMaterials(((Action) parcelable).getId());
                }
                break;
            case DialogsFactory.PROPERTY_CORRELATION_PICKER:
                Action correlation1 = new Action();
                correlation1.setTitle("Propietario");
                correlation1.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                Action correlation2 = new Action();
                correlation2.setTitle("Poseedor");
                correlation2.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                Action correlation3 = new Action();
                correlation3.setTitle("Representante legal");
                correlation3.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                Action correlation4 = new Action();
                correlation4.setTitle("Arrendatario");
                correlation4.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                Action correlation5 = new Action();
                correlation5.setTitle("Tenedor");
                correlation5.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                Action correlation6 = new Action();
                correlation6.setTitle("Otro");
                correlation6.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

                List<Item> items = new ArrayList<>();

                items.add(correlation1);
                items.add(correlation2);
                items.add(correlation3);
                items.add(correlation4);
                items.add(correlation5);
                items.add(correlation6);

                initAdapterAndSendToView(items);
                break;
            case DialogsFactory.ACTION_TYPE:
                List<Item> accionTypes = accionesManager.getAccionTypes();
                initAdapterAndSendToView(accionTypes);
                break;
            case DialogsFactory.POINT_ACTION_TYPE:
                List<Item> pointAccionTypes = accionesManager.getPointAccionTypes();
                initAdapterAndSendToView(pointAccionTypes);
                break;
        }
    }
}
