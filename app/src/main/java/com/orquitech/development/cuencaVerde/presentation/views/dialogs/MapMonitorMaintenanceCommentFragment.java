package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.databinding.MapMonitorMaintenanceCommentBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapExecutionActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapMonitorAndMaintenanceActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMonitorMaintenanceCommentDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class MapMonitorMaintenanceCommentFragment extends BaseDialogFragment implements ItemList, MapMonitorMaintenanceCommentDialogMvvm.View, AppListView {

    private static final String BUNDLE_MONITOR_AND_MAINTENANCE_PHOTO_RECYCLER = "monitorAndMaintenancePhotosListView";
    private DialogListener listener;
    private MapMonitorMaintenanceCommentBinding binding;
    private MapMonitorMaintenanceCommentDialogMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static MapMonitorMaintenanceCommentFragment getInstance(Bundle data) {
        MapMonitorMaintenanceCommentFragment fragment = new MapMonitorMaintenanceCommentFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.map_monitor_maintenance_comment, null, false);
        viewModel = (MapMonitorMaintenanceCommentDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAP_MONITOR_MAINTENANCE_DIALOG_VIEW_MODEL);
        binding.setViewModel(viewModel);

        Bundle argumentsBundle = getArguments();
        if (argumentsBundle != null) {
            MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint = argumentsBundle.getParcelable(RxBus.PAYLOAD);
            if (monitorAndMaintenanceCommentPoint != null) {
                viewModel.setMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint);
                binding.comment.setSelection(binding.comment.getText().length());
            }
        }

        Window window = getDialog().getWindow();
        setWindowDrawable(window);

        return binding.getRoot();
    }

    @Override
    public void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        dismissDialog(() -> listener.onMidButtonClick(monitorAndMaintenanceCommentPoint));
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        viewModel.clearSubscriptions();
        viewModel.deleteMonitorAndMaintenanceCommentPoint();
        super.onDismiss(dialog);
        this.listener = null;
    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        RecyclerView.LayoutManager layoutManager = LayoutManagerUtil.getHorizontalListLayoutManager(getDialog().getOwnerActivity(), null, BUNDLE_MONITOR_AND_MAINTENANCE_PHOTO_RECYCLER);
        binding.imagesList.setLayoutManager(layoutManager);
        binding.imagesList.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGotItems() {

    }

    @Override
    public void onGetItemsError() {

    }

    @Override
    public void onItemClicked(Item item) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).requestFileReadPermission(true);
        }
    }

    @Override
    public void onItemClicked(View sharedElement, Item item) {

    }

    @Override
    public void onPhotographyRegistry(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        if (monitorAndMaintenanceCommentPoint.getMonitorAndMaintenanceId().contains("execution")) {
            if (!monitorAndMaintenanceCommentPoint.getId().contains("execution")) {
                monitorAndMaintenanceCommentPoint.setId("execution_" + monitorAndMaintenanceCommentPoint.getId());
            }
        } else if (!monitorAndMaintenanceCommentPoint.getId().contains("monitoreo_")) {
            monitorAndMaintenanceCommentPoint.setId("monitoreo_" + monitorAndMaintenanceCommentPoint.getId());
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, monitorAndMaintenanceCommentPoint);
        try {
            if (getContext() instanceof MapMonitorAndMaintenanceActivity) {
                ((MapMonitorAndMaintenanceActivity) getContext()).changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
            } else if (getContext() instanceof MapExecutionActivity) {
                ((MapExecutionActivity) getContext()).changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
