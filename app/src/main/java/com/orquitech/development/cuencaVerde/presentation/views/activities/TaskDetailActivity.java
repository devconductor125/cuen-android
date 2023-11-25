package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.TasksFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskDetailViewMvvm;

import java.io.Serializable;
import java.util.Objects;

public class TaskDetailActivity extends BaseActivity implements DialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_base);
        getComponent().inject(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null && bundle.getParcelable(RxBus.PAYLOAD) != null) {
                BaseFragment fragment = viewsFactory.getAppFragmentView(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                fragment.animate(false);
                replaceFragment(fragment);
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.SURVEY_VIEW:
            case AppViewsFactory.MAP_MONITOR_AND_MAINTENANCE_VIEW:
            case AppViewsFactory.MAP_CONTRACT_SIEMBRA_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                        break;
                }
                break;
            case AppViewsFactory.MAP_TASK_VIEW:
            case AppViewsFactory.MAP_EXECUTION_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName()) != null) {
                            TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager().findFragmentByTag(TasksFragment.class.getName());
                            tasksFragment.refreshTasks();
                        }
                        showMessage(TextUtil.getString(this, R.string.send_map_success_from_task), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }

    @Override
    public void onButtonOne() {

    }

    @Override
    public void onButtonTwo(Bundle bundle) {
        if (bundle != null) {
            switch (Objects.requireNonNull(bundle.getString(RxBus.PAYLOAD))) {
                case END_TASK_DIALOG:
                    if (getCurrentFragment() instanceof TaskDetailViewMvvm.View) {
                        TaskDetailViewMvvm.View fragment = (TaskDetailViewMvvm.View) getCurrentFragment();
                        fragment.sendAndEndTask();
                    }
                    break;
                case SEND_TASK_DATA_DIALOG:
                    if (getCurrentFragment() instanceof TaskDetailViewMvvm.View) {
                        TaskDetailViewMvvm.View fragment = (TaskDetailViewMvvm.View) getCurrentFragment();
                        fragment.sendTaskData();
                    }
                    break;
            }
        }
    }

    @Override
    public void onItemClick(Bundle bundle) {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onMidButtonClick(Parcelable data) {

    }

    @Override
    public void onMidButtonClick(Serializable data) {

    }
}
