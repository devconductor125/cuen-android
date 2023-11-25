package com.orquitech.development.cuencaVerde.logic;

import com.orquitech.development.cuencaVerde.presentation.factories.Item;

import java.util.List;

import io.reactivex.Observable;

public interface AccionesManager {

    Observable<List<Item>> getItemsSubject();

    Observable<List<Item>> getMaterialsSubject();

    Observable<Boolean> getAccionesAreasFromService();

    Observable<Boolean> getAccionesAreas();

    Observable<Boolean> getAccionesFromService();

    Observable<Boolean> getAcciones();

    List<Item> getAccionTypes();

    List<Item> getPointAccionTypes();

    void getMaterials(String actionId);

    Observable<Boolean> getAllMaterialsFromService();

    Observable<Boolean> getAccionesPunto();

    Observable<Boolean> getAccionesPuntoFromService();

    Observable<Boolean> getDependenciesFromService();

    Observable<Boolean> getDependencies();

    Observable<Boolean> getPqrsTypesFromService(String dependencyId);

    Observable<Boolean> getPqrsTypes(String dependencyId);

    Observable<Boolean> getOfflineData();
}
