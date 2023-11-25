//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.module;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppListAdapterFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ListAdapterFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    @Singleton
    @Provides
    public ViewsFactory viewFactory(App app) {
        return new AppViewsFactory(app);
    }

    @Singleton
    @Provides
    public ListAdapterFactory listAdapterFactory(ViewsFactory viewFactory) {
        return new AppListAdapterFactory(viewFactory);
    }

    @Singleton
    @Provides
    public ViewModelsFactory viewModelFactory(Bus bus) {
        return new AppViewModelsFactory(bus);
    }
}
