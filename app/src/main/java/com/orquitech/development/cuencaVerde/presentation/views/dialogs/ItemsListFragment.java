package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.ActionsDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;

public class ItemsListFragment extends BaseDialogFragment {

    public static final String ITEMS_TYPE = "itemsType";
    public static final String PARENT_PAYLOAD = "parentPayload";
    private ListDialogListener listener;
    private int itemsType;
    private Dialog dialog;
    private Parcelable payload;

    public static ItemsListFragment getInstance(Bundle data) {
        ItemsListFragment fragment = new ItemsListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            listener = (ListDialogListener) getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog = dialogManager.getDialog(getContext(), DialogsFactory.ACTION_SELECTOR, new DialogListenerAdapter() {
            @Override
            public void onItemClick(Bundle bundle) {
                bundle.putInt(ITEMS_TYPE, itemsType);
                if (payload instanceof Action) {
                    bundle.putParcelable(PARENT_PAYLOAD, payload);
                }
                listener.onItemClick(bundle);
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            Parcelable payload = bundle.getParcelable(RxBus.PAYLOAD);
            if (dialog instanceof ActionsDialogMvvm.View && payload != null) {
                ((ActionsDialogMvvm.View) dialog).setPayload(payload);
            }
            this.payload = payload;
        }
        if (savedInstanceState != null) {
            itemsType = savedInstanceState.getInt(ITEMS_TYPE);
            payload = savedInstanceState.getParcelable(PARENT_PAYLOAD);
        }
        if (dialog instanceof ActionSelectorDialog) {
            ((ActionSelectorDialog) dialog).setItemsType(itemsType);
        }
        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ITEMS_TYPE, itemsType);
        outState.putParcelable(PARENT_PAYLOAD, payload);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialogManager.dismissDialog();
        this.listener = null;
        if (dialog != null && dialog instanceof ActionSelectorDialog) {
            ((ActionSelectorDialog) dialog).onDismiss(null);
        }
    }

    public void setItemsType(int itemsType) {
        this.itemsType = itemsType;
    }
}
