//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.base;

import android.content.Context;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public abstract class FormBaseFragment extends BaseFragment {

    protected static final String PARCELABLE = "parcelable";
    private static final String SEND_STATUS = "sendStatus";
    protected static final String DATE_PICKER = "datePicker";

    protected Bundle savedInstanceState;
    protected Bundle argumentsBundle;
    protected ViewDataBinding binding;
    protected boolean isSending;

    @Inject
    public App app;

    @Inject
    public ViewModelsFactory viewModelFactory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogManager.dismissDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        this.savedInstanceState = savedInstanceState;
        this.argumentsBundle = getArguments();
    }

    private void initListener(Context context) {
        try {
            activity = (FormFragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCELABLE, getParcelable());
        outState.putBoolean(SEND_STATUS, isSending);
    }

    protected abstract Parcelable getParcelable();
}
