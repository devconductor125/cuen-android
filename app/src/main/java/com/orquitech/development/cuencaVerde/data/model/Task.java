package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;

public class Task extends BaseItem implements Parcelable {

    public static final int ON_TIME = 0;
    public static final int DELAYED = 1;
    public static final int UPLOAD_MINUTA_PHOTOS = 32;
    public static final int APROBACION_EN_VALIDACION = 6;
    public static final int PSA_ABIERTA = 32;

    public static final int SUBTYPE_SPECIAL_FIRST_ONE = 31;
    public static final int SUBTYPE_SPECIAL_LAST_ONE = 38;

    private String description;
    private String status;
    private String taskType;
    private String procedureId;
    private String procedureName;
    private String fromDate;
    private String toDate;
    protected int dueDateStatus;
    protected String title;
    private String taskTypeTitle;
    private PredioPotencial predioPotencial;
    private String potentialId;
    private boolean open;
    private String newComment;
    private int taskOpenSubTypeId;
    private int taskSubType;

    public Task() {
    }

    public String getTitle() {
        return procedureName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskType() {
        if (TextUtils.isEmpty(taskType)) {
            taskType = "";
        }
        if (getTaskOpenSubTypeId() == PSA_ABIERTA) {
            taskType = BaseFragment.PSA;
        }
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String projectId) {
        this.procedureId = projectId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getDueDateStatus() {
        return dueDateStatus;
    }

    public void setDueDateStatus(int dueDateStatus) {
        this.dueDateStatus = dueDateStatus;
    }

    public String getTaskTypeTitle() {
        return taskTypeTitle;
    }

    public void setTaskTypeTitle(String taskTypeTitle) {
        this.taskTypeTitle = taskTypeTitle;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public PredioPotencial getPredioPotencial() {
        return predioPotencial;
    }

    public void setPredioPotencial(PredioPotencial predioPotencial) {
        this.predioPotencial = predioPotencial;
    }

    public boolean isForPredioPotencial() {
        return predioPotencial != null && !TextUtils.isEmpty(predioPotencial.id);
    }

    public String getPotentialId() {
        return potentialId;
    }

    public void setPotentialId(String predioId) {
        this.potentialId = predioId;
    }

    public String getNewComment() {
        return newComment;
    }

    public void setNewComment(String newComment) {
        this.newComment = newComment;
    }

    public int getTaskOpenSubTypeId() {
        return taskOpenSubTypeId;
    }

    public void setTaskOpenSubTypeId(int taskOpenSubTypeId) {
        this.taskOpenSubTypeId = taskOpenSubTypeId;
    }

    public boolean canSendMap() {
        boolean isCommunicationsExecutableTask = getTaskOpenSubTypeId() == 6 || getTaskOpenSubTypeId() == 8;
        boolean isSiembraTask = taskType.equals(BaseFragment.CONTRACT_SIEMBRA);
        boolean isExecutionTask = taskType.equals(BaseFragment.EXECUTION);
        return !isOpen() || !isExecutionTask || !isSiembraTask || !isCommunicationsExecutableTask;
    }

    public boolean canDoTask() {
        boolean isContractorTask = getTaskOpenSubTypeId() == 18;
        boolean isCommunicationsExecutableTask = getTaskOpenSubTypeId() == 6 || getTaskOpenSubTypeId() == 7 || getTaskOpenSubTypeId() == 8;
        boolean isPsaExecutableTask = getTaskOpenSubTypeId() == PSA_ABIERTA;
        boolean isSiembraTask = taskType.equals(BaseFragment.CONTRACT_SIEMBRA);
        boolean isExecutionTask = taskType.equals(BaseFragment.EXECUTION);
        boolean isRecursoHidrico = getTaskType().equals(BaseFragment.HYDROLOGICAL_PROCESS);
        boolean isErosiveProcess = getTaskType().equals(BaseFragment.EROSIVE_PROCESS);
        boolean isPsa = getTaskType().equals(BaseFragment.PSA);
        return !isOpen() || isPsa || isExecutionTask || isSiembraTask || isCommunicationsExecutableTask || isContractorTask || isRecursoHidrico || isErosiveProcess || isPsaExecutableTask;
    }

    public boolean canEndTask() {
        boolean isMonitoreo = getTaskType().equals(BaseFragment.MONITOREO);
        boolean isMeasurementTask = getTaskType().equals(BaseFragment.MEDICION_DEL_PREDIO);
        boolean isExecutionTask = getTaskType().equals(BaseFragment.EXECUTION);
        boolean isCommunicationsExecutableTask = getTaskOpenSubTypeId() == 6 || getTaskOpenSubTypeId() == 7 || getTaskOpenSubTypeId() == 8;
        boolean isRecursoHidrico = getTaskType().equals(BaseFragment.HYDROLOGICAL_PROCESS);
        boolean isErosiveProcess = getTaskType().equals(BaseFragment.EROSIVE_PROCESS);
        boolean isPsa = getTaskType().equals(BaseFragment.PSA);
        return isOpen() || isPsa || isCommunicationsExecutableTask || isExecutionTask || isRecursoHidrico || isErosiveProcess || isMeasurementTask || isMonitoreo;
    }

    public boolean canDoPhotos() {
        return getTaskOpenSubTypeId() == 6 || getTaskOpenSubTypeId() == 7 || getTaskOpenSubTypeId() == 8 || isSpecialTask();
    }

    public boolean canSendData() {
        return getTaskOpenSubTypeId() != 13;
    }

    private boolean isNotSpecialTask() {
        boolean greaterThanOrEqual = getTaskOpenSubTypeId() >= SUBTYPE_SPECIAL_FIRST_ONE;
        boolean lessThanOrEqual = getTaskOpenSubTypeId() <= SUBTYPE_SPECIAL_LAST_ONE;
        return !(greaterThanOrEqual && lessThanOrEqual);
    }

    public boolean isSpecialTask() {
        boolean greaterThanOrEqual = getTaskOpenSubTypeId() >= SUBTYPE_SPECIAL_FIRST_ONE;
        boolean lessThanOrEqual = getTaskOpenSubTypeId() <= SUBTYPE_SPECIAL_LAST_ONE;
        return greaterThanOrEqual && lessThanOrEqual;
    }

    public boolean doesNotRequireForm() {
        boolean isCommunicationsWithNoForm = getTaskOpenSubTypeId() == 7;
        return isCommunicationsWithNoForm;
    }

    public boolean isCommunications() {
        return getTaskOpenSubTypeId() == 8 || getTaskOpenSubTypeId() == 6 || getTaskOpenSubTypeId() == 7;
    }

    public int getTaskSubType() {
        return taskSubType;
    }

    public void setTaskSubType(int taskSubType) {
        this.taskSubType = taskSubType;
    }

    public boolean isHydrologicalProcess() {
        return this.taskSubType == 21;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeString(this.taskType);
        dest.writeString(this.procedureId);
        dest.writeString(this.procedureName);
        dest.writeString(this.fromDate);
        dest.writeString(this.toDate);
        dest.writeInt(this.dueDateStatus);
        dest.writeString(this.title);
        dest.writeString(this.taskTypeTitle);
        dest.writeParcelable(this.predioPotencial, flags);
        dest.writeString(this.potentialId);
        dest.writeByte(this.open ? (byte) 1 : (byte) 0);
        dest.writeString(this.newComment);
        dest.writeInt(this.taskOpenSubTypeId);
        dest.writeInt(this.taskSubType);
    }

    protected Task(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.taskType = in.readString();
        this.procedureId = in.readString();
        this.procedureName = in.readString();
        this.fromDate = in.readString();
        this.toDate = in.readString();
        this.dueDateStatus = in.readInt();
        this.title = in.readString();
        this.taskTypeTitle = in.readString();
        this.predioPotencial = in.readParcelable(PredioPotencial.class.getClassLoader());
        this.potentialId = in.readString();
        this.open = in.readByte() != 0;
        this.newComment = in.readString();
        this.taskOpenSubTypeId = in.readInt();
        this.taskSubType = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
