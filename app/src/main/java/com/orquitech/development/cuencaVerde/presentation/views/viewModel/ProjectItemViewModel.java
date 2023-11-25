package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import androidx.databinding.ObservableField;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class ProjectItemViewModel implements ProjectItemViewMvvm.ViewModel {

    private final Procedure project;
    private final Bus bus;
    private final ProjectItemViewMvvm.View view;
    private final ObservableField<Long> id;
    private final ObservableField<String> image;
    private final ObservableField<String> title;
    private final ObservableField<String> pendingTasks;
    private final ObservableField<String> doneTasks;

    public ProjectItemViewModel(AppView view, Item item, Bus bus) {
        this.view = (ProjectItemViewMvvm.View) view;
        this.bus = bus;
        this.project = ((Procedure) item);
        this.id = new ObservableField<>();
        this.image = new ObservableField<>();
        this.title = new ObservableField<>();
        this.pendingTasks = new ObservableField<>();
        this.doneTasks = new ObservableField<>();
        this.title.set(this.project.getName());

        this.pendingTasks.set("4");
        this.doneTasks.set("6");
    }

    @Override
    public String getTitle() {
        return title.get();
    }

    @Override
    public String getPendingTasks() {
        return pendingTasks.get();
    }

    @Override
    public String getDoneTasks() {
        return doneTasks.get();
    }

    @Override
    public void onClick(View view) {
        this.view.onClick(project);
    }
}
