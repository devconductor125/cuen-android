package com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories;

import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.ActionsDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.CustomDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.CustomDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsWithCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMarkerCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMonitorMaintenanceCommentDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.OfflineDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ActionItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AddPhotoItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppListItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AutoCompleteFormTextFieldViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.CartaIntencionItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.CheckboxItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardHeaderViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ErosiveProcessItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ErosiveProcessListViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.FormTextFieldViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ListHeaderItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.LogInViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MainViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MapPredioPotencialViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MonitorAndMaintenanceItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MonitorAndMaintenancePhotoItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PQRSViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotoDetailViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographicRegistryPhotoItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographyRegistryViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PredioPotencialItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PredioPotencialTasksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PrediosViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ProjectItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SamplingPointItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SamplingPointListViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SettingsViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SiembraDetailItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.SiembraDetailListViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskCommentItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskDetailViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TasksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TracksViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.cartaIntencion.FormCartaIntencionViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm.FormContractorFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm.FormErosiveProcessFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm.FormErosiveProcessFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm.FormMeetingsWithActorsFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp.FormPredioFollowUpViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm.FormSamplingPointFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm.FormSamplingPointFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm.FormSiembraDetailFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm.FormStardSheetFormViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyMainViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey.FormSurveyViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewMainViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance.FormVegetalMaintenanceViewViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapExecutionTaskViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapMonitorAndMaintenanceViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.contractSiembra.MapContractSiembraViewModel;

import javax.inject.Inject;

public class AppViewModelsFactory implements ViewModelsFactory {

    public static final int LOG_IN_VIEW_MODEL = 100;
    public static final int MAP_TASK_VIEW_MODEL = 201;
    public static final int MAP_EXECUTION_TASK_VIEW_MODEL = 202;
    public static final int TRACKS_VIEW_MODEL = 300;
    public static final int PROJECTS_VIEW_MODEL = 400;
    public static final int TASKS_VIEW_MODEL = 500;
    public static final int PHOTOGRAPHY_REGISTRY_LIST_VIEW_MODEL = 501;
    public static final int PREDIOS_VIEW_MODEL = 505;
    public static final int PREDIO_POTENCIAL_TASKS_VIEW_MODEL = 510;
    public static final int TASK_DETAIL_VIEW_MODEL = 550;
    public static final int PHOTO_DETAIL_VIEW_MODEL = 560;
    public static final int MAIN_VIEW_VIEW_MODEL = 600;
    public static final int FORM_SURVEY_MAIN_VIEW_VIEW_MODEL = 700;
    public static final int FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_VIEW_MODEL = 710;
    public static final int FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_VIEW_MODEL = 720;
    public static final int FORM_PREDIO_FOLLOW_UP_MAIN_VIEW_VIEW_MODEL = 730;
    public static final int FORM_CARTA_INTENCION_MAIN_VIEW_VIEW_MODEL = 740;
    public static final int FORM_STARD_SHEET_FORM_MAIN_VIEW_VIEW_MODEL = 745;
    public static final int FORM_CONTRACTOR_FORM_MAIN_VIEW_VIEW_MODEL = 746;
    public static final int FORM_SIEMBRA_DETAIL_FORM_MAIN_VIEW_VIEW_MODEL = 747;
    public static final int FORM_SAMPLING_POINT_FORM_MAIN_VIEW_VIEW_MODEL = 749;
    public static final int FORM_MEETINGS_WITH_ACTORS_FORM_MAIN_VIEW_VIEW_MODEL = 750;
    public static final int FORM_MONITOR_CONTROL_MAIN_VIEW_VIEW_MODEL = 751;
    public static final int FORM_EROSIVE_PROCESS_FORM_MAIN_VIEW_VIEW_MODEL = 752;
    public static final int SETTINGS_VIEW_VIEW_MODEL = 760;
    public static final int FORM_SURVEY_VIEW_MODEL = 800;
    public static final int FORM_STARD_MONITOR_AND_MAINTENANCE_VIEW_MODEL = 810;
    public static final int FORM_VEGETAL_MAINTENANCE_VIEW_MODEL = 820;
    public static final int FORM_PREDIO_FOLLOW_UP_VIEW_MODEL = 830;
    public static final int FORM_CARTA_INTENCION_VIEW_VIEW_MODEL = 840;
    public static final int FORM_STARD_SHEET_FORM_VIEW_VIEW_MODEL = 850;
    public static final int FORM_CONTRACTOR_FORM_VIEW_VIEW_MODEL = 851;
    public static final int FORM_SIEMBRA_DETAIL_FORM_VIEW_VIEW_MODEL = 852;
    public static final int FORM_SAMPLING_POINT_FORM_VIEW_VIEW_MODEL = 853;
    public static final int FORM_EROSIVE_PROCESS_FORM_VIEW_VIEW_MODEL = 854;
    public static final int FORM_MEETING_WITH_ACTORS_FORM_VIEW_VIEW_MODEL = 855;
    public static final int MAP_PREDIO_POTENCIAL_VIEW_MODEL = 900;
    public static final int MONITOR_AND_MAINTENANCE_VIEW_MODEL = 910;
    public static final int CONTRACT_SIEMBRA_VIEW_MODEL = 911;
    public static final int DASH_BOARD_HEADER_VIEW_MODEL = 999;
    public static final int ACTION_SELECTOR_DIALOG_VIEW_MODEL = 950;
    public static final int MAP_MARKER_COMMENT_DIALOG_VIEW_MODEL = 960;
    public static final int FEATURE_DETAILS_DIALOG_VIEW_MODEL = 965;
    public static final int CONFIRMATION_DIALOG_VIEW_MODEL = 966;
    public static final int OFFLINE_DIALOG_VIEW_MODEL = 967;
    public static final int MAP_MONITOR_MAINTENANCE_DIALOG_VIEW_MODEL = 970;
    public static final int PQRS_VIEW_MODEL = 980;
    public static final int SIEMBRA_DETAIL_LIST_VIEW_MODEL = 981;
    public static final int SAMPLING_POINT_LIST_VIEW_MODEL = 982;
    public static final int EROSIVE_PROCESS_LIST_VIEW_MODEL = 983;
    public static final int FEATURE_DETAILS_WITH_COMMENT_DIALOG_VIEW_MODEL = 984;

    public static final int ACTION_ITEM_VIEW_MODEL = 1000;
    public static final int PROJECT_ITEM_VIEW_MODEL = 2000;
    public static final int TASK_ITEM_VIEW_MODEL = 3000;
    public static final int ADD_PHOTO_ITEM_VIEW_MODEL = 3200;
    public static final int MONITOR_AND_MAINTENANCE_ITEM_VIEW_MODEL = 3100;
    public static final int CARTA_INTENCION_ITEM_VIEW_MODEL = 3150;
    public static final int PREDIO_POTENCIAL_ITEM_VIEW_MODEL = 3151;
    public static final int SIEMBRA_DETAIL_ITEM_VIEW_MODEL = 3152;
    public static final int SAMPLING_POINT_ITEM_VIEW_MODEL = 3153;
    public static final int EROSIVE_PROCESS_ITEM_VIEW_MODEL = 3154;
    public static final int MONITOR_AND_MAINTENANCE_PHOTO_ITEM_VIEW_MODEL = 3300;
    public static final int PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW_MODEL = 3310;
    public static final int LIST_HEADER_ITEM_VIEW_MODEL = 4000;
    public static final int TASK_COMMENT_ITEM_VIEW_MODEL = 5000;
    public static final int CHECKBOX_ITEM_VIEW_MODEL = 5010;

    public static final int FORM_TEXT_FIELD_VIEW_MODEL = 10000;
    public static final int AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL = 11000;

    private final Bus bus;

    @Inject
    public AppViewModelsFactory(Bus bus) {
        this.bus = bus;
    }

    @Override
    public BaseViewModel getViewModel(AppView view, int type) {
        BaseViewModel viewModel;
        switch (type) {
            case LOG_IN_VIEW_MODEL:
                viewModel = new LogInViewModel(view);
                break;
            case MAP_TASK_VIEW_MODEL:
                viewModel = new MapTaskViewModel(view);
                break;
            case MAP_EXECUTION_TASK_VIEW_MODEL:
                viewModel = new MapExecutionTaskViewModel(view);
                break;
            case MAP_PREDIO_POTENCIAL_VIEW_MODEL:
                viewModel = new MapPredioPotencialViewModel(view);
                break;
            case MONITOR_AND_MAINTENANCE_VIEW_MODEL:
                viewModel = new MapMonitorAndMaintenanceViewModel(view);
                break;
            case CONTRACT_SIEMBRA_VIEW_MODEL:
                viewModel = new MapContractSiembraViewModel(view);
                break;
            case TASK_DETAIL_VIEW_MODEL:
                viewModel = new TaskDetailViewModel(view);
                break;
            case PHOTO_DETAIL_VIEW_MODEL:
                viewModel = new PhotoDetailViewModel(view);
                break;
            case TRACKS_VIEW_MODEL:
                viewModel = new TracksViewModel(view);
                break;
            case PROJECTS_VIEW_MODEL:
                viewModel = new DashboardViewModel(view);
                break;
            case TASKS_VIEW_MODEL:
                viewModel = new TasksViewModel(view);
                break;
            case PHOTOGRAPHY_REGISTRY_LIST_VIEW_MODEL:
                viewModel = new PhotographyRegistryViewModel(view);
                break;
            case SIEMBRA_DETAIL_LIST_VIEW_MODEL:
                viewModel = new SiembraDetailListViewModel(view);
                break;
            case SAMPLING_POINT_LIST_VIEW_MODEL:
                viewModel = new SamplingPointListViewModel(view);
                break;
            case EROSIVE_PROCESS_LIST_VIEW_MODEL:
                viewModel = new ErosiveProcessListViewModel(view);
                break;
            case PREDIOS_VIEW_MODEL:
                viewModel = new PrediosViewModel(view);
                break;
            case PREDIO_POTENCIAL_TASKS_VIEW_MODEL:
                viewModel = new PredioPotencialTasksViewModel(view);
                break;
            case PQRS_VIEW_MODEL:
                viewModel = new PQRSViewModel(view);
                break;
            case MAIN_VIEW_VIEW_MODEL:
                viewModel = new MainViewViewModel(view);
                break;
            case FORM_SURVEY_VIEW_MODEL:
                viewModel = new FormSurveyViewViewModel(view);
                break;
            case FORM_TEXT_FIELD_VIEW_MODEL:
                viewModel = new FormTextFieldViewModel(view);
                break;
            case AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL:
                viewModel = new AutoCompleteFormTextFieldViewModel(view);
                break;
            case FORM_SURVEY_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormSurveyMainViewViewModel(view);
                break;
            case FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormStardMonitorAndMaintenanceViewMainViewModel(view);
                break;
            case FORM_STARD_MONITOR_AND_MAINTENANCE_VIEW_MODEL:
                viewModel = new FormStardMonitorAndMaintenanceViewViewModel(view);
                break;
            case FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormVegetalMaintenanceViewMainViewModel(view);
                break;
            case FORM_VEGETAL_MAINTENANCE_VIEW_MODEL:
                viewModel = new FormVegetalMaintenanceViewViewModel(view);
                break;
            case FORM_PREDIO_FOLLOW_UP_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormPredioFollowUpViewMainViewModel(view);
                break;
            case FORM_PREDIO_FOLLOW_UP_VIEW_MODEL:
                viewModel = new FormMaintenanceControlViewViewModel(view);
                break;
            case FORM_CARTA_INTENCION_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormCartaIntencionViewMainViewModel(view);
                break;
            case FORM_STARD_SHEET_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormStardSheetFormViewMainViewModel(view);
                break;
            case FORM_CONTRACTOR_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormContractorFormViewMainViewModel(view);
                break;
            case FORM_SIEMBRA_DETAIL_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormSiembraDetailFormViewMainViewModel(view);
                break;
            case FORM_SAMPLING_POINT_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormSamplingPointFormViewMainViewModel(view);
                break;
            case FORM_EROSIVE_PROCESS_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormErosiveProcessFormViewMainViewModel(view);
                break;
            case FORM_MEETINGS_WITH_ACTORS_FORM_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormMeetingsWithActorsFormViewMainViewModel(view);
                break;
            case FORM_MONITOR_CONTROL_MAIN_VIEW_VIEW_MODEL:
                viewModel = new FormMaintenanceControlViewMainViewModel(view);
                break;
            case FORM_CARTA_INTENCION_VIEW_VIEW_MODEL:
                viewModel = new FormCartaIntencionViewViewModel(view);
                break;
            case FORM_STARD_SHEET_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormStardSheetFormViewViewModel(view);
                break;
            case FORM_CONTRACTOR_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormContractorFormViewViewModel(view);
                break;
            case FORM_SIEMBRA_DETAIL_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormSiembraDetailFormViewViewModel(view);
                break;
            case FORM_SAMPLING_POINT_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormSamplingPointFormViewViewModel(view);
                break;
            case FORM_EROSIVE_PROCESS_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormErosiveProcessFormViewViewModel(view);
                break;
            case FORM_MEETING_WITH_ACTORS_FORM_VIEW_VIEW_MODEL:
                viewModel = new FormMeetingsWithActorsFormViewViewModel(view);
                break;
            case DASH_BOARD_HEADER_VIEW_MODEL:
                viewModel = new DashboardHeaderViewModel(view);
                break;
            case ACTION_SELECTOR_DIALOG_VIEW_MODEL:
                viewModel = new ActionsDialogViewModel(view);
                break;
            case MAP_MARKER_COMMENT_DIALOG_VIEW_MODEL:
                viewModel = new MapMarkerCommentDialogViewModel(view);
                break;
            case FEATURE_DETAILS_DIALOG_VIEW_MODEL:
                viewModel = new FeatureDetailsDialogViewModel(view);
                break;
            case FEATURE_DETAILS_WITH_COMMENT_DIALOG_VIEW_MODEL:
                viewModel = new FeatureDetailsWithCommentDialogViewModel(view);
                break;
            case CONFIRMATION_DIALOG_VIEW_MODEL:
                viewModel = new CustomDialogViewModel((CustomDialogMvvm.View) view);
                break;
            case OFFLINE_DIALOG_VIEW_MODEL:
                viewModel = new OfflineDialogViewModel(view);
                break;
            case MAP_MONITOR_MAINTENANCE_DIALOG_VIEW_MODEL:
                viewModel = new MapMonitorMaintenanceCommentDialogViewModel(view);
                break;
            case SETTINGS_VIEW_VIEW_MODEL:
                viewModel = new SettingsViewModel(view);
                break;
            default:
                viewModel = null;
        }
        return viewModel;
    }

    @Override
    public AppListItemViewModel getItemViewModel(AppView view, int type, Item item) {
        AppListItemViewModel viewModel;
        switch (type) {
            case ACTION_ITEM_VIEW_MODEL:
                viewModel = new ActionItemViewModel(view, item, bus);
                break;
            case PROJECT_ITEM_VIEW_MODEL:
                viewModel = new ProjectItemViewModel(view, item, bus);
                break;
            case TASK_ITEM_VIEW_MODEL:
                viewModel = new TaskItemViewModel(view, item, bus);
                break;
            case ADD_PHOTO_ITEM_VIEW_MODEL:
                viewModel = new AddPhotoItemViewModel(view);
                break;
            case MONITOR_AND_MAINTENANCE_ITEM_VIEW_MODEL:
                viewModel = new MonitorAndMaintenanceItemViewModel(view, item, bus);
                break;
            case CARTA_INTENCION_ITEM_VIEW_MODEL:
                viewModel = new CartaIntencionItemViewModel(view, item, bus);
                break;
            case PREDIO_POTENCIAL_ITEM_VIEW_MODEL:
                viewModel = new PredioPotencialItemViewModel(view, item, bus);
                break;
            case SIEMBRA_DETAIL_ITEM_VIEW_MODEL:
                viewModel = new SiembraDetailItemViewModel(view, item, bus);
                break;
            case SAMPLING_POINT_ITEM_VIEW_MODEL:
                viewModel = new SamplingPointItemViewModel(view, item, bus);
                break;
            case EROSIVE_PROCESS_ITEM_VIEW_MODEL:
                viewModel = new ErosiveProcessItemViewModel(view, item, bus);
                break;
            case MONITOR_AND_MAINTENANCE_PHOTO_ITEM_VIEW_MODEL:
                viewModel = new MonitorAndMaintenancePhotoItemViewModel(view, item);
                break;
            case PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW_MODEL:
                viewModel = new PhotographicRegistryPhotoItemViewModel(view, item);
                break;
            case LIST_HEADER_ITEM_VIEW_MODEL:
                viewModel = new ListHeaderItemViewModel(view, item);
                break;
            case TASK_COMMENT_ITEM_VIEW_MODEL:
                viewModel = new TaskCommentItemViewModel(view, item);
                break;
            case CHECKBOX_ITEM_VIEW_MODEL:
                viewModel = new CheckboxItemViewModel(view, item);
                break;
            default:
                viewModel = null;
        }
        return viewModel;
    }
}
