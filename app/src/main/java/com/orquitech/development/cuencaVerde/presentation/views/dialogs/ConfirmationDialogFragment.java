package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.CustomDialogBinding;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.CustomDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class ConfirmationDialogFragment extends BaseDialogFragment implements CustomDialogMvvm.View {

    public static final String TITLE = "title";

    private DialogListener listener;
    private CustomDialogBinding binding;
    private CustomDialogMvvm.ViewModel viewModel;
    private String title;

    @Inject
    ViewModelsFactory viewModelFactory;
    private Bundle bundle;

    public static ConfirmationDialogFragment getInstance(Bundle data) {
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.custom_dialog, null, false);
        viewModel = (CustomDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.CONFIRMATION_DIALOG_VIEW_MODEL);

        binding.setViewModel(viewModel);

        Window window = getDialog().getWindow();
        setWindowDrawable(window);

        try {
            listener = (DialogListener) getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedInstanceState != null) {
            title = savedInstanceState.getString(TITLE);
        }

        if (!TextUtils.isEmpty(title)) {
            binding.text.setText(title);
        }

        return binding.getRoot();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE, title);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        this.listener = null;
    }

    @Override
    public String getString(String content, int resource) {
        String result = "";
        if (getActivity() != null) {
            result = getActivity().getString(resource, content);
        }
        return result;
    }

    @Override
    public void onButtonOne() {
        dismissDialog(() -> {
            if (listener != null) {
                listener.onButtonOne();
            }
        });
    }

    @Override
    public void onButtonTwo() {
        dismissDialog(() -> {
            if (listener != null) {
                listener.onButtonTwo(bundle);
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
