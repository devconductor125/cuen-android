//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.databinding.FragmentDashboardBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.dasboardHeader.DashBoardHeaderListener;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardViewMvvm.View, ItemList {

    private FragmentDashboardBinding binding;
    private DashboardViewMvvm.ViewModel viewModel;
    private Bundle savedInstanceState;
    private RecyclerView.LayoutManager layoutManager;
    private Parcelable listState;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static DashboardFragment getInstance(Bundle data) {
        DashboardFragment fragment = new DashboardFragment();
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        viewModel = (DashboardViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.PROJECTS_VIEW_MODEL);
        binding.setViewModel(viewModel);
        binding.dashBoardHeader.getQuota();

        initSwipeRefresh();
        initList();
        viewModel.onReadyForSubscriptions();
        setAppBarListener();
        try {
            binding.dashBoardHeader.setListener((DashBoardHeaderListener) getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    private void setAppBarListener() {
        binding.mainAppbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                binding.mainToolbar.animate()
                        .alpha(1);
            } else {
                binding.mainToolbar.animate()
                        .alpha(0);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((BaseViewModel) viewModel).clearSubscriptions();
    }

    private void initList() {
        layoutManager = LayoutManagerUtil.getVerticalListLayoutManager(getContext(), savedInstanceState, LIST_STATE);
        binding.projectsList.setLayoutManager(layoutManager);
        binding.projectsList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefresh.setOnRefreshListener(() -> {
            animate(true);
            fadeOutList();
            viewModel.getDashboard(true);
            binding.dashBoardHeader.getQuota();
        });
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        Parcelable listState = binding.projectsList.getLayoutManager().onSaveInstanceState();
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
    public boolean isFragmentAnimate() {
        return animate;
    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        binding.projectsList.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGotItems() {
        if (listState != null) {
            binding.projectsList.getLayoutManager().onRestoreInstanceState(listState);
            listState = null;
        }
        binding.swipeRefresh.setRefreshing(false);
        fadeInList();
        if (savedInstanceState == null || animate) {
            runListItemsLayoutAnimation(binding.projectsList);
        } else {
            binding.projectsList.setLayoutAnimation(null);
        }
    }

    private void fadeOutList() {
        binding.projectsList.animate()
                .alpha(0.5f)
                .start();
    }

    private void fadeInList() {
        binding.projectsList.animate()
                .alpha(1f)
                .start();
    }

    @Override
    public void onGetItemsError() {
        binding.swipeRefresh.setRefreshing(false);
        fadeInList();
    }

    @Override
    public void onProjectClicked(Procedure project) {
        if (!binding.swipeRefresh.isRefreshing()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(RxBus.PAYLOAD, project);
            activity.changeToActivity(AppViewsFactory.TASKS_VIEW, bundle);
        }
    }

    @Override
    public void onItemClicked(Item task) {

    }

    @Override
    public void onItemClicked(View sharedElement, Item item) {

    }
}
