package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class TracksViewModel extends BaseViewModel implements TracksViewMvvm.ViewModel {

    private final TracksViewMvvm.View view;

    public TracksViewModel(AppView view) {
        super(view);
        this.view = (TracksViewMvvm.View) view;
    }

    @Override
    public void getPredios() {

    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        /*case RxBus.LOCATION_MANAGER_CONNECTED:
                            view.getLocation();
                            break;*/
                    }
                })
                .subscribe());
    }
}
