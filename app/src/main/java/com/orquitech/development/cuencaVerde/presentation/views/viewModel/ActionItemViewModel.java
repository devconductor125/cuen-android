package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.BaseDialogListItem;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class ActionItemViewModel implements ActionItemViewMvvm.ViewModel {

    private final BaseDialogListItem item;
    private final Bus bus;
    private final ActionItemViewMvvm.View view;

    public ActionItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (ActionItemViewMvvm.View) view;
        this.bus = bus;
        this.item = (BaseDialogListItem) item;
    }

    @Override
    public String getTitle() {
        String result = "";
        if (item != null) {
            result = item.getTitle();
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        this.view.onClick(item);
    }

    @Override
    public String getBulletColor() {
        String result = "";
        if (item instanceof Action) {
            result = ((Action) item).getColor();
        }
        return result;
    }
}
