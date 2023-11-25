package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface FormViewMvvm {

    interface ViewModel extends AppViewModel {

        void setTask(Task task);

        void onBackPressed(android.view.View view);

        void onNextStep(android.view.View view);

        void onSendParcelable(android.view.View view);

        void onSendParcelableCancelled();
    }
}
