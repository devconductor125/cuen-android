//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.databinding.FragmentTaskDetailBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ConfirmationDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskDetailViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class TaskDetailFragment extends BaseFragment implements TaskDetailViewMvvm.View {

    private FragmentTaskDetailBinding binding;
    private TaskDetailViewMvvm.ViewModel viewModel;
    private Bundle savedInstanceState;
    private Task task;
    private AppBarLayout.OnOffsetChangedListener offsetChangedListener;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static TaskDetailFragment getInstance(Bundle data) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getComponent().inject(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            task = bundle.getParcelable(RxBus.PAYLOAD);
        }
        offsetChangedListener = (appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                binding.mainToolbar.setNavigationIcon(upArrow);
                binding.mainToolbar.animate()
                        .alpha(1);
            } else {
                Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                binding.mainToolbar.setNavigationIcon(upArrow);
                binding.mainToolbar.animate()
                        .alpha(0);
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_detail, container, false);
        viewModel = (TaskDetailViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.TASK_DETAIL_VIEW_MODEL);
        viewModel.setTask(task);
        binding.setViewModel(viewModel);
        viewModel.onReadyForSubscriptions();
        setUpToolBar(binding.mainToolbar);
        binding.taskComments.setNestedScrollingEnabled(false);
        initList();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mainAppbar.addOnOffsetChangedListener(offsetChangedListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mainAppbar.removeOnOffsetChangedListener(offsetChangedListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.clearSubscriptions();
    }

    private void initListener(Context context) {
        try {
            activity = (FragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initList() {
        RecyclerView.LayoutManager layoutManager = LayoutManagerUtil.getGridListLayoutManager(getContext(), savedInstanceState, LIST_STATE);
        binding.taskComments.setLayoutManager(layoutManager);
        binding.taskComments.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public Drawable getDueDateBackground(int dueDateStatus) {
        Drawable result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_green);
        switch (dueDateStatus) {
            case Task.ON_TIME:
                result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_green);
                break;
            case Task.DELAYED:
                result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_red);
                break;
        }
        return result;
    }

    @Override
    public String getDueDateText(int dueDateStatus) {
        String result = "";
        switch (dueDateStatus) {
            case Task.ON_TIME:
                result = getContext().getString(R.string.on_time);
                break;
            case Task.DELAYED:
                result = getContext().getString(R.string.delayed);
                break;
        }
        return result;
    }

    @Override
    public void onDoTask() {
        if (!UserLocationService.isInstanceCreated() || UserLocationService.isSameTask(task.getId())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(RxBus.PAYLOAD, task);
            switch (task.getTaskType()) {
                case MEDICION_DEL_PREDIO:
                    if (task.getTaskSubType() == Task.UPLOAD_MINUTA_PHOTOS) {
                        activity.changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
                    } else {
                        activity.changeToActivity(AppViewsFactory.MAP_TASK_VIEW, bundle);
                    }
                    break;
                case MONITOREO:
                    activity.changeToActivity(AppViewsFactory.MAP_MONITOR_AND_MAINTENANCE_VIEW, bundle);
                    break;
                case CONTRACTOR:
                    activity.changeToActivity(AppViewsFactory.MAP_CONTRACT_SIEMBRA_VIEW, bundle);
                    break;
                case HYDROLOGICAL_PROCESS:
                case EROSIVE_PROCESS:
                    activity.changeToActivity(AppViewsFactory.MAP_HYDROLOGICAL_PROCESS_VIEW, bundle);
                    break;
                case EXECUTION:
                    activity.changeToActivity(AppViewsFactory.MAP_EXECUTION_VIEW, bundle);
                    break;
                case PSA:
                    activity.changeToActivity(AppViewsFactory.MAP_PSA_VIEW, bundle);
                    break;
                case COMMUNICATIONS:
                    activity.changeToActivity(AppViewsFactory.COMMUNICATIONS_MEETINGS_FORM, bundle);
                    break;
                case ENCUESTA:
                    activity.requestFileReadPermission(true); //TODO File load test
                    activity.changeToActivity(AppViewsFactory.SURVEY_VIEW, bundle);
                    break;
            }
        } else {
            activity.showMessage(getString(R.string.active_tracking), R.color.colorAccent, R.id.coordinator);
        }
    }

    @Override
    public void onBackClick() {
        if (isAdded() && getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onGotItems() {

    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        binding.taskComments.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGetItemsError() {

    }

    @Override
    public void onPhotographyRegistry(Task task) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, task);
        activity.changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
    }

    @Override
    public void endTask() {
        Intent resultIntent = new Intent();
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        getActivity().finish();
    }

    @Override
    public void onTaskDataSentSuccess() {
        activity.showMessage(getString(R.string.task_data_sent), R.color.colorSecondary);
    }

    @Override
    public void showErrorMessage(int resource) {
        activity.showMessage(getString(resource), R.color.colorAccent);
    }

    @Override
    public void showCantSendTaskMessage() {
        activity.showMessage(getString(R.string.cant_send_task_message), R.color.colorAccent);
    }

    @Override
    public void showEndTaskConfirmation() {
        if (isAdded() && getActivity() != null) {
            ConfirmationDialogFragment fragment = (ConfirmationDialogFragment) dialogManager.getDialogFragment(DialogsFactory.CONFIRMATION, new Bundle());
            fragment.setTitle(getString(R.string.end_send_task));
            Bundle bundle = new Bundle();
            bundle.putString(RxBus.PAYLOAD, BaseActivity.END_TASK_DIALOG);
            fragment.setBundle(bundle);
            fragment.show(getActivity().getSupportFragmentManager(), BaseActivity.END_TASK_DIALOG);
        }
    }

    @Override
    public void showSendTaskDataConfirmation() {
        if (isAdded() && getActivity() != null) {
            ConfirmationDialogFragment fragment = (ConfirmationDialogFragment) dialogManager.getDialogFragment(DialogsFactory.CONFIRMATION, new Bundle());
            fragment.setTitle(getString(R.string.end_send_task_data));
            Bundle bundle = new Bundle();
            bundle.putString(RxBus.PAYLOAD, BaseActivity.SEND_TASK_DATA_DIALOG);
            fragment.setBundle(bundle);
            fragment.show(getActivity().getSupportFragmentManager(), BaseActivity.SEND_TASK_DATA_DIALOG);
        }
    }

    @Override
    public void sendAndEndTask() {
        viewModel.endTask();
    }

    @Override
    public void sendTaskData() {
        viewModel.sendTaskData();
    }
}
