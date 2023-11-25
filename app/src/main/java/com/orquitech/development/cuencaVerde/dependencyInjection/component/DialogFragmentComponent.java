//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.component;

import com.orquitech.development.cuencaVerde.dependencyInjection.scope.AppScope;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ConfirmationDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsWithCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMarkerCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMonitorMaintenanceCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.OfflineDialogFragment;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface DialogFragmentComponent extends AppComponent {

    void inject(MapMonitorMaintenanceCommentFragment mapMonitorMaintenanceCommentFragment);

    void inject(MapMarkerCommentFragment mapMarkerCommentFragment);

    void inject(FeatureDetailsFragment featureDetailsFragment);

    void inject(FeatureDetailsWithCommentFragment featureDetailsWithCommentFragment);

    void inject(OfflineDialogFragment offlineDialogFragment);

    void inject(ConfirmationDialogFragment confirmationDialogFragment);
}