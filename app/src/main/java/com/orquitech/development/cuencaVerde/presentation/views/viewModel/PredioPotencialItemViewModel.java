package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class PredioPotencialItemViewModel implements TaskItemViewMvvm.ViewModel {

    private final PredioPotencial predioPotencial;
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
    private final ObservableField<Boolean> hasRemoteId;

    public PredioPotencialItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (TaskItemViewMvvm.View) view;
        this.bus = bus;
        this.predioPotencial = ((PredioPotencial) item);
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.id = new ObservableField<>();
        this.status = new ObservableField<>();
        this.fromDate = new ObservableField<>();
        this.toDate = new ObservableField<>();
        this.dueDateStatus = new ObservableField<>();
        this.taskTypeName = new ObservableField<>();
        this.hasRemoteId = new ObservableField<>();

        this.title.set(this.predioPotencial.getName());
        this.hasRemoteId.set(this.predioPotencial.getRemoteId() > 0);
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
        return null;
    }

    @Override
    public Drawable getDueDateBackground() {
        return null;
    }

    @Override
    public String getTaskType() {
        return taskTypeName.get();
    }

    @Override
    public void onClick(View view) {
        this.view.onClick(predioPotencial);
    }

    @Override
    public boolean hasRemoteId() {
        return hasRemoteId.get();
    }

    @Override
    public void onPhotographyRegistry(View view) {

    }
}
