package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormViewModel;

public interface MeetingsWithActorsFormViewMvvm {

    interface View extends AppFormView {

        Task getTask();
    }

    interface ViewModel extends AppFormViewModel {

    }
}
