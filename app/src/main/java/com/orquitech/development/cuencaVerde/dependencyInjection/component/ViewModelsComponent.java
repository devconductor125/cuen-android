//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.component;

import com.orquitech.development.cuencaVerde.dependencyInjection.scope.AppScope;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.ActionsDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsWithCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMarkerCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMonitorMaintenanceCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.OfflineDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AutoCompleteFormTextFieldViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseSingleListViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardHeaderViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyMainViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.FormTextFieldViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.LogInViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MainViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MapPredioPotencialViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PQRSViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotoDetailViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographyRegistryViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PredioPotencialTasksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PrediosViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SettingsViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SiembraDetailListViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskDetailViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TasksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TracksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp.FormPredioFollowUpViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp.FormPredioFollowUpViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapMonitorAndMaintenanceViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.contractSiembra.MapContractSiembraViewModel;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface ViewModelsComponent extends AppComponent {

    void inject(BaseSingleListViewModel baseSingleListViewModel);

    void inject(LogInViewModel logInViewModel);

    void inject(MapTaskViewModel mapViewModel);

    void inject(FormSiembraDetailFormViewMainViewModel mapViewModel);

    void inject(PhotoDetailViewModel photoDetailViewModel);

    void inject(BaseViewModel mapViewModel);

    void inject(MapContractSiembraViewModel mapContractSiembraViewModel);

    void inject(PhotographyRegistryViewModel photographyRegistryViewModel);

    void inject(FormTextFieldViewModel formTextFieldViewModel);

    void inject(PrediosViewModel prediosViewModel);

    void inject(FormMeetingsWithActorsFormViewMainViewModel formMeetingsWithActorsFormViewMainViewModel);

    void inject(FormMeetingsWithActorsFormViewViewModel formMeetingsWithActorsFormViewViewModel);

    void inject(FormSiembraDetailFormViewViewModel detailFormViewViewModel);

    void inject(FormContractorFormViewViewModel formContractorFormViewViewModel);

    void inject(FormContractorFormViewMainViewModel formContractorFormViewMainViewModel);

    void inject(PredioPotencialTasksViewModel predioPotencialTasksViewModel);

    void inject(FormVegetalMaintenanceViewViewModel formVegetalMaintenanceViewViewModel);

    void inject(AutoCompleteFormTextFieldViewModel autoCompleteFormTextFieldViewModel);

    void inject(OfflineDialogViewModel offlineDialogViewModel);

    void inject(FormStardSheetFormViewViewModel formStardSheetFormViewViewModel);

    void inject(FormPredioFollowUpViewViewModel formPredioFollowUpViewViewModel);

    void inject(FeatureDetailsDialogViewModel featureDetailsDialogViewModel);

    void inject(FeatureDetailsWithCommentDialogViewModel featureDetailsWithCommentDialogViewModel);

    void inject(FormStardSheetFormViewMainViewModel formPredioFollowUpViewViewModel);

    void inject(FormMaintenanceControlViewMainViewModel formMaintenanceControlViewMainViewModel);

    void inject(FormMaintenanceControlViewViewModel formMaintenanceControlViewViewModel);

    void inject(TaskDetailViewModel taskDetailViewModel);

    void inject(PQRSViewModel pqrsViewModel);

    void inject(SettingsViewModel settingsViewModel);

    void inject(FormCartaIntencionViewViewModel formCartaIntencionViewViewModel);

    void inject(FormCartaIntencionViewMainViewModel formCartaIntencionViewMainViewModel);

    void inject(MainViewViewModel mainViewViewModel);

    void inject(MapPredioPotencialViewModel mapPredioPotencialViewModel);

    void inject(FormPredioFollowUpViewMainViewModel formPredioFollowUpViewMainViewModel);

    void inject(FormVegetalMaintenanceViewMainViewModel formVegetalMaintenanceViewMainViewModel);

    void inject(FormStardMonitorAndMaintenanceViewViewModel formStardMonitorAndMaintenanceViewViewModel);

    void inject(MapMonitorMaintenanceCommentDialogViewModel mapMonitorMaintenanceCommentDialogViewModel);

    void inject(MapMonitorAndMaintenanceViewModel monitorAndMaintenanceViewModel);

    void inject(FormStardMonitorAndMaintenanceViewMainViewModel formStardMonitorAndMaintenanceViewMainViewModel);

    void inject(TracksViewModel tracksViewModel);

    void inject(DashboardViewModel tracksViewModel);

    void inject(TasksViewModel tracksViewModel);

    void inject(ActionsDialogViewModel actionsDialogViewModel);

    void inject(FormSurveyViewViewModel actionsDialogViewModel);

    void inject(FormSurveyMainViewViewModel formMainViewViewModel);

    void inject(DashboardHeaderViewModel dashboardHeaderViewModel);

    void inject(MapMarkerCommentDialogViewModel mapMarkerCommentDialogViewModel);

    void inject(SiembraDetailListViewModel siembraDetailListViewModel);
}
