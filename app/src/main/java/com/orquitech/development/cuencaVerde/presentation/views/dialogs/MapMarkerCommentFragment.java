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
import com.orquitech.development.cuencaVerde.databinding.MapMarkerCommentBinding;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.MapMarkerCommentDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class MapMarkerCommentFragment extends BaseDialogFragment implements MapMarkerCommentDialogMvvm.View {

    private DialogListener listener;
    private MapMarkerCommentBinding binding;
    private MapMarkerCommentDialogMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public static MapMarkerCommentFragment getInstance(Bundle data) {
        MapMarkerCommentFragment fragment = new MapMarkerCommentFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.map_marker_comment, null, false);
        viewModel = (MapMarkerCommentDialogMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAP_MARKER_COMMENT_DIALOG_VIEW_MODEL);
        binding.setViewModel(viewModel);

        Window window = getDialog().getWindow();
        setWindowDrawable(window);

        return binding.getRoot();
    }

    protected WindowManager.LayoutParams getLayoutParameters(Window window, Rect displayRectangle) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (displayRectangle.width() * CustomDialog.DEFAULT_WIDTH_PERCENTAGE);
        /*lp.height = (int) (displayRectangle.height() * CustomDialog.DEFAULT_HEIGHT_PERCENTAGE);*/
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return lp;
    }

    @Override
    public void saveComment(String comment) {
        dismissDialog(() -> listener.onMidButtonClick(comment));
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        this.listener = null;
    }
}
