package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.databinding.FeatureDetailsBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class FeatureDetailsFragment extends BaseDialogFragment implements FeatureDetailsDialogMvvm.View {

    private DialogListener listener;
    private FeatureDetailsBinding binding;
    private FeatureDetailsDialogMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static FeatureDetailsFragment getInstance(Bundle data) {
        FeatureDetailsFragment fragment = new FeatureDetailsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.feature_details, null, false);
        viewModel = (FeatureDetailsDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FEATURE_DETAILS_DIALOG_VIEW_MODEL);

        Bundle argumentsBundle = getArguments();
        if (argumentsBundle != null) {
            Feature feature = argumentsBundle.getParcelable(RxBus.PAYLOAD);
            if (feature != null) {
                viewModel.setFeature(feature);
                binding.setViewModel(viewModel);
            }
        }

        Window window = getDialog().getWindow();
        setWindowDrawable(window);

        return binding.getRoot();
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
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
}
