package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.TaskComment;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class TaskCommentItemViewModel implements TaskCommentItemViewMvvm.ViewModel {

    private final TaskComment item;
    private final TaskCommentItemViewMvvm.View view;

    public TaskCommentItemViewModel(AppView view, Item item) {
        this.view = (TaskCommentItemViewMvvm.View) view;
        this.item = (TaskComment) item;
    }

    @Override
    public String getContent() {
        String result = "";
        if (item != null) {
            result = item.getContent();
        }
        return result;
    }

    @Override
    public String getAuthor() {
        String result = "";
        if (item != null) {
            result = item.getAuthor();
        }
        return result;
    }

    @Override
    public void onClick(View view) {

    }
}
