package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapExecutionTaskViewModel extends MapTaskViewModel implements MapExecutionTaskViewMvvm.ViewModel {

    public MapExecutionTaskViewModel(AppView appView) {
        super(appView);
    }

    @Override
    public void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        monitorAndMaintenanceCommentPoint.setId(monitorAndMaintenanceCommentPoint.getCleanedId());
        proceduresManager.saveMonitorAndMaintenancePoint(monitorAndMaintenanceCommentPoint);
    }

    @Override
    protected void subscribeToObservables() {
        super.subscribeToObservables();

        disposables.add(proceduresManager.getMonitorAndMaintenanceCommentPointsSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(((MapExecutionTaskViewMvvm.View) this.view)::addMonitorAndMaintenanceCommentPointMarker)
                .subscribe());
    }

    @Override
    public void setTask(Task task) {
        super.setTask(task);
        proceduresManager.getMonitorAndMaintenancePoints(task.getId());
    }
}
