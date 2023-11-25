package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

        import android.location.Location;
        import android.view.View;

        import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
        import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
        import com.orquitech.development.cuencaVerde.data.model.Task;
        import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
        import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
        import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
        import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

        import io.reactivex.android.schedulers.AndroidSchedulers;
        import io.reactivex.disposables.CompositeDisposable;

public class MapMonitorAndMaintenanceViewModel extends MapTaskViewModel implements MapMonitorAndMaintenanceViewMvvm.ViewModel {

    private final MapMonitorAndMaintenanceViewMvvm.View view;
    private Location lastLocation;
    private String propertyName;
    private CompositeDisposable sendMonitorMaintenanceDisposable;

    public MapMonitorAndMaintenanceViewModel(AppView view) {
        super(view);
        this.view = (MapMonitorAndMaintenanceViewMvvm.View) view;
        getComponent().inject(this);
        sendMonitorMaintenanceDisposable = new CompositeDisposable();
    }

    @Override
    public void setTask(Task task) {
        if (task != null && this.task == null) {
            this.task = task;
            proceduresManager.getMonitorAndMaintenanceByIdForView(task.getId());
        }
    }

    @Override
    public void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        monitorAndMaintenanceCommentPoint.setId(monitorAndMaintenanceCommentPoint.getCleanedId());
        proceduresManager.saveMonitorAndMaintenancePoint(monitorAndMaintenanceCommentPoint);
        ((MonitorAndMaintenance) getTask()).addPoint(monitorAndMaintenanceCommentPoint);
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    protected void subscribeToObservables() {
        super.subscribeToObservables();

        disposables.add(prediosManager.getValidMaintenanceControlObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(isValid -> {
                    if (isValid) {
                        sendValidMaintenanceControl();
                    } else {
                        this.view.showMissingMaintenanceControlFormMessage();
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getMonitorAndMaintenanceSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(monitorAndMaintenance -> {
                    proceduresManager.getMonitorAndMaintenancePoints(monitorAndMaintenance.getId());
                    this.task = monitorAndMaintenance;
                    this.view.onGotMonitorAndMaintenance(monitorAndMaintenance);
                    if (monitorAndMaintenance.hasFillableForm()) {
                        view.showFormButton();
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getMonitorAndMaintenanceCommentPointsSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(resultList -> {
                    for (MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint : resultList) {
                        ((MonitorAndMaintenance) task).addPoint(monitorAndMaintenanceCommentPoint);
                    }
                    this.view.addMonitorAndMaintenanceCommentPointMarker(resultList);
                })
                .subscribe());

        subscribeToSentMonitorMaintenanceObservable();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        sendMonitorMaintenanceDisposable.clear();
    }

    @Override
    public void onGeoJson(GeoJson geoJson) {
    }

    @Override
    public void onForm(View view) {
        this.view.openMaintenanceControlForm();
    }

    @Override
    public void addMonitoringPoint(View view) {
        this.view.showAddMonitoringPointDialog(null);
    }

    private void sendValidMaintenanceControl() {

    }

    private boolean featureRequiresMaintenanceControlForm(Feature feature) {
        boolean featureRequiresMaintenanceControlForm = false;
        switch (feature.getProperties().getAccionId()) {
            case "4":
                featureRequiresMaintenanceControlForm = true;
                break;
        }
        return featureRequiresMaintenanceControlForm;
    }

    private void subscribeToSentMonitorMaintenanceObservable() {
        sendMonitorMaintenanceDisposable.add(proceduresManager.getSentMonitorAndMaintenanceCommentSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(monitorAndMaintenanceCommentPoint -> this.view.onSendMonitorMaintenanceSuccess())
                .subscribe());
    }

    @Override
    public void onSendMonitorAndMaintenanceCancelled() {
        sendMonitorMaintenanceDisposable.clear();
    }

    @Override
    public int getActivityType() {
        int returnValue = 0;
        switch (((MonitorAndMaintenance) task).getFormType()) {
            case MonitorAndMaintenance.STARD:
                returnValue = AppViewsFactory.STARD_MONITOR_AND_MAINTENANCE_VIEW;
                break;
            case MonitorAndMaintenance.VEGETAL_MAINTENANCE:
                returnValue = AppViewsFactory.VEGETAL_MAINTENANCE_VIEW;
                break;
            case MonitorAndMaintenance.PREDIO_FOLLOW_UP:
                returnValue = AppViewsFactory.PREDIO_FOLLOW_UP_VIEW;
                break;
        }
        return returnValue;
    }
}
