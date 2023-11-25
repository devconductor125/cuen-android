package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class CheckboxItemViewModel implements CheckboxItemViewMvvm.ViewModel {

    private final CheckboxItemViewMvvm.View view;
    private Program program;

    public CheckboxItemViewModel(AppView view, Item item) {
        this.view = (CheckboxItemViewMvvm.View) view;
        this.program = ((Program) item);
    }

    @Override
    public Program getProgram() {
        return program;
    }

    @Override
    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public void onClick(View view) {

    }
}
