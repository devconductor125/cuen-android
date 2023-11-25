package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.os.Bundle;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsWithCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;

public class MapPsaActivity extends MapTaskActivity {

    @Override
    public int getViewType() {
        return AppViewsFactory.MAP_PSA_VIEW;
    }

    @Override
    public void showFeatureDialog(Feature feature) {
        if (feature != null) {
            if (feature.isFromManagementLayer()) {
                showManagementLayerPropertyInfo(feature);
            } else {
                if (feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RxBus.PAYLOAD, feature);
                    bundle.putParcelable(RxBus.TASK, viewModel.getTask());
                    FeatureDetailsWithCommentFragment fragment = (FeatureDetailsWithCommentFragment) dialogManager.getDialogFragment(DialogsFactory.FEATURE_DETAIL_WITH_COMMENT, bundle);
                    fragment.show(getSupportFragmentManager(), MAP_DIALOG);
                }
            }
        }
    }
}
