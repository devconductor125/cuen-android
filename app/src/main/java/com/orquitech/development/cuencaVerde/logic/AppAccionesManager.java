package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.orquitech.development.cuencaVerde.data.adapters.ManagerCallbackAdapter;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

public class AppAccionesManager extends BaseManager implements AccionesManager {

    public static final String AREAS = "area";
    public static final String ACCIONES = "accion";
    public static final String PUNTOS = "punto";

    private final ApiManager apiManager;
    private final ConnectivityStatusManager connectivityStatusManager;
    private final PersistenceManager persistenceManager;

    private List<Item> materials = new ArrayList<>();

    private PublishSubject<List<Item>> itemsSubject = PublishSubject.create();
    private PublishSubject<List<Item>> materialsSubject = PublishSubject.create();

    public AppAccionesManager(Bus bus, ApiManager apiManager, ConnectivityStatusManager connectivityStatusManager, PersistenceManager persistenceManager) {
        super(bus);
        this.apiManager = apiManager;
        this.connectivityStatusManager = connectivityStatusManager;
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Observable<List<Item>> getItemsSubject() {
        return itemsSubject;
    }

    @Override
    public Observable<List<Item>> getMaterialsSubject() {
        return materialsSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAccionesAreasFromService() {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getAcciones(AppAccionesManager.AREAS)
                        .onErrorReturn(ServicesUtils::getAccionesResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(areasResponse -> {
                            if (areasResponse.size() == 1 && areasResponse.get(0).getCode() > 0) {
                                publishServiceError(areasResponse.get(0));
                            } else {
                                Log.d(getClass().getSimpleName(), "Success getAcciones areas from service!");
                                List<Item> areas = MapperUtils.accionesResponseToActionsList(areasResponse);
                                persistenceManager.saveAcciones(areas);
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAccionesAreas() {
        return Observable.create(subscriber -> {
            persistenceManager.getAcciones(AppAccionesManager.AREAS, 0, 1000, new ManagerCallbackAdapter() {
                @Override
                public void onAcciones(List<Action> areas) {
                    List<Item> result = new ArrayList<>(areas);
                    itemsSubject.onNext(result);
                }
            });
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAccionesFromService() {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getAcciones(AppAccionesManager.ACCIONES)
                        .onErrorReturn(ServicesUtils::getAccionesResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(accionesResponse -> {
                            if (accionesResponse.size() == 1 && accionesResponse.get(0).getCode() > 0) {
                                publishServiceError(accionesResponse.get(0));
                                itemsSubject.onNext(new ArrayList<>());
                            } else {
                                Log.d(getClass().getSimpleName(), "Success getAcciones from service!");
                                List<Item> acciones = MapperUtils.accionesResponseToActionsList(accionesResponse);
                                itemsSubject.onNext(acciones);
                                persistenceManager.saveAcciones(acciones);
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAcciones() {
        return Observable.create(subscriber -> {
            persistenceManager.getAcciones(AppAccionesManager.ACCIONES, 0, 1000, new ManagerCallbackAdapter() {
                @Override
                public void onAcciones(List<Action> acciones) {
                    List<Item> result = new ArrayList<>(acciones);
                    itemsSubject.onNext(result);
                }
            });
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAccionesPuntoFromService() {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getAcciones(AppAccionesManager.PUNTOS)
                        .onErrorReturn(ServicesUtils::getAccionesResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(accionesResponse -> {
                            if (accionesResponse.size() == 1 && accionesResponse.get(0).getCode() > 0) {
                                publishServiceError(accionesResponse.get(0));
                            } else {
                                Log.d(getClass().getSimpleName(), "Success getAccionesPunto from service!");
                                List<Item> accionesPunto = MapperUtils.accionesResponseToActionsList(accionesResponse);
                                persistenceManager.saveAcciones(accionesPunto);
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAccionesPunto() {
        return Observable.create(subscriber -> {
            persistenceManager.getAcciones(AppAccionesManager.PUNTOS, 0, 1000, new ManagerCallbackAdapter() {
                @Override
                public void onAcciones(List<Action> accionesPunto) {
                    List<Item> result = new ArrayList<>(accionesPunto);
                    itemsSubject.onNext(result);
                }
            });
        });
    }

    @Override
    public List<Item> getAccionTypes() {
        List<Item> accionesType = new ArrayList<>();

        Action action = new Action();
        action.setId("");
        action.setTitle("Area");
        action.setAccionType(AppAccionesManager.AREAS);
        action.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        accionesType.add(action);

        Action action3 = new Action();
        action3.setId("");
        action3.setTitle("Recorrido");
        action3.setAccionType(AppAccionesManager.ACCIONES);
        action3.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        accionesType.add(action3);

        Action action2 = new Action();
        action2.setId("");
        action2.setTitle("Punto");
        action2.setAccionType(AppAccionesManager.PUNTOS);
        action2.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        accionesType.add(action2);

        return accionesType;
    }

    @Override
    public List<Item> getPointAccionTypes() {
        List<Item> accionesType = new ArrayList<>();

        Action action = new Action();
        action.setId("");
        action.setTitle("Punto");
        action.setOnMeasurement(true);
        action.setAccionType(AppAccionesManager.PUNTOS);
        action.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        accionesType.add(action);

        return accionesType;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMaterials(String actionId) {
        persistenceManager.getMaterials(0, 1000, new ManagerCallbackAdapter() {
            @Override
            public void onMaterials(List<Material> materials) {
                List<Item> result = new ArrayList<>();
                result.addAll(materials);
                AppAccionesManager.this.materials = result;
                List<Item> filteredMaterials = getMaterialsForAction(actionId, result);
                materialsSubject.onNext(filteredMaterials);
            }
        });
    }

    private List<Item> getMaterialsForAction(String actionId, List<Item> materials) {
        List<Item> result = new ArrayList<>();
        for (Item item : materials) {
            if (((Material) item).getActionIds().contains(actionId)) {
                result.add(item);
            }
        }
        return result;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getAllMaterialsFromService() {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getAllMaterials()
                        .onErrorReturn(ServicesUtils::getMaterialsResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(materialsResponse -> {
                            if (materialsResponse.size() == 1 && materialsResponse.get(0).getCode() > 0) {
                                publishServiceError(materialsResponse.get(0));
                            } else {
                                Log.d(getClass().getSimpleName(), "Success getAllMaterials from service!");
                                List<Item> materials = MapperUtils.materialsActionResponseToMaterialsList(materialsResponse);
                                persistenceManager.saveMaterials(materials);
                                this.materials = materials;
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getDependenciesFromService() {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getDependencies()
                        .onErrorReturn(ServicesUtils::getDependenciesExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(dependenciesResponse -> {
                            if (dependenciesResponse.size() > 0 && !TextUtils.isEmpty(dependenciesResponse.get(0).id)) {
                                List<Item> dependencies = MapperUtils.dependencyResponseToDependencyList(dependenciesResponse);
                                itemsSubject.onNext(dependencies);
                                persistenceManager.saveDependencies(dependencies);
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getDependencies() {
        return Observable.create(subscriber -> {
            persistenceManager.getDependencies(new ManagerCallbackAdapter() {
                @Override
                public void onDependencies(List<Dependency> dependencies) {
                    List<Item> result = new ArrayList<>();
                    result.addAll(dependencies);
                    itemsSubject.onNext(result);
                }
            });
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getPqrsTypesFromService(String dependencyId) {
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getPqrsTypes()
                        .onErrorReturn(ServicesUtils::getPqrsTypesExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(pqrsTypesResponse -> {
                            if (pqrsTypesResponse.size() > 0 && !TextUtils.isEmpty(pqrsTypesResponse.get(0).id)) {
                                List<Item> pqrsTypes = MapperUtils.pqrsTypeResponseToPqrsTypeList(pqrsTypesResponse);
                                itemsSubject.onNext(pqrsTypes);
                                persistenceManager.savePqrsTypes(pqrsTypes);
                            }
                            subscriber.onNext(true);
                        });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getPqrsTypes(String dependencyId) {
        return Observable.create(subscriber -> {
            persistenceManager.getPqrsTypes(dependencyId, new ManagerCallbackAdapter() {
                @Override
                public void onPqrsTypes(List<PQRSType> pqrsTypes) {
                    List<Item> result = new ArrayList<>();
                    result.addAll(pqrsTypes);
                    itemsSubject.onNext(result);
                }
            });
        });
    }

    @Override
    public Observable<Boolean> getOfflineData() {
        return getAccionesFromService()
                .flatMap(onAcciones -> getAccionesAreasFromService())
                .flatMap(onAccionesAreas -> getAccionesPuntoFromService())
                .flatMap(onAccionesPunto -> getAllMaterialsFromService())
                .flatMap(onMaterials -> getDependenciesFromService())
                .flatMap(onDependencies -> getPqrsTypesFromService(null))
                .map(onPqrsTypes -> true);
    }
}
