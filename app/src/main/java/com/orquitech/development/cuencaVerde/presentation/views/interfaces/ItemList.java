package com.orquitech.development.cuencaVerde.presentation.views.interfaces;

import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.factories.Item;

public interface ItemList {

    void onItemClicked(Item item);

    void onItemClicked(View sharedElement, Item item);
}
