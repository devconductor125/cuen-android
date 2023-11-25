//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.component;

import com.orquitech.development.cuencaVerde.dependencyInjection.scope.AppScope;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseLocationActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.MainActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.PhotographyRegistryActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.TasksActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.CartaIntencionActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ContractorActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.MaintenanceControlFormActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SiembraDetailActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardMonitorAndMaintenanceActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardSheetFormActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SurveyActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.VegetalMaintenanceActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapPredioPotencialActivity;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ActionSelectorDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.CustomDialog;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.DashboardFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.ErosiveProcessListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.LogInFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PQRSFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PhotoDetailFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PhotographyRegistryFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PredioPotencialTasksFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PrediosFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.SamplingPointListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.SiembraDetailListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TaskDetailFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TasksFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TracksFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.base.FormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.cartaIntencion.FormCartaIntencionBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.contractorForm.FormContractorFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.erosiveProcessForm.FormErosiveProcessFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.maintenanceControl.FormMaintenanceControlBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.meetingsWithActorsForm.FormMeetingsWithActorsFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.predioFollowUp.FormPredioFollowUpBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.samplingPointForm.FormSamplingPointFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.siembraDetailForm.FormSiembraDetailFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardSheetForm.FormStardSheetFormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.vegetalMaintenance.FormVegetalMaintenanceBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.lists.ActionListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.AddPhotoItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.CheckboxItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.ListHeaderItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.MonitorAndMaintenancePhotoItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.PhotographicRegistryPhotoItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.PredioListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.ProjectListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.SamplingPointListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.SiembraDetailListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.TaskCommentItemView;
import com.orquitech.development.cuencaVerde.presentation.views.lists.TaskListItemView;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.AutoCompleteFormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.FormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.dasboardHeader.DashBoardHeader;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface ViewsComponent extends AppComponent {

    void inject(LogInFragment actionListItemView);

    void inject(BaseActivity baseActivity);

    void inject(FormBaseFragment formBaseFragment);

    void inject(StardSheetFormActivityForm stardSheetFormActivityForm);

    void inject(SiembraDetailActivityForm siembraDetailActivityForm);

    void inject(SamplingPointListItemView samplingPointListItemView);

    void inject(FormSamplingPointFormBaseFragment formSamplingPointFormBaseFragment);

    void inject(FormErosiveProcessFormBaseFragment formErosiveProcessFormBaseFragment);

    void inject(ContractorActivityForm contractorFormActivityForm);

    void inject(SiembraDetailListFragment siembraDetailListFragment);

    void inject(PhotoDetailFragment photoDetailFragment);

    void inject(SamplingPointListFragment samplingPointListFragment);

    void inject(ErosiveProcessListFragment erosiveProcessListFragment);

    void inject(PhotographicRegistryPhotoItemView photographicRegistryPhotoItemView);

    void inject(CheckboxItemView checkboxItemView);

    void inject(FormMaintenanceControlBaseFragment formMaintenanceControlBaseFragment);

    void inject(SiembraDetailListItemView formMaintenanceControlBaseFragment);

    void inject(FormVegetalMaintenanceBaseFragment formVegetalMaintenanceBaseFragment);

    void inject(MaintenanceControlFormActivityForm monitorControlFormActivityForm);

    void inject(FormSiembraDetailFormBaseFragment formSiembraDetailFormBaseFragment);

    void inject(FormContractorFormBaseFragment monitorControlFormActivityForm);

    void inject(FormMeetingsWithActorsFormBaseFragment formMeetingsWithActorsFormBaseFragment);

    void inject(StardMonitorAndMaintenanceActivityForm stardMonitorAndMaintenanceActivityForm);

    void inject(PredioPotencialTasksFragment predioPotencialTasksFragment);

    void inject(VegetalMaintenanceActivityForm vegetalMaintenanceActivityForm);

    void inject(FormStardSheetFormBaseFragment formStardSheetFormBaseFragment);

    void inject(CartaIntencionActivityForm cartaIntencionActivityForm);

    void inject(BaseLocationActivity baseLocationActivity);

    void inject(TaskCommentItemView taskCommentItemView);

    void inject(FormPredioFollowUpBaseFragment formPredioFollowUpBaseFragment);

    void inject(MainActivity mainActivity);

    void inject(TaskDetailFragment taskDetailFragment);

    void inject(MapPredioPotencialActivity mainActivity);

    void inject(PQRSFragment pqrsFragment);

    void inject(FormCartaIntencionBaseFragment formCartaIntencionBaseFragment);

    void inject(TasksActivity tasksActivity);

    void inject(MapActivity mapActivity);

    void inject(TracksFragment tracksFragment);

    void inject(FormStardMonitorAndMaintenanceBaseFragment formStardMonitorAndMaintenanceBaseFragment);

    void inject(AddPhotoItemView addPhotoItemView);

    void inject(MonitorAndMaintenancePhotoItemView monitorAndMaintenanceItemView);

    void inject(CustomDialog customDialog);

    void inject(ActionSelectorDialog actionSelectorDialog);

    void inject(ActionListItemView actionListItemView);

    void inject(DashboardFragment tasksFragment);

    void inject(ProjectListItemView taskListItemView);

    void inject(TasksFragment tasksFragment);

    void inject(TaskListItemView taskListItemView);

    void inject(FormTextField formTextField);

    void inject(AutoCompleteFormTextField autoCompleteFormTextField);

    void inject(SurveyActivityForm formActivity);

    void inject(FormSurveyBaseFragment formSurveyBaseFragment);

    void inject(ListHeaderItemView listHeaderItemView);

    void inject(DashBoardHeader dashBoardHeader);

    void inject(PrediosFragment prediosFragment);

    void inject(PredioListItemView predioListItemView);

    void inject(PhotographyRegistryActivity photographyRegistryActivity);

    void inject(PhotographyRegistryFragment photograpyRegistryFragment);
}
