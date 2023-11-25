package com.orquitech.development.cuencaVerde.logic;

import android.os.Bundle;

import io.reactivex.Observable;

public interface Bus {

    void publish(Bundle bundle);

    void publishBehavior(Bundle bundle);

    Observable<Bundle> getObservable();

    Observable<Bundle> getBehaviorObservable();
}
