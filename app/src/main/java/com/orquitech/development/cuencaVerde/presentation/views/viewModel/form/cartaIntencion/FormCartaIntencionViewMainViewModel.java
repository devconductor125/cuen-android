package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion;

import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormCartaIntencionViewMainViewModel extends BaseViewModel implements CartaIntencionViewMvvm.ViewModel {

    private final CartaIntencionViewMvvm.View view;

    public FormCartaIntencionViewMainViewModel(AppView appView) {
        super(appView);
        this.view = (CartaIntencionViewMvvm.View) appView;
    }

    @Override
    public void onBarButtonClick(int position) {
        int viewId = -1;
        switch (position) {
            case 0:
                viewId = AppViewsFactory.FORM_CARTA_INTENCION_MAIN_VIEW_PART_1;
                break;
        }
        this.view.changeToView(viewId, position);
    }

    @Override
    public void saveCartaIntencion(CartaIntencion cartaIntencion) {
        prediosManager.saveCartaIntencion(cartaIntencion);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
