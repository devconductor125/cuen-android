package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;

public class ProgressBarDialog extends BaseAppDialog implements AppDialog, DialogInterface.OnDismissListener {

    public ProgressBarDialog(@NonNull Context context, @NonNull DialogListener listener) {
        super(context, listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.progress_bar_dialog, null, false);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected WindowManager.LayoutParams getLayoutParameters(Window window, Rect displayRectangle) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) getContext().getResources().getDimension(R.dimen.dimen_xxxxlarge);
        lp.height = (int) getContext().getResources().getDimension(R.dimen.dimen_xxxxlarge);
        return lp;
    }
}
