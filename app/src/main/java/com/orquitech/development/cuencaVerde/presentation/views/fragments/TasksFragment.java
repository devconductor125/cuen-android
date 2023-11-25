//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.ErosiveProcess;
import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitoreoRecursoHidricoProcess;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.databinding.FragmentTasksBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TasksViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class TasksFragment extends BaseFragment implements TasksViewMvvm.View, ItemList {

    private FragmentTasksBinding binding;
    private TasksViewMvvm.ViewModel viewModel;
    private Bundle savedInstanceState;
    private RecyclerView.LayoutManager layoutManager;
    private Parcelable listState;
    private Procedure project;
    private AppBarLayout.OnOffsetChangedListener offsetChangedListener;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static TasksFragment getInstance(Bundle data) {
        TasksFragment fragment = new TasksFragment();
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
            project = bundle.getParcelable(RxBus.PAYLOAD);
        }
        offsetChangedListener = (appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                if (viewModel.hasProject()) {
                    Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                    upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    binding.mainToolbar.setNavigationIcon(upArrow);
                }
                binding.mainToolbar.animate()
                        .alpha(1);
            } else {
                if (viewModel.hasProject()) {
                    Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24px);
                    upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                    binding.mainToolbar.setNavigationIcon(upArrow);
                }
                binding.mainToolbar.animate()
                        .alpha(0);
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false);
        viewModel = (TasksViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.TASKS_VIEW_MODEL);
        viewModel.setProject(project);
        binding.setViewModel(viewModel);
        initSwipeRefresh();
        initList();
        viewModel.onReadyForSubscriptions();
        if (viewModel.hasProject()) {
            setUpToolBar(binding.mainToolbar);
        } else {
            disableToolBarBackButton(binding.mainToolbar);
        }
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
        ((BaseViewModel) viewModel).clearSubscriptions();
    }

    private void initList() {
        layoutManager = LayoutManagerUtil.getGridListLayoutManager(getContext(), savedInstanceState, LIST_STATE);
        binding.tasksList.setLayoutManager(layoutManager);
        binding.tasksList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefresh.setOnRefreshListener(() -> {
            animate(true);
            refreshTasks();
        });
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void refreshTasks() {
        binding.swipeRefresh.setRefreshing(true);
        fadeOutList();
        viewModel.getTasks(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        Parcelable listState = binding.tasksList.getLayoutManager().onSaveInstanceState();
        state.putParcelable(LIST_STATE, listState);
    }

    private void initListener(Context context) {
        try {
            activity = (FragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        binding.tasksList.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGotItems() {
        if (listState != null) {
            binding.tasksList.getLayoutManager().onRestoreInstanceState(listState);
            listState = null;
        }
        binding.swipeRefresh.setRefreshing(false);
        fadeInList();
        if (savedInstanceState == null || animate) {
            runListItemsLayoutAnimation(binding.tasksList);
        } else {
            binding.tasksList.setLayoutAnimation(null);
        }
    }

    private void fadeOutList() {
        binding.tasksList.animate()
                .alpha(0.5f)
                .start();
    }

    private void fadeInList() {
        binding.tasksList.animate()
                .alpha(1f)
                .start();
    }

    @Override
    public void onGetItemsError() {
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClicked(Item item) {
        if (!binding.swipeRefresh.isRefreshing()) {
            Bundle bundle = new Bundle();
            if (item instanceof MonitorAndMaintenance) {
                MonitorAndMaintenance monitorAndMaintenance = (MonitorAndMaintenance) item;
                bundle.putParcelable(RxBus.PAYLOAD, monitorAndMaintenance);
                activity.changeToActivity(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                return;
            }
            if (item instanceof ExecutionTask) {
                ExecutionTask executionTask = (ExecutionTask) item;
                bundle.putParcelable(RxBus.PAYLOAD, executionTask);
                activity.changeToActivity(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                return;
            }
            if (item instanceof ErosiveProcess) {
                ErosiveProcess erosiveProcess = (ErosiveProcess) item;
                bundle.putParcelable(RxBus.PAYLOAD, erosiveProcess);
                activity.changeToActivity(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                return;
            }
            if (item instanceof MonitoreoRecursoHidricoProcess) {
                MonitoreoRecursoHidricoProcess erosiveProcess = (MonitoreoRecursoHidricoProcess) item;
                bundle.putParcelable(RxBus.PAYLOAD, erosiveProcess);
                activity.changeToActivity(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                return;
            }
            if (item instanceof Task) {
                Task task = (Task) item;
                bundle.putParcelable(RxBus.PAYLOAD, task);
                activity.changeToActivity(AppViewsFactory.TASK_DETAIL_VIEW, bundle);
                return;
            }
            if (item instanceof CartaIntencion) {
                CartaIntencion cartaIntencion = (CartaIntencion) item;
                bundle.putParcelable(RxBus.PAYLOAD, cartaIntencion);
                activity.changeToActivity(AppViewsFactory.CARTA_INTENCION_VIEW, bundle);
                return;
            }
        }
    }

    @Override
    public void onItemClicked(View sharedElement, Item item) {

    }

    @Override
    public void onBackClick() {
        if (isAdded() && getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
