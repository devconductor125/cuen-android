package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.PrediosFragment;

public class PrediosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_base);
        getComponent().inject(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.PREDIOS_VIEW, bundle);
            fragment.animate(false);
            replaceFragment(fragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.PREDIO_POTENCIAL_TASK_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (getSupportFragmentManager().findFragmentByTag(PrediosFragment.class.getName()) != null) {
                            PrediosFragment prediosFragment = (PrediosFragment) getSupportFragmentManager().findFragmentByTag(PrediosFragment.class.getName());
                            prediosFragment.refreshTasks();
                        }
                        showMessage(TextUtil.getString(this, R.string.send_survey_carta_success), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }
}
