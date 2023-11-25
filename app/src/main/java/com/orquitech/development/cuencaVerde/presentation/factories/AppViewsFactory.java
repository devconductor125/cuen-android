package com.orquitech.development.cuencaVerde.presentation.factories;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.presentation.views.activities.ErosiveProcessFormListActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.PhotographyRegistryActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.PredioPotencialActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.PrediosActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.SamplingPointListActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.SettingsActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.SiembraDetailListActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.TaskDetailActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.TasksActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.CartaIntencionActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ContractorActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.ErosiveProcessActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.MaintenanceControlFormActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.MeetingsWithActorsActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.PredioFollowUpActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SamplingPointActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SiembraDetailActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardMonitorAndMaintenanceActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.StardSheetFormActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.SurveyActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.form.VegetalMaintenanceActivityForm;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapContractSiembraActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapExecutionActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapHydrologicalMonitoringProcessActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapMonitorAndMaintenanceActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapPredioPotencialActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapPsaActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapTaskActivity;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
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
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.cartaIntencion.FormCartaIntencionFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.contractorForm.FormContractorFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.erosiveProcessForm.FormErosiveProcessFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.maintenanceControl.FormMaintenanceControlFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.meetingsWithActorsForm.FormMeetingsWithActorsFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.predioFollowUp.FormPredioFollowUpFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.samplingPointForm.FormSamplingPointFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.siembraDetailForm.FormSiembraDetailFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardMonitorAndMaintenance.FormStardMonitorAndMaintenanceFragmentPart2;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardSheetForm.FormStardSheetFormFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.stardSheetForm.FormStardSheetFormFragmentPart2;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart2;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart3;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart4;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart5;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.survey.FormSurveyFragmentPart6;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.vegetalMaintenance.FormVegetalMaintenanceFragmentPart1;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
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

public class AppViewsFactory implements ViewsFactory {

    public static final int ACTIONS_LIST_ITEM_VIEW = 100;
    public static final int PROJECTS_LIST_ITEM_VIEW = 200;
    public static final int TASKS_LIST_ITEM_VIEW = 300;
    public static final int SIEMBRA_DETAIL_LIST_ITEM_VIEW = 301;
    public static final int SAMPLING_POINT_LIST_ITEM_VIEW = 302;
    public static final int EROSIVE_PROCESS_LIST_ITEM_VIEW = 303;
    public static final int PREDIOS_LIST_ITEM_VIEW = 305;
    public static final int ADD_PHOTO_ITEM_VIEW = 310;
    public static final int MONITOR_MAINTENANCE_PHOTO_ITEM_VIEW = 320;
    public static final int PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW = 321;
    public static final int LIST_HEADER_ITEM_VIEW = 400;
    public static final int TASK_COMMENT_ITEM_VIEW = 500;
    public static final int CHECKBOX_ITEM_VIEW = 510;

    public static final int LOGIN_VIEW = 1000;
    public static final int MAP_TASK_VIEW = 2000;
    public static final int MAP_MONITOR_AND_MAINTENANCE_VIEW = 2100;
    public static final int MAP_CONTRACT_SIEMBRA_VIEW = 2110;
    public static final int MAP_HYDROLOGICAL_PROCESS_VIEW = 2116;
    public static final int MAP_EXECUTION_VIEW = 2111;
    public static final int MAP_PSA_VIEW = 2112;
    public static final int STARD_MONITOR_AND_MAINTENANCE_VIEW = 2200;
    public static final int COMMUNICATIONS_MEETINGS_FORM = 2210;
    public static final int VEGETAL_MAINTENANCE_VIEW = 2300;
    public static final int PREDIO_FOLLOW_UP_VIEW = 2400;
    public static final int CARTA_INTENCION_VIEW = 2500;
    public static final int TASKS_VIEW = 3000;
    public static final int SIEMBRA_DETAIL_LIST_VIEW = 3010;
    public static final int HYDROLOGICAL_SAMPLING_POINT_LIST_VIEW = 3011;
    public static final int EROSIVE_PROCESS_LIST_VIEW = 3012;
    public static final int PREDIO_POTENCIAL_TASKS_VIEW = 3100;
    public static final int PQRS_VIEW = 3200;
    public static final int TRACKS_VIEW = 4000;
    public static final int DASHBOARD_VIEW = 5000;
    public static final int SURVEY_VIEW = 6000;
    public static final int SETTINGS_VIEW = 6100;
    public static final int PREDIO_POTENCIAL_VIEW = 7000;
    public static final int PREDIO_POTENCIAL_TASK_VIEW = 7100;
    public static final int TASK_DETAIL_VIEW = 8000;
    public static final int STARD_SHEET_FORM_VIEW = 9000;
    public static final int CONTRACTOR_FORM_VIEW = 9010;
    public static final int SIEMBRA_DETAIL_FORM_VIEW = 9015;
    public static final int SAMPLING_POINT_FORM_VIEW = 9016;
    public static final int EROSIVE_PROCESS_FORM_VIEW = 9017;
    public static final int MAINTENANCE_CONTROL_FORM_VIEW = 9100;
    public static final int PREDIOS_VIEW = 9200;
    public static final int SIEMBRA_DETAIL_VIEW = 9300;
    public static final int PHOTOGRAPHY_REGISTRY_VIEW = 9400;
    public static final int PHOTO_DETAIL_VIEW = 9410;

    public static final int FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_PART_1 = 22000;
    public static final int FORM_PREDIO_FOLLOW_UP_MAIN_VIEW_PART_1 = 23000;
    public static final int FORM_CARTA_INTENCION_MAIN_VIEW_PART_1 = 24000;
    public static final int FORM_MAINTENANCE_CONTROL_MAIN_VIEW_PART_1 = 25000;

    public static final int FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_1 = 11000;
    public static final int FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2 = 12000;

    public static final int FORM_SURVEY_VIEW_PART_1 = 10000;
    public static final int FORM_SURVEY_VIEW_PART_2 = 20000;
    public static final int FORM_SURVEY_VIEW_PART_3 = 30000;
    public static final int FORM_SURVEY_VIEW_PART_4 = 40000;
    public static final int FORM_SURVEY_VIEW_PART_5 = 50000;
    public static final int FORM_SURVEY_VIEW_PART_6 = 60000;

    public static final int FORM_STARD_SHEET_FORM_VIEW_PART_1 = 70000;
    public static final int FORM_STARD_SHEET_FORM_VIEW_PART_2 = 71000;

    public static final int FORM_CONTRACTOR_FORM_VIEW_PART_1 = 80000;

    public static final int FORM_SIEMBRA_DETAIL_FORM_VIEW_PART_1 = 81000;
    public static final int FORM_MEETINGS_WITH_ACTORS_FORM_VIEW_PART_1 = 81010;
    public static final int FORM_SAMPLING_POINT_FORM_VIEW_PART_1 = 81020;
    public static final int FORM_EROSIVE_PROCESS_FORM_VIEW_PART_1 = 81021;

    private App app;

    public AppViewsFactory(App app) {
        this.app = app;
    }

    @Override
    public ItemView getItemView(int type, AppListView parentView) {
        ItemView item;
        switch (type) {
            case ACTIONS_LIST_ITEM_VIEW:
                item = new ActionListItemView(app.getApplicationContext());
                break;
            case PROJECTS_LIST_ITEM_VIEW:
                item = new ProjectListItemView(app.getApplicationContext());
                break;
            case TASKS_LIST_ITEM_VIEW:
                item = new TaskListItemView(app.getApplicationContext());
                break;
            case SIEMBRA_DETAIL_LIST_ITEM_VIEW:
                item = new SiembraDetailListItemView(app.getApplicationContext());
                break;
            case SAMPLING_POINT_LIST_ITEM_VIEW:
            case EROSIVE_PROCESS_LIST_ITEM_VIEW:
                item = new SamplingPointListItemView(app.getApplicationContext());
                break;
            case PREDIOS_LIST_ITEM_VIEW:
                item = new PredioListItemView(app.getApplicationContext());
                break;
            case ADD_PHOTO_ITEM_VIEW:
                item = new AddPhotoItemView(app.getApplicationContext());
                break;
            case MONITOR_MAINTENANCE_PHOTO_ITEM_VIEW:
                item = new MonitorAndMaintenancePhotoItemView(app.getApplicationContext());
                break;
            case PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW:
                item = new PhotographicRegistryPhotoItemView(app.getApplicationContext());
                break;
            case LIST_HEADER_ITEM_VIEW:
                item = new ListHeaderItemView(app.getApplicationContext());
                break;
            case TASK_COMMENT_ITEM_VIEW:
                item = new TaskCommentItemView(app.getApplicationContext());
                break;
            case CHECKBOX_ITEM_VIEW:
                item = new CheckboxItemView(app.getApplicationContext());
                break;
            default:
                item = null;
        }
        if (item != null) {
            item.setParentView(parentView);
        }
        return item;
    }

    @Override
    public BaseFragment getAppFragmentView(int type, Bundle bundle) {
        BaseFragment view;
        switch (type) {
            case LOGIN_VIEW:
                view = LogInFragment.getInstance(bundle);
                break;
            case DASHBOARD_VIEW:
                view = DashboardFragment.getInstance(bundle);
                break;
            case TASKS_VIEW:
                view = TasksFragment.getInstance(bundle);
                break;
            case PHOTOGRAPHY_REGISTRY_VIEW:
                view = PhotographyRegistryFragment.getInstance(bundle);
                break;
            case SIEMBRA_DETAIL_LIST_VIEW:
                view = SiembraDetailListFragment.getInstance(bundle);
                break;
            case HYDROLOGICAL_SAMPLING_POINT_LIST_VIEW:
                view = SamplingPointListFragment.getInstance(bundle);
                break;
            case EROSIVE_PROCESS_LIST_VIEW:
                view = ErosiveProcessListFragment.getInstance(bundle);
                break;
            case PREDIO_POTENCIAL_TASK_VIEW:
                view = PredioPotencialTasksFragment.getInstance(bundle);
                break;
            case PQRS_VIEW:
                view = PQRSFragment.getInstance(bundle);
                break;
            case TASK_DETAIL_VIEW:
                view = TaskDetailFragment.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_1:
                view = FormSurveyFragmentPart1.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_2:
                view = FormSurveyFragmentPart2.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_3:
                view = FormSurveyFragmentPart3.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_4:
                view = FormSurveyFragmentPart4.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_5:
                view = FormSurveyFragmentPart5.getInstance(bundle);
                break;
            case FORM_SURVEY_VIEW_PART_6:
                view = FormSurveyFragmentPart6.getInstance(bundle);
                break;
            case FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_1:
                view = FormStardMonitorAndMaintenanceFragmentPart1.getInstance(bundle);
                break;
            case FORM_STARD_MONITOR_AND_MAINTENANCE_MAIN_VIEW_PART_2:
                view = FormStardMonitorAndMaintenanceFragmentPart2.getInstance(bundle);
                break;
            case FORM_VEGETAL_MAINTENANCE_MAIN_VIEW_PART_1:
                view = FormVegetalMaintenanceFragmentPart1.getInstance(bundle);
                break;
            case FORM_PREDIO_FOLLOW_UP_MAIN_VIEW_PART_1:
                view = FormPredioFollowUpFragmentPart1.getInstance(bundle);
                break;
            case FORM_MAINTENANCE_CONTROL_MAIN_VIEW_PART_1:
                view = FormMaintenanceControlFragmentPart1.getInstance(bundle);
                break;
            case FORM_CARTA_INTENCION_MAIN_VIEW_PART_1:
                view = FormCartaIntencionFragmentPart1.getInstance(bundle);
                break;
            case FORM_STARD_SHEET_FORM_VIEW_PART_1:
                view = FormStardSheetFormFragmentPart1.getInstance(bundle);
                break;
            case FORM_STARD_SHEET_FORM_VIEW_PART_2:
                view = FormStardSheetFormFragmentPart2.getInstance(bundle);
                break;
            case FORM_CONTRACTOR_FORM_VIEW_PART_1:
                view = FormContractorFormFragmentPart1.getInstance(bundle);
                break;
            case FORM_SIEMBRA_DETAIL_FORM_VIEW_PART_1:
                view = FormSiembraDetailFormFragmentPart1.getInstance(bundle);
                break;
            case FORM_SAMPLING_POINT_FORM_VIEW_PART_1:
                view = FormSamplingPointFormFragmentPart1.getInstance(bundle);
                break;
            case FORM_EROSIVE_PROCESS_FORM_VIEW_PART_1:
                view = FormErosiveProcessFormFragmentPart1.getInstance(bundle);
                break;
            case FORM_MEETINGS_WITH_ACTORS_FORM_VIEW_PART_1:
                view = FormMeetingsWithActorsFormFragmentPart1.getInstance(bundle);
                break;
            case PREDIOS_VIEW:
                view = PrediosFragment.getInstance(bundle);
                break;
            case PHOTO_DETAIL_VIEW:
                view = PhotoDetailFragment.getInstance(bundle);
                break;
            default:
                view = null;
        }
        return view;
    }

    @Override
    public Class getActivityClass(int type) {
        Class resultClass;
        switch (type) {
            case TASKS_VIEW:
                resultClass = TasksActivity.class;
                break;
            case SIEMBRA_DETAIL_LIST_VIEW:
                resultClass = SiembraDetailListActivity.class;
                break;
            case HYDROLOGICAL_SAMPLING_POINT_LIST_VIEW:
                resultClass = SamplingPointListActivity.class;
                break;
            case EROSIVE_PROCESS_LIST_VIEW:
                resultClass = ErosiveProcessFormListActivity.class;
                break;
            case MAP_TASK_VIEW:
                resultClass = MapTaskActivity.class;
                break;
            case MAP_MONITOR_AND_MAINTENANCE_VIEW:
                resultClass = MapMonitorAndMaintenanceActivity.class;
                break;
            case MAP_CONTRACT_SIEMBRA_VIEW:
                resultClass = MapContractSiembraActivity.class;
                break;
            case MAP_EXECUTION_VIEW:
                resultClass = MapExecutionActivity.class;
                break;
            case MAP_PSA_VIEW:
                resultClass = MapPsaActivity.class;
                break;
            case MAP_HYDROLOGICAL_PROCESS_VIEW:
                resultClass = MapHydrologicalMonitoringProcessActivity.class;
                break;
            case TASK_DETAIL_VIEW:
                resultClass = TaskDetailActivity.class;
                break;
            case PHOTOGRAPHY_REGISTRY_VIEW:
                resultClass = PhotographyRegistryActivity.class;
                break;
            case STARD_SHEET_FORM_VIEW:
                resultClass = StardSheetFormActivityForm.class;
                break;
            case CONTRACTOR_FORM_VIEW:
                resultClass = ContractorActivityForm.class;
                break;
            case SIEMBRA_DETAIL_FORM_VIEW:
                resultClass = SiembraDetailActivityForm.class;
                break;
            case SAMPLING_POINT_FORM_VIEW:
                resultClass = SamplingPointActivityForm.class;
                break;
            case EROSIVE_PROCESS_FORM_VIEW:
                resultClass = ErosiveProcessActivityForm.class;
                break;
            case MAINTENANCE_CONTROL_FORM_VIEW:
                resultClass = MaintenanceControlFormActivityForm.class;
                break;
            case PREDIO_POTENCIAL_VIEW:
                resultClass = MapPredioPotencialActivity.class;
                break;
            case PREDIO_POTENCIAL_TASK_VIEW:
                resultClass = PredioPotencialActivity.class;
                break;
            case STARD_MONITOR_AND_MAINTENANCE_VIEW:
                resultClass = StardMonitorAndMaintenanceActivityForm.class;
                break;
            case VEGETAL_MAINTENANCE_VIEW:
                resultClass = VegetalMaintenanceActivityForm.class;
                break;
            case COMMUNICATIONS_MEETINGS_FORM:
                resultClass = MeetingsWithActorsActivityForm.class;
                break;
            case PREDIO_FOLLOW_UP_VIEW:
                resultClass = PredioFollowUpActivityForm.class;
                break;
            case SURVEY_VIEW:
                resultClass = SurveyActivityForm.class;
                break;
            case SETTINGS_VIEW:
                resultClass = SettingsActivity.class;
                break;
            case CARTA_INTENCION_VIEW:
                resultClass = CartaIntencionActivityForm.class;
                break;
            case PREDIOS_VIEW:
                resultClass = PrediosActivity.class;
                break;
            default:
                resultClass = null;
        }
        return resultClass;
    }
}
