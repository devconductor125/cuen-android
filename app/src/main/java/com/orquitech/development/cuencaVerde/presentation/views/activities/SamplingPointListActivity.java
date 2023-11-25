package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.SamplingPointListFragment;

public class SamplingPointListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_base);
        getComponent().inject(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Parcelable parcelable = intent.getParcelableExtra(RxBus.PAYLOAD);
            Parcelable task = intent.getParcelableExtra(RxBus.TASK);
            if (parcelable != null && parcelable instanceof Feature) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(RxBus.PAYLOAD, parcelable);
                bundle.putParcelable(RxBus.TASK, task);
                BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.HYDROLOGICAL_SAMPLING_POINT_LIST_VIEW, bundle);
                fragment.animate(false);
                replaceFragment(fragment);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.SAMPLING_POINT_FORM_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (getSupportFragmentManager().findFragmentByTag(SamplingPointListFragment.class.getName()) != null) {
                            SamplingPointListFragment samplingPointListFragment = (SamplingPointListFragment) getSupportFragmentManager().findFragmentByTag(SamplingPointListFragment.class.getName());
                            samplingPointListFragment.refreshSiembraDetails();
                        }
                        showMessage(TextUtil.getString(this, R.string.saved_sampling_point_form_success), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }
}
