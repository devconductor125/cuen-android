package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.Task;

public interface FeatureDetailsWithCommentDialogMvvm {

    interface View extends FeatureDetailsDialogMvvm.View {

        void onViewModelUpdated();
    }

    interface ViewModel extends FeatureDetailsDialogMvvm.ViewModel {

        void setTask(Task task);

        GeometryComment getGeometryComment();

        void setGeometryComment(GeometryComment geometryComment);

        void onSaveComment(android.view.View view);

        void clearSubscriptions();
    }
}
