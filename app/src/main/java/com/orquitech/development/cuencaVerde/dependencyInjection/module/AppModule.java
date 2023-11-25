//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.module;

import com.orquitech.development.cuencaVerde.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App app() {
        return app;
    }
}
