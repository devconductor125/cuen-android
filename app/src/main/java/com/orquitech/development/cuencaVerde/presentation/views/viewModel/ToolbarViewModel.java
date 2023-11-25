package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

public class ToolbarViewModel implements ToolbarMvvm.ViewModel {

    private final ToolbarMvvm.View toolBar;

    public ToolbarViewModel(ToolbarMvvm.View toolBar) {
        this.toolBar = toolBar;
    }

    @Override
    public void onLeftIconClick(View view) {
        toolBar.onLeftIconClick();
    }

    @Override
    public void onRightMidIconClick(View view) {
        toolBar.onRightMidIconClick();
    }

    @Override
    public void onRightEndIconClick(View view) {
        toolBar.onRightEndIconClick();
    }
}
