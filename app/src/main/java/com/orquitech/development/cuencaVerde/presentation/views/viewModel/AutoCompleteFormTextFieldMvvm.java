package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.widget.ArrayAdapter;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface AutoCompleteFormTextFieldMvvm {

    interface View extends AppView {

        String getValue();

        void setValue(String value);

        void setAutoCompleteAdapter(ArrayAdapter<String> adapter);
    }

    interface ViewModel {

        String getValue();

        void setValue(String value);

        String getHint();

        void setHint(String hint);

        void setAutoCompleteData(String[] data);
    }
}
