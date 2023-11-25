package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class SamplingPointItemViewModel implements TaskItemViewMvvm.ViewModel {

    private final SamplingPointForm samplingPointForm;
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

    public SamplingPointItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (TaskItemViewMvvm.View) view;
        this.bus = bus;
        this.samplingPointForm = ((SamplingPointForm) item);
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.id = new ObservableField<>();
        this.status = new ObservableField<>();
        this.fromDate = new ObservableField<>();
        this.toDate = new ObservableField<>();
        this.dueDateStatus = new ObservableField<>();
        this.taskTypeName = new ObservableField<>();

        this.taskTypeName.set("Formulario de monitoreo");
        this.title.set(this.samplingPointForm.getSampleId());
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
        this.view.onClick(samplingPointForm);
    }

    @Override
    public boolean hasRemoteId() {
        return false;
    }

    @Override
    public void onPhotographyRegistry(View view) {
        SamplingPointForm samplingPointForm = this.samplingPointForm;
        samplingPointForm.setId(samplingPointForm.getCleanedId() + "_sampling_point");
        this.view.onPhotographyRegistry(samplingPointForm);
        this.samplingPointForm.setId(this.samplingPointForm.getCleanedId());
    }
}
