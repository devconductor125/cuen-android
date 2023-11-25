package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface CustomDialogMvvm {

    interface View extends AppView {

        void onButtonOne();

        void onButtonTwo();

        String getString(String content, int resource);
    }

    interface ViewModel {

        void onButtonOne(android.view.View view);

        void onButtonTwo(android.view.View view);
    }
}
