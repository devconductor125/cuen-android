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
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.databinding.FragmentPredioPotencialTasksBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.PredioPotencialTasksViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class PredioPotencialTasksFragment extends BaseFragment implements PredioPotencialTasksViewMvvm.View, ItemList {

    private FragmentPredioPotencialTasksBinding binding;
    private PredioPotencialTasksViewMvvm.ViewModel viewModel;
    private Bundle savedInstanceState;
    private RecyclerView.LayoutManager layoutManager;
    private Parcelable listState;
    private PredioPotencial predioPotencial;
    private AppBarLayout.OnOffsetChangedListener offsetChangedListener;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static PredioPotencialTasksFragment getInstance(Bundle data) {
        PredioPotencialTasksFragment fragment = new PredioPotencialTasksFragment();
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
            predioPotencial = bundle.getParcelable(RxBus.PAYLOAD);
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
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_predio_potencial_tasks, container, false);
        viewModel = (PredioPotencialTasksViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.PREDIO_POTENCIAL_TASKS_VIEW_MODEL);
        viewModel.setPredio(predioPotencial);
        binding.setViewModel(viewModel);
        initList();
        viewModel.onReadyForSubscriptions();
        setUpToolBar(binding.mainToolbar);
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
    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
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
    }

    @Override
    public void onGotItems() {

    }

    @Override
    public void onGetItemsError() {
    }

    @Override
    public void onItemClicked(Item item) {
        Bundle bundle = new Bundle();
        if (item instanceof MonitorAndMaintenance) {
            MonitorAndMaintenance monitorAndMaintenance = (MonitorAndMaintenance) item;
            bundle.putParcelable(RxBus.PAYLOAD, monitorAndMaintenance);
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

    @Override
    public void onItemClicked(View sharedElement, Item item) {

    }

    @Override
    public void onBackClick() {
        if (isAdded() && getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void changeToView(int viewId) {
        Bundle bundle = new Bundle();
        PredioPotencial predioPotencial = viewModel.getPredioPotencial();
        bundle.putParcelable(RxBus.PAYLOAD, predioPotencial);
        activity.changeToActivity(viewId, bundle);
    }

    @Override
    public void showMessage(int messageResourceId, int color) {
        activity.showMessage(getString(messageResourceId), color, R.id.coordinator);
    }

    @Override
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
    }
}
