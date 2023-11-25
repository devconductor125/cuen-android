package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class TaskItemViewModel implements TaskItemViewMvvm.ViewModel {

    private final Task task;
    private final Bus bus;
    private final TaskItemViewMvvm.View view;
    private final ObservableField<String> image;
    private final ObservableField<String> title;
    private final ObservableField<Long> id;
    private final ObservableField<String> status;
    private final ObservableField<String> fromDate;
    private final ObservableField<String> toDate;
    private final ObservableField<Integer> dueDateStatus;
    private final ObservableField<String> taskTypeTitle;

    public TaskItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (TaskItemViewMvvm.View) view;
        this.bus = bus;
        this.task = ((Task) item);
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.id = new ObservableField<>();
        this.status = new ObservableField<>();
        this.fromDate = new ObservableField<>();
        this.toDate = new ObservableField<>();
        this.dueDateStatus = new ObservableField<>();
        this.taskTypeTitle = new ObservableField<>();

        this.title.set(this.task.getTitle());
        this.status.set(this.task.getStatus());
        this.fromDate.set(this.task.getFromDate());
        this.toDate.set(this.task.getToDate());
        this.dueDateStatus.set(this.task.getDueDateStatus());
        this.taskTypeTitle.set(this.task.getTaskTypeTitle());
    }

    @Override
    public String getTitle() {
        String result;
        if (task instanceof ExecutionTask) {
            // result = task.getTitle(); TODO Check this line form the service response
            result = "Tarea de ejecución - " + task.getTitle();
        } else {
            result = task.getProcedureName();
        }
        return result;
    }

    @Override
    public String getStatus() {
        return status.get();
    }

    @Override
    public String getFromDate() {
        return fromDate.get();
    }

    @Override
    public String getToDate() {
        return toDate.get();
    }

    @Override
    public String getDueDateStatus() {
        return view.getDueDateText(dueDateStatus.get());
    }

    @Override
    public Drawable getDueDateBackground() {
        return view.getDueDateBackground(dueDateStatus.get());
    }

    @Override
    public String getTaskType() {
        String result = "Tarea de " + taskTypeTitle.get();
        if (task instanceof ExecutionTask) {
            result = "Tarea de ejecución - " + taskTypeTitle.get();
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        this.view.onClick(task);
    }

    @Override
    public boolean hasRemoteId() {
        return false;
    }

    @Override
    public void onPhotographyRegistry(View view) {

    }
}
