package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TasksFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PredioPotencialActivity extends BaseActivity {

    private CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_base);
        getComponent().inject(this);

        disposables = new CompositeDisposable();

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.PREDIO_POTENCIAL_TASK_VIEW, bundle);
            fragment.animate(false);
            replaceFragment(fragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposables.add(prediosManager.getSentSurveyAndPollObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(hasSentBothForms -> {
                    if (hasSentBothForms) {
                        prediosManager.resetSurveyAndPollObservable();
                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                })
                .subscribe());
    }

    @Override
    protected void onPause() {
        disposables.clear();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.CARTA_INTENCION_VIEW:
            case AppViewsFactory.SURVEY_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName()) != null) {
                            TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName());
                            tasksFragment.refreshTasks();
                        }
                        showMessage(TextUtil.getString(this, R.string.success), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }
}
