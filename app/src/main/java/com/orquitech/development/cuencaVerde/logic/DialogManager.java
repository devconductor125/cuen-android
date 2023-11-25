package com.orquitech.development.cuencaVerde.logic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;

public interface DialogManager {

    void showDialog(Context context, int dialogType, DialogListener listener);

    Dialog getDialog(Context context, int dialogType, DialogListener listener);

    void dismissDialog();

    BaseDialogFragment getDialogFragment(int dialogType, Bundle bundle);
}
