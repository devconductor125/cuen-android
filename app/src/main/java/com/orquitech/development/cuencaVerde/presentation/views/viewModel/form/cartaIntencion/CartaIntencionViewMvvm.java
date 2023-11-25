package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion;

import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface CartaIntencionViewMvvm {

    interface View extends AppFormView {

    }

    interface ViewModel extends AppViewModel {

        void onBarButtonClick(int position);

        void saveCartaIntencion(CartaIntencion cartaIntencion);
    }
}
