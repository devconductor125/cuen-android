//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
