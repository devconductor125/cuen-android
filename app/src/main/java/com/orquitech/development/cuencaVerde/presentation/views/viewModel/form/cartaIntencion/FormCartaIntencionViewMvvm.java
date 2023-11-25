package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormCartaIntencionViewMvvm {

    interface View extends FormCartaIntencionBaseViewMvvm.View {

        void showCorrelationDialog();

        void showDatePicker(AppDate date);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        void setCartaIntención(PredioPotencial predioPotencial);

        CartaIntencion getCartaIntencion();

        CartaIntencion getCartaIntencionForSave();

        String getPredioPotencialId();

        void setCartaIntención(CartaIntencion cartaIntencion);

        void showCorrelationDialog(android.view.View view);

        void setPropertyCorrelation(String propertyCorrelation);

        void onTakePicture(android.view.View view);

        void onSendCartaIntencion(android.view.View view);

        void onSendCartaIntencionCancelled();

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day);

        void setPrograms();
    }
}
