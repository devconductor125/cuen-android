package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.OfflineDialogBinding;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.OfflineDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class OfflineDialogFragment extends BaseDialogFragment implements OfflineDialogMvvm.View {

    public static final String GETTING_OFFLINE_DATA = "gettingData";

    private DialogListener listener;
    private OfflineDialogBinding binding;
    private OfflineDialogMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static OfflineDialogFragment getInstance(Bundle data) {
        OfflineDialogFragment fragment = new OfflineDialogFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.offline_dialog, null, false);
        viewModel = (OfflineDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.OFFLINE_DIALOG_VIEW_MODEL);

        binding.setViewModel(viewModel);

        Window window = getDialog().getWindow();
        setWindowDrawable(window);

        try {
            listener = (DialogListener) getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedInstanceState != null) {
            boolean isGettingOfflineData = savedInstanceState.getBoolean(GETTING_OFFLINE_DATA);
            if (!isGettingOfflineData) {
                viewModel.getOfflineData();
            }
        } else {
            viewModel.getOfflineData();
        }

        return binding.getRoot();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(GETTING_OFFLINE_DATA, viewModel.isGettingOfflineData());
    }

    protected WindowManager.LayoutParams getLayoutParameters(Window window, Rect displayRectangle) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        return lp;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        this.listener = null;
    }

    @Override
    public void onGotOfflineData() {
        dismissDialog(() -> {
            if (listener != null) {
                listener.onButtonTwo(null);
            }
        });
    }
}
