package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.drawable.Drawable;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface TaskItemViewMvvm {

    interface View extends AppView {

        void onClick(Item item);

        Drawable getDueDateBackground(int dueDateStatus);

        String getDueDateText(int integer);

        void onPhotographyRegistry(BaseItem item);
    }

    interface ViewModel extends AppListItemViewModel {

        String getTitle();

        String getStatus();

        String getFromDate();

        String getToDate();

        boolean hasRemoteId();

        String getDueDateStatus();

        Drawable getDueDateBackground();

        String getTaskType();

        void onPhotographyRegistry(android.view.View view);
    }
}
