package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.presentation.factories.Item;

public abstract class BaseItem implements Parcelable, Item {

    protected int type;
    protected String id;

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCleanedId() {
        String id;
        if (getId().indexOf("_") > 0) {
            id = getId().substring(0, getId().indexOf("_"));
        } else {
            id = getId();
        }
        return id;
    }
}
