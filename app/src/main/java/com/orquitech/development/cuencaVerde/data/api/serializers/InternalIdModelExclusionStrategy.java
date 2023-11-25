package com.orquitech.development.cuencaVerde.data.api.serializers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class InternalIdModelExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Exclude.class) != null;
    }
}
