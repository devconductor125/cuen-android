package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class ErosiveProcessItemViewModel implements TaskItemViewMvvm.ViewModel {

    private final ErosiveProcessForm erosiveProcessForm;
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

    public ErosiveProcessItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (TaskItemViewMvvm.View) view;
        this.bus = bus;
        this.erosiveProcessForm = ((ErosiveProcessForm) item);
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.id = new ObservableField<>();
        this.status = new ObservableField<>();
        this.fromDate = new ObservableField<>();
        this.toDate = new ObservableField<>();
        this.dueDateStatus = new ObservableField<>();
        this.taskTypeName = new ObservableField<>();

        this.taskTypeName.set("Formulario de monitoreo");
        this.title.set(this.erosiveProcessForm.getIdentificationDate().getString());
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
        this.view.onClick(erosiveProcessForm);
    }

    @Override
    public boolean hasRemoteId() {
        return false;
    }

    @Override
    public void onPhotographyRegistry(View view) {
        ErosiveProcessForm erosiveProcessForm = this.erosiveProcessForm;
        erosiveProcessForm.setId(erosiveProcessForm.getCleanedId() + "_erosive_process_point");
        this.view.onPhotographyRegistry(erosiveProcessForm);
        this.erosiveProcessForm.setId(this.erosiveProcessForm.getCleanedId());
    }
}
