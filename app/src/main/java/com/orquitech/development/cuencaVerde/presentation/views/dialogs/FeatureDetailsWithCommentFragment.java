package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.DialogInterface;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.databinding.FeatureDetailsWithComment2Binding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.FeatureDetailsWithCommentDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class FeatureDetailsWithCommentFragment extends BaseDialogFragment implements FeatureDetailsWithCommentDialogMvvm.View {

    private DialogListener listener;
    private FeatureDetailsWithComment2Binding binding;
    private FeatureDetailsWithCommentDialogMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static FeatureDetailsWithCommentFragment getInstance(Bundle data) {
        FeatureDetailsWithCommentFragment fragment = new FeatureDetailsWithCommentFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.feature_details_with_comment_2, null, false);
        viewModel = (FeatureDetailsWithCommentDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FEATURE_DETAILS_WITH_COMMENT_DIALOG_VIEW_MODEL);

        Bundle argumentsBundle = getArguments();
        if (argumentsBundle != null) {
            Feature feature = argumentsBundle.getParcelable(RxBus.PAYLOAD);
            Task task = argumentsBundle.getParcelable(RxBus.TASK);
            if (feature != null) {
                viewModel.setFeature(feature);
            }
            if (feature != null) {
                viewModel.setTask(task);
            }
            binding.setViewModel(viewModel);
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
        viewModel.clearSubscriptions();
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
    public void onViewModelUpdated() {
        binding.setViewModel(viewModel);
    }
}
