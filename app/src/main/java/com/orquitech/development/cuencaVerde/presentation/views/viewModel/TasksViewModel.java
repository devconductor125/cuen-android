package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.AppApiManager;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class TasksViewModel extends BaseSingleListViewModel implements TasksViewMvvm.ViewModel {

    private String projectId;
    private String projectName;
    private Procedure project;
    private CompositeDisposable viewModelDisposable;

    @Inject
    App app;

    @Inject
    ProceduresManager projectsManager;

    @Inject
    ApiManager apiManager;

    public TasksViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
        viewModelDisposable = new CompositeDisposable();
    }

    @Override
    public void setProject(Procedure project) {
        if (project != null) {
            this.project = project;
            this.projectId = project.getId();
            this.projectName = TextUtil.getString(view.getContext(), R.string.project_tasks, project.getName());
        }
    }

    @Override
    public String getProjectName() {
        if (TextUtils.isEmpty(projectName)) {
            projectName = TextUtil.getString(view.getContext(), R.string.tasks);
        }
        return projectName;
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
        getTasks();
    }

    private void subscribeToObservables() {
        viewModelDisposable.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.ON_FILES_RESULT:
                            Intent intent = bundle.getParcelable(RxBus.PAYLOAD);
                            if (intent != null) {
                                ArrayList<Uri> fileUris = new ArrayList<>();
                                ClipData clipData = intent.getClipData();
                                Uri data = intent.getData();
                                if (clipData != null) {
                                    for (int i = 0; i < clipData.getItemCount(); i++) {
                                        Uri uri = clipData.getItemAt(i).getUri();
                                        fileUris.add(uri);
                                    }
                                } else if (data != null) {
                                    fileUris.add(data);
                                }
                                projectsManager.uploadFiles("2", fileUris); // TODO get fragment_task_detail id
                            }
                            break;
                    }
                })
                .subscribe());

        viewModelDisposable.add(apiManager.getUploadProgressObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> Log.d(getClass().getName(), "Upload: " + bundle.get(AppApiManager.BYTES_WRITTEN) + " sent of " + bundle.get(AppApiManager.CONTENT_LENGTH)))
                .subscribe());

        subscribeToItemsListObservable(projectsManager.getTaskObservable(), AppViewsFactory.TASKS_LIST_ITEM_VIEW);
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        viewModelDisposable.clear();
    }

    @Override
    public void getTasks(boolean refresh) {
        projectsManager.clearMonitorAndMaintenance();
        getTasks();
    }

    @Override
    public void getTasks() {
        projectsManager.getProjectTasks(projectId).subscribe();
    }

    @Override
    public void onBackClick(View view) {
        ((TasksViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public boolean hasProject() {
        return project != null;
    }
}
