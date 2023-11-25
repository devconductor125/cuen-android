package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface FormTextFieldMvvm {

    interface View extends AppView {

        String getValue();

        void setValue(String value);
    }

    interface ViewModel {

        String getValue();

        void setValue(String value);

        String getHint();

        void setHint(String hint);
    }
}
