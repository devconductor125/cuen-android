package com.orquitech.development.cuencaVerde.presentation.factories;

import com.orquitech.development.cuencaVerde.presentation.views.adapters.AppBaseListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public class AppListAdapterFactory implements ListAdapterFactory {

    private ViewsFactory viewFactory;

    public AppListAdapterFactory(ViewsFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public ListAdapter getListAdapter(AppListView view) {
        return new AppBaseListAdapter(viewFactory, view);
    }
}
