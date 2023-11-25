package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.ListHeader;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class ListHeaderItemViewModel implements ListHeaderItemViewMvvm.ViewModel {

    private final ListHeaderItemViewMvvm.View view;
    private final ListHeader listHeader;
    private final ObservableField<String> text;

    public ListHeaderItemViewModel(AppView view, Item item) {
        this.view = (ListHeaderItemViewMvvm.View) view;
        this.listHeader = ((ListHeader) item);
        this.text = new ObservableField<>();

        this.text.set(this.listHeader.getText());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String getText() {
        return text.get();
    }
}
