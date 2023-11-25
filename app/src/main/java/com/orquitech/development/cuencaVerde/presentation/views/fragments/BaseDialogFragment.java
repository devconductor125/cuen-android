//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.Window;
import android.view.WindowManager;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerDialogFragmentComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DialogFragmentComponent;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.AppDialogManager;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.CustomDialog;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AnimationBaseCallback;

public abstract class BaseDialogFragment extends DialogFragment {

    protected AppDialogManager dialogManager;
    private DialogFragmentComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerDialogFragmentComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();
        dialogManager = new AppDialogManager(new DialogsFactory());
    }


    protected DialogFragmentComponent getComponent() {
        return component;
    }

    protected void dismissDialog(AnimationBaseCallback callback) {
        dismiss();
        callback.onCompleted();
    }

    protected void setWindowDrawable(Window window) {
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Rect displayRectangle = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            WindowManager.LayoutParams lp = getLayoutParameters(window, displayRectangle);
            window.setAttributes(lp);
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
}
