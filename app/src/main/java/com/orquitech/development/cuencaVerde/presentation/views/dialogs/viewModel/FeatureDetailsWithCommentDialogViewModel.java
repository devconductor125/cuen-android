package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class FeatureDetailsWithCommentDialogViewModel extends FeatureDetailsDialogViewModel implements FeatureDetailsWithCommentDialogMvvm.ViewModel {

    private GeometryComment geometryComment;
    private Task task;

    public FeatureDetailsWithCommentDialogViewModel(AppView dialog) {
        super(dialog);
        getComponent().inject(this);
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(prediosManager.getSavedGeometryCommentSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geometryComment -> {
                    this.geometryComment = geometryComment;
                    ((FeatureDetailsWithCommentDialogMvvm.View) view).onViewModelUpdated();
                })
                .subscribe());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getGeometryComment(task.getId(), feature.getProperties().getHash());
    }

    @Override
    public GeometryComment getGeometryComment() {
        return geometryComment;
    }

    @Override
    public void setGeometryComment(GeometryComment geometryComment) {
        this.geometryComment = geometryComment;
    }

    @Override
    public void onSaveComment(View view) {
        Feature feature = getFeature();
        geometryComment.setHash(feature.getProperties().getHash());
        prediosManager.saveGeometryComment(task.getId(), geometryComment);
        this.view.dismiss();
    }
}
