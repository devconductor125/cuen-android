package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.Quota;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface DashBoardHeaderMvvm {

    interface View extends AppView {

        void openPredioPotencialView();

        void onSettingsClick();

        void onPrediosClick();

        void setQuota(Quota quota);

        void hideQuota();

        void hidePrediosButtons();
    }

    interface ViewModel extends AppViewModel {

        void onCreatePredioPotencialClick(android.view.View view);

        void onPrediosClick(android.view.View view);

        void onSettingsClick(android.view.View view);

        void getQuota();
    }
}
