package com.orquitech.development.cuencaVerde.logic;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;

public abstract class BaseManager {

    protected final Bus bus;

    public BaseManager(Bus bus) {
        this.bus = bus;
    }

    protected void publishServiceError(CuencaVerdeResponse response) {
        Bundle bundle = new Bundle();
        bundle.putInt(RxBus.CODE, RxBus.SERVICE_ERROR);
        bundle.putInt(RxBus.PAYLOAD, response.getCode());
        bus.publish(bundle); // TODO Removed for production
    }
}
