package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.drawable.Drawable;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface TaskDetailViewMvvm {

    interface View extends AppListView {

        Drawable getDueDateBackground(int dueDateStatus);

        String getDueDateText(int dueDateStatus);

        void onDoTask();

        void onBackClick();

        void onPhotographyRegistry(Task task);

        void endTask();

        void onTaskDataSentSuccess();

        void showErrorMessage(int resource);

        void showCantSendTaskMessage();

        void showEndTaskConfirmation();

        void showSendTaskDataConfirmation();

        void sendAndEndTask();

        void sendTaskData();
    }

    interface ViewModel extends AppViewModel {

        void setTask(Task task);

        Task getTask();

        Drawable getDueDateBackground();

        String getDueDateStatus();

        void onDoTask(android.view.View view);

        void onPhotographyRegistry(android.view.View view);

        void onEndTask(android.view.View view);

        void onSendTaskData(android.view.View view);

        void endTask();

        void sendTaskData();

        void onBackClick(android.view.View view);
    }
}
