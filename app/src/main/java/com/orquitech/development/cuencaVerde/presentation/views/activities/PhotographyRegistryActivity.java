package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PhotographyRegistryViewMvvm;

public class PhotographyRegistryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_base);
        getComponent().inject(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null && bundle.getParcelable(RxBus.PAYLOAD) != null) {
                BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
                fragment.animate(false);
                replaceFragment(fragment);
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onImageResult(Bitmap bitmap) {
        if (getCurrentFragment() instanceof PhotographyRegistryViewMvvm.View) {
            PhotographyRegistryViewMvvm.View fragment = (PhotographyRegistryViewMvvm.View) getCurrentFragment();
            fragment.addNewPhoto(bitmap);
        }
    }

    public void refreshPhotosList() {
        if (getCurrentFragment() instanceof PhotographyRegistryViewMvvm.View) {
            PhotographyRegistryViewMvvm.View fragment = (PhotographyRegistryViewMvvm.View) getCurrentFragment();
            fragment.refreshList();
        }
    }
}
