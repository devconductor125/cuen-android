package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.LogInCredentials;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface LogInViewMvvm {

    interface View extends AppView {

        void onLogInTriggered();

        void onLogInSuccess();

        void onInvalidUsername();

        void onInvalidPassword();

        void onLogInError();
    }

    interface ViewModel extends AppViewModel {

        LogInCredentials getLogInCredentials();

        void setLogInCredentials(LogInCredentials logInCredentials);

        void logIn(android.view.View view);

        void onLogInCancelled();
    }
}
