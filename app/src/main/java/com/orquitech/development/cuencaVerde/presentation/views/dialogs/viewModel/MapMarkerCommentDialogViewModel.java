package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class MapMarkerCommentDialogViewModel extends BaseViewModel implements MapMarkerCommentDialogMvvm.ViewModel {

    private final MapMarkerCommentDialogMvvm.View view;
    private String comment = "";

    public MapMarkerCommentDialogViewModel(AppView view) {
        super(view);
        this.view = (MapMarkerCommentDialogMvvm.View) view;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void saveComment(View view) {
        this.view.saveComment(comment);
    }
}
