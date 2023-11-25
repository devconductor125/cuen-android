package com.orquitech.development.cuencaVerde.logic;

import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class RxBus implements Bus {

    public static final String CODE = "code";
    public static final String PAYLOAD = "rxPayload";
    public static final String TASK = "task";
    public static final String PENDING_MAP_SAVE = "pendingMapSave";

    public static final int LOCATION_MANAGER_CONNECTED = 100;
    public static final int LOCATION_CHANGED = 200;
    public static final int LOCATION_CHANGED_NO_NEW_POINT = 210;
    public static final int LOCATION_HIGH_ACCURACY_ERROR = 211;
    public static final int LOCATION_LOW_ACCURACY_ERROR = 212;
    public static final int SERVICE_ERROR = 300;
    public static final int ON_LOCATION_RESULT = 400;
    public static final int PAUSE_TRACK = 500;
    public static final int EMPTY_MAP_FEATURE = 510;
    public static final int RESUME_TRACK = 550;
    public static final int ON_FILES_RESULT = 600;
    public static final int ON_IMAGES_RESULT = 700;
    public static final int TEXT_FIELD_VALIDATION = 800;
    public static final int IN_PHOTO_FOR_DELETION_STATE = 810;

    private PublishSubject<Bundle> publishSubject = PublishSubject.create();
    private BehaviorSubject<Bundle> behaviorSubject = BehaviorSubject.create();

    @Override
    public void publish(Bundle bundle) {
        publishSubject.onNext(bundle);
    }

    @Override
    public void publishBehavior(Bundle bundle) {
        behaviorSubject.onNext(bundle);
    }

    @Override
    public Observable<Bundle> getObservable() {
        return publishSubject;
    }

    @Override
    public Observable<Bundle> getBehaviorObservable() {
        return behaviorSubject;
    }
}
