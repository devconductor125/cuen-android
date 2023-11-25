package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ListAdapterFactory;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class TaskDetailViewModel extends BaseSingleListViewModel implements TaskDetailViewMvvm.ViewModel {

    private TaskDetailViewMvvm.View view;
    private Task task;
    private boolean endingTask;
    private boolean canSendTask = true;

    @Inject
    ProceduresManager proceduresManager;

    @Inject
    PrediosManager prediosManager;

    @Inject
    ListAdapterFactory listAdapterFactory;

    public TaskDetailViewModel(AppView view) {
        super((AppListView) view);
        this.view = (TaskDetailViewMvvm.View) view;
        getComponent().inject(this);
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToItemsListObservable(proceduresManager.getTaskCommentsObservable(), AppViewsFactory.TASK_COMMENT_ITEM_VIEW);
        if (this.task != null) {
            proceduresManager.getTaskComments(this.task).subscribe();
        }
        disposables.add(prediosManager.getTaskHasGeoJsonSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geoJson -> {
                    if (geoJson.hasValidFeatures()) {
                        if (task.getTaskType().equals(BaseFragment.PSA)) {
                            prediosManager.endPsaTask(task);
                        } else if (task instanceof ExecutionTask) {
                            prediosManager.endExecutionTask(task);
                        } else if (task.getTaskSubType() == Task.APROBACION_EN_VALIDACION) {
                            proceduresManager.endTaskNoMap(task);
                        } else {
                            proceduresManager.endMeasurementTask(task);
                        }
                        this.view.endTask();
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.missing_execution_map);
                    }
                }).subscribe());

        disposables.add(prediosManager.getTaskHasContractorFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(contractorForm -> {
                    if (contractorForm.isValid()) {
                        prediosManager.sendContractorForm(task);
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.missing_contractor_form);
                    }
                }).subscribe());

        disposables.add(prediosManager.getSentSiembraDetailsFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    if (success) {
                        prediosManager.sendContractorTask(task.getCleanedId());
                        this.view.endTask();
                    }
                }).subscribe());

        disposables.add(prediosManager.getHasMeetingWithActorsFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(meetingsWithActorsForm -> {
                    if (!TextUtils.isEmpty(meetingsWithActorsForm.getTaskId())) {
                        prediosManager.sendMeetingsWithActorsForm(task);
                    } else {
                        if (task.doesNotRequireForm()) {
                            proceduresManager.sendPhotographicRegistry(task);
                        } else {
                            endingTask = false;
                            this.view.showErrorMessage(R.string.missing_meeting_with_actors_form);
                        }
                    }
                }).subscribe());

        disposables.add(prediosManager.getSentMeetingWithActorsFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    if (success) {
                        proceduresManager.sendPhotographicRegistry(task);
                    }
                }).subscribe());

        disposables.add(prediosManager.getTaskHasSamplingPointFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(samplingPointForms -> {
                    if (samplingPointForms.size() > 0) {
                        prediosManager.sendHydrologicalProcessForms(task);
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.missing_sampling_forms);
                    }
                }).subscribe());

        disposables.add(prediosManager.getTaskHasErosiveProcessFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(erosiveProcessForms -> {
                    if (erosiveProcessForms.size() > 0) {
                        prediosManager.sendErosiveProcessForms(task);
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.missing_erosive_process_forms);
                    }
                }).subscribe());

        disposables.add(proceduresManager.getTaskHasMinutaPhotosSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(photographicRegistryPhotos -> {
                    if (photographicRegistryPhotos.size() > 0) {
                        proceduresManager.endTaskNoMap(task);
                        this.view.endTask();
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.missing_minuta_photos);
                    }
                }).subscribe());

        disposables.add(prediosManager.getSentContractorFormSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(contractorForm -> {
                    if (contractorForm.isValid() && canSendTask) {
                        // prediosManager.deleteContractorForm(task.getId());
                        prediosManager.sendSiembraDetailForms(task);
                    } else {
                        canSendTask = true;
                        this.view.onTaskDataSentSuccess();
                    }
                }).subscribe());

        disposables.add(prediosManager.getSentSamplingPointFormsSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    if (canSendTask) {
                        this.view.endTask();
                    } else {
                        canSendTask = true;
                        this.view.onTaskDataSentSuccess();
                    }
                })
                .subscribe());

        disposables.add(prediosManager.getSentErosiveProcessFormsSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    if (canSendTask) {
                        this.view.endTask();
                    } else {
                        canSendTask = true;
                        this.view.onTaskDataSentSuccess();
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getSentPhotographicRegistrySubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(success -> {
                    if (success && canSendTask) {
                        if (task.getTaskSubType() == Task.UPLOAD_MINUTA_PHOTOS) {
                            proceduresManager.endTaskNoMap(task);
                        } else if (task.isCommunications()) {
                            prediosManager.endCommunicationsTask(task);
                            this.view.endTask();
                        }
                    } else {
                        canSendTask = true;
                        this.view.onTaskDataSentSuccess();
                    }
                }).subscribe());

        disposables.add(proceduresManager.getSentMapSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(onSuccess -> {
                    if (canSendTask) {
                        if (task.getTaskType().equals(BaseFragment.PSA)) {
                            prediosManager.endCommunicationsTask(task);
                        }
                        this.view.endTask();
                    } else {
                        canSendTask = true;
                        this.view.onTaskDataSentSuccess();
                    }
                }).subscribe());

        disposables.add(prediosManager.getValidMaintenanceControlObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(isValid -> {
                    if (isValid) {
                        proceduresManager.endMonitorAndMaintenance((MonitorAndMaintenance) task);
                        this.view.endTask();
                    } else {
                        endingTask = false;
                        this.view.showErrorMessage(R.string.requires_maintenance_control_form);
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getSentMonitorAndMaintenanceCommentSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> this.view.endTask())
                .subscribe());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public Task getTask() {
        return this.task;
    }

    @Override
    public Drawable getDueDateBackground() {
        return view.getDueDateBackground(this.task.getDueDateStatus());
    }

    @Override
    public String getDueDateStatus() {
        return view.getDueDateText(task.getDueDateStatus());
    }

    @Override
    public void onDoTask(View view) {
        this.view.onDoTask();
    }

    @Override
    public void onPhotographyRegistry(View view) {
        this.view.onPhotographyRegistry(task);
    }

    @Override
    public void onEndTask(View view) {
        if (connectivityStatusManager.isConnected() && !endingTask) {
            this.view.showEndTaskConfirmation();
        } else {
            this.view.showCantSendTaskMessage();
        }
    }

    @Override
    public void onSendTaskData(View view) {
        if (connectivityStatusManager.isConnected()) {
            this.view.showSendTaskDataConfirmation();
        } else {
            this.view.showCantSendTaskMessage();
        }
    }

    @Override
    public void endTask() {
        endingTask = true;
        if (task.isSpecialTask()) {
            prediosManager.endSpecialTask(task);
            this.view.endTask();
        } else {
            switch (task.getTaskType()) {
                case BaseFragment.MONITOREO:
                    prediosManager.validateMaintenanceControlForm((MonitorAndMaintenance) task);
                    break;
                case BaseFragment.EROSIVE_PROCESS:
                    prediosManager.taskHasErosiveProcessForms(task);
                    break;
                case BaseFragment.HYDROLOGICAL_PROCESS:
                    prediosManager.taskHasHydrologicalProcessForms(task);
                    break;
                case BaseFragment.COMMUNICATIONS:
                    prediosManager.taskHasMeetingWithActorsForm(task);
                    break;
                case BaseFragment.CONTRACTOR:
                    prediosManager.taskHasContractorForm(task);
                    break;
                case BaseFragment.PSA:
                    prediosManager.taskHasGeoJson(task);
                    break;
                case BaseFragment.EXECUTION:
                    if (task.getTaskOpenSubTypeId() == 13) {
                        prediosManager.endExecutionTask(task);
                        this.view.endTask();
                    } else {
                        prediosManager.taskHasGeoJson(task);
                    }
                    break;
                default:
                    if (task.getTaskSubType() == Task.UPLOAD_MINUTA_PHOTOS) {
                        proceduresManager.taskHasMinutaPhotos(task.getId());
                    } else {
                        prediosManager.taskHasGeoJson(task);
                    }
                    break;
            }
        }
    }

    @Override
    public void sendTaskData() {
        canSendTask = false;
        if (task.isSpecialTask()) {
            proceduresManager.sendPhotographicRegistry(task);
        } else {
            switch (task.getTaskType()) {
                case BaseFragment.MONITOREO:
                    proceduresManager.sendMonitorAndMaintenanceData((MonitorAndMaintenance) task, task.getCleanedId());
                    this.view.onTaskDataSentSuccess();
                    canSendTask = true;
                    break;
                case BaseFragment.EROSIVE_PROCESS:
                    prediosManager.sendErosiveProcessForms(task);
                    break;
                case BaseFragment.HYDROLOGICAL_PROCESS:
                    prediosManager.sendHydrologicalProcessForms(task);
                    break;
                case BaseFragment.COMMUNICATIONS:
                    prediosManager.sendMeetingsWithActorsForm(task);
                    break;
                case BaseFragment.CONTRACTOR:
                    prediosManager.sendContractorForm(task);
                    break;
                case BaseFragment.PSA:
                    proceduresManager.sendMap(task);
                    break;
                case BaseFragment.EXECUTION:
                    proceduresManager.sendMap(task);
                    break;
                default:
                    if (task.getTaskSubType() == Task.UPLOAD_MINUTA_PHOTOS) {
                        proceduresManager.sendMinuta(task);
                    } else if (task.getTaskSubType() == Task.APROBACION_EN_VALIDACION) {
                        proceduresManager.sendValidationMap(task);
                    } else {
                        proceduresManager.sendMap(task);
                    }
                    break;
            }
        }
    }

    @Override
    public void onBackClick(View view) {
        this.view.onBackClick();
    }
}
