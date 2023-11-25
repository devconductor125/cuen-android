package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.CustomDialogBinding;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.CustomDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.CustomDialogViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AnimationBaseCallback;
import com.orquitech.development.cuencaVerde.presentation.views.utils.AnimationUtil;

import javax.inject.Inject;

public abstract class CustomDialog extends AlertDialog implements AppDialog, CustomDialogMvvm.View {

    public static final float DEFAULT_WIDTH_PERCENTAGE = 0.85f;
    public static final float DEFAULT_HEIGHT_PERCENTAGE = 0.85f;
    private CustomDialogBinding binding;
    protected DialogListener listener;
    public static final float[] DEFAULT_DIALOG_LOCATION = new float[]{0.5f, 0.5f};
    private ViewsComponent component;
    protected float width;

    @Inject
    App app;

    public CustomDialog(@NonNull Context context, @NonNull DialogListener listener) {
        super(context);
        this.width = DEFAULT_WIDTH_PERCENTAGE;
        this.listener = listener;

        component = DaggerViewsComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();

        component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.custom_dialog, null, false);

        CustomDialogViewModel viewModel = new CustomDialogViewModel(this);
        binding.setViewModel(viewModel);

        setContentView(binding.getRoot());

        Window window = getWindow();

        if (window != null && savedInstanceState == null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Rect displayRectangle = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(window.getAttributes());
            lp.width = (int) (displayRectangle.width() * width);
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(lp);

            AnimationUtil.enterReveal(window.getDecorView().getRootView(), DEFAULT_DIALOG_LOCATION);
        }
    }

    protected void setDialogTitle(int stringResource) {
        this.binding.text.setText(TextUtil.getString(getContext(), stringResource));
    }

    @Override
    public void onButtonOne() {
        listener.onButtonOne();
        dismissDialog(() -> listener.onButtonOne());
    }

    @Override
    public void onButtonTwo() {
        dismissDialog(() -> listener.onButtonTwo(null));
    }

    private void dismissDialog(AnimationBaseCallback callback) {
        Window window = getWindow();
        if (window != null) {
            AnimationUtil.exitReveal(window.getDecorView().getRootView(), DEFAULT_DIALOG_LOCATION, () -> {
                dismiss();
                callback.onCompleted();
            });
        }
    }
}
