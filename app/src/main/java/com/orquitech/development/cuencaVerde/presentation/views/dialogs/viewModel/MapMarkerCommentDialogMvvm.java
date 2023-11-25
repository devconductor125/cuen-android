package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface MapMarkerCommentDialogMvvm {

    interface View extends AppView {

        void saveComment(String comment);
    }

    interface ViewModel {

        String getComment();

        void setComment(String comment);

        void saveComment(android.view.View view);
    }
}
