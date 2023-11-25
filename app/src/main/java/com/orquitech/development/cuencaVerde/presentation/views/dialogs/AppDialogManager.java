package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.logic.DialogManager;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;

public class AppDialogManager implements DialogManager {

    private DialogsFactory dialogsFactory;
    private AppDialog dialog;

    public AppDialogManager(DialogsFactory dialogsFactory) {
        this.dialogsFactory = dialogsFactory;
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showDialog(Context context, int dialogType, DialogListener listener) {
        dialog = dialogsFactory.getDialog(dialogType, context, listener);
        dialog.show();
    }

    @Override
    public Dialog getDialog(Context context, int dialogType, DialogListener listener) {
        return (Dialog) dialogsFactory.getDialog(dialogType, context, listener);
    }

    @Override
    public BaseDialogFragment getDialogFragment(int dialogType, Bundle bundle) {
        return dialogsFactory.getDialogFragment(dialogType, bundle);
    }
}
