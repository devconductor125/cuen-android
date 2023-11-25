package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.location.Location;

import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface PQRSViewMvvm {

    interface View extends AppView {

        void showDependencyDialog();

        void setSubscribeAgreementVisibility(boolean shouldShow);

        void setPredioPotencialVisibility(boolean isChecked);

        void onViewModelUpdated();

        void showErrorMessage(int messageResource);

        void onSendPQRSTriggered();

        void showPQRSTypeDialog(Dependency dependency);

        void setPqrsType(PQRSType pqrsType);

        void setLocation(Location location);
    }

    interface ViewModel extends AppViewModel {

        PQRS getPqrs();

        void setPqrs(PQRS pqrs);

        void switchSubscribeAgreementVisibility(android.view.View view, boolean isChecked);

        void switchPredioPotencialVisibility(android.view.View view, boolean isChecked);

        void showDependencyPicker(android.view.View view);

        void savePQRS();

        void onSendPQRS(android.view.View view);

        void setDependency(Dependency dependency);

        void setPqrsType(PQRSType pqrsType);

        void setLocation(Location location);
    }
}
