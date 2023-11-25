package com.orquitech.development.cuencaVerde.presentation.views.interfaces;

import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;

public interface AppListView extends AppView {

    void onGotItems();

    void onListAdapterReady(ListAdapter adapter);

    void onGetItemsError();
}
