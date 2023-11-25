package com.orquitech.development.cuencaVerde.presentation.factories;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface ItemView {

    void bind(Item item, int position);

    void setParentView(AppListView parentView);
}
