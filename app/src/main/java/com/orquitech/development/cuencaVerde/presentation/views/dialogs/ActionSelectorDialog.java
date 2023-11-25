package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.BaseDialogListItem;
import com.orquitech.development.cuencaVerde.databinding.ActionsDialogBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.ActionsDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.utils.LayoutManagerUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class ActionSelectorDialog extends BaseAppDialog implements AppDialog, ActionsDialogMvvm.View, DialogInterface.OnDismissListener {

    private static final String BUNDLE_ACTIONS_RECYCLER = "actionsListView";
    private int itemsType;
    private int stringResource = R.string.action_dialog_title;
    private ActionsDialogMvvm.ViewModel viewModel;
    private Parcelable payload;

    @Inject
    ViewModelsFactory viewModelFactory;

    public ActionSelectorDialog(@NonNull Context context, @NonNull DialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.actions_dialog, null, false);
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        viewModel = (ActionsDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.ACTION_SELECTOR_DIALOG_VIEW_MODEL);
        ((ActionsDialogBinding) binding).setViewModel(viewModel);
        setDialogTitle();
    }

    private void setDialogTitle() {
        ((ActionsDialogBinding) binding).text.setText(TextUtil.getString(getOwnerActivity(), stringResource));
    }

    private void setDialogTitleResource(int stringResource) {
        this.stringResource = stringResource;
    }

    @Override
    public void onGotItems() {

    }

    @Override
    public Parcelable getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(Parcelable payload) {
        this.payload = payload;
    }

    @Override
    public void onListAdapterReady(ListAdapter adapter) {
        RecyclerView.LayoutManager layoutManager = LayoutManagerUtil.getGridListLayoutManager(getOwnerActivity(), null, BUNDLE_ACTIONS_RECYCLER);
        ((ActionsDialogBinding) binding).actionsList.setLayoutManager(layoutManager);
        ((ActionsDialogBinding) binding).actionsList.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onGetItemsError() {

    }

    @Override
    public void onItemClicked(BaseDialogListItem item) {
        Bundle bundle = new Bundle();
        if (item != null) {
            bundle.putParcelable(RxBus.PAYLOAD, item);
        }
        dismissDialog(() -> listener.onItemClick(bundle));
    }

    @Override
    public int getItemsType() {
        return itemsType;
    }

    public void setItemsType(int itemsType) {
        this.itemsType = itemsType;
        int resource = R.string.action_dialog_title;
        switch (itemsType) {
            case DialogsFactory.MAP_ACTIONS:
                resource = R.string.map_actions;
                break;
            case DialogsFactory.MAP_ACTIONS_POINTS:
                resource = R.string.map_practice;
                break;
            case DialogsFactory.MAP_MATERIALS:
                resource = R.string.map_materials;
                break;
            case DialogsFactory.PROPERTY_CORRELATION_PICKER:
                resource = R.string.property_correlation;
                break;
            case DialogsFactory.ACTION_TYPE:
            case DialogsFactory.POINT_ACTION_TYPE:
                resource = R.string.action_type_question;
                break;
        }
        setDialogTitleResource(resource);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        viewModel.clearSubscriptions();
    }
}
