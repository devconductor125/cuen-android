//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.component;

import com.orquitech.development.cuencaVerde.dependencyInjection.scope.AppScope;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface ServicesComponent extends AppComponent {

    void inject(UserLocationService userLocationService);
}
