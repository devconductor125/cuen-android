package com.orquitech.development.cuencaVerde.data;

public interface SerializationManager {

    String toJson(Object value);

    Object fromJson(String infoString, Class objectClass);
}
