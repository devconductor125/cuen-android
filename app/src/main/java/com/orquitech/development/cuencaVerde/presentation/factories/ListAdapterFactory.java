package com.orquitech.development.cuencaVerde.presentation.factories;

import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface ListAdapterFactory {

    ListAdapter getListAdapter(AppListView view);
}
