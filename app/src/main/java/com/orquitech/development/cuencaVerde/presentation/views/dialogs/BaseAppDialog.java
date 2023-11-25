package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import androidx.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AnimationBaseCallback;
import com.orquitech.development.cuencaVerde.presentation.views.utils.AnimationUtil;

public class BaseAppDialog extends AlertDialog implements AppDialog, DialogInterface.OnDismissListener {

    protected ViewDataBinding binding;
    protected DialogListener listener;
    private ViewsComponent component;

    public BaseAppDialog(@NonNull Context context, @NonNull DialogListener listener) {
        super(context);
        this.listener = listener;
        setOnDismissListener(this);
        component = DaggerViewsComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Window window = getWindow();

        if (window != null && savedInstanceState == null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Rect displayRectangle = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            WindowManager.LayoutParams lp = getLayoutParameters(window, displayRectangle);
            getWindow().setAttributes(lp);

            AnimationUtil.enterReveal(window.getDecorView().getRootView(), CustomDialog.DEFAULT_DIALOG_LOCATION);
        }
    }

    protected WindowManager.LayoutParams getLayoutParameters(Window window, Rect displayRectangle) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (displayRectangle.width() * CustomDialog.DEFAULT_WIDTH_PERCENTAGE);
        /*lp.height = (int) (displayRectangle.height() * CustomDialog.DEFAULT_HEIGHT_PERCENTAGE);*/
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return lp;
    }

    protected ViewsComponent getComponent() {
        return component;
    }

    protected void dismissDialog(AnimationBaseCallback callback) {
        Window window = getWindow();
        if (window != null) {
            AnimationUtil.exitReveal(window.getDecorView().getRootView(), CustomDialog.DEFAULT_DIALOG_LOCATION, () -> {
                dismiss();
                callback.onCompleted();
            });
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        listener.onDismiss();
        listener = null;
    }
}
