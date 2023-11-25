package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;

public interface ToolbarMvvm {

    interface View {

        void setListener(ToolBarListener listener);

        void onLeftIconClick();

        void onRightMidIconClick();

        void onRightEndIconClick();
    }

    interface ViewModel {

        void onLeftIconClick(android.view.View view);

        void onRightMidIconClick(android.view.View view);

        void onRightEndIconClick(android.view.View view);
    }
}
