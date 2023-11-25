package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class OfflineDialogViewModel extends BaseViewModel implements OfflineDialogMvvm.ViewModel {

    private final OfflineDialogMvvm.View view;
    private boolean isGettingOfflineData;

    public OfflineDialogViewModel(AppView view) {
        super(view);
        this.view = (OfflineDialogMvvm.View) view;
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        /*disposables.add(accionesManager.getItemsSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::onGotOfflineData)
                .subscribe());*/

        //appComponent.proceduresManager().getAllTasks();
        //appComponent.accionesManager().getAccionesAreas();
        //appComponent.accionesManager().getAcciones();
        //appComponent.accionesManager().getAccionesPunto();
        //appComponent.accionesManager().getAllMaterials();
    }

    private void onGotOfflineData() {
        view.onGotOfflineData();
    }

    @Override
    public boolean isGettingOfflineData() {
        return isGettingOfflineData;
    }

    @Override
    public void getOfflineData() {

    }
}
