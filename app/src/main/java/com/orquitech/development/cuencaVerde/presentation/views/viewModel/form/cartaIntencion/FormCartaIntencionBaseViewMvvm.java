package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseListViewMvvm;

import java.util.List;

public interface FormCartaIntencionBaseViewMvvm {

    interface View extends FormBaseListViewMvvm.View {

        CartaIntencion getCartaIntencion();

        void onMunicipalities(List<Municipality> municipalities);

        String getPredioPotencialId(long remoteId);
    }
}
