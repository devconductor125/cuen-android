package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class MonitorAndMaintenanceItemViewModel implements TaskItemViewMvvm.ViewModel {

    private final MonitorAndMaintenance monitorAndMaintenance;
    private final Bus bus;
    private final TaskItemViewMvvm.View view;
    private final ObservableField<String> image;
    private final ObservableField<String> title;
    private final ObservableField<Long> id;
    private final ObservableField<String> status;
    private final ObservableField<String> fromDate;
    private final ObservableField<String> toDate;
    private final ObservableField<Integer> dueDateStatus;
    private final ObservableField<String> taskTypeName;

    public MonitorAndMaintenanceItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (TaskItemViewMvvm.View) view;
        this.bus = bus;
        this.monitorAndMaintenance = ((MonitorAndMaintenance) item);
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.id = new ObservableField<>();
        this.status = new ObservableField<>();
        this.fromDate = new ObservableField<>();
        this.toDate = new ObservableField<>();
        this.dueDateStatus = new ObservableField<>();
        this.taskTypeName = new ObservableField<>();

        this.title.set(this.monitorAndMaintenance.getTitle());
        this.taskTypeName.set(this.monitorAndMaintenance.getTaskTypeTitle());
        this.fromDate.set(this.monitorAndMaintenance.getFromDate());
        this.toDate.set(this.monitorAndMaintenance.getToDate());
        this.dueDateStatus.set(this.monitorAndMaintenance.getDueDateStatus());
    }

    @Override
    public String getTitle() {
        return title.get();
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
        return taskTypeName.get();
    }

    @Override
    public void onClick(View view) {
        this.view.onClick(monitorAndMaintenance);
    }

    @Override
    public boolean hasRemoteId() {
        return false;
    }

    @Override
    public void onPhotographyRegistry(View view) {

    }
}
