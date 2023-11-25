//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.maintenanceControl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.FormFragmentListener;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.forms.base.FormBaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl.FormMaintenanceControlViewMvvm;

public abstract class FormMaintenanceControlBaseFragment extends FormBaseFragment implements FormMaintenanceControlViewMvvm.View {

    protected FormMaintenanceControlViewMvvm.ViewModel viewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (FormMaintenanceControlViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.FORM_PREDIO_FOLLOW_UP_VIEW_MODEL);

        Task task = argumentsBundle.getParcelable(RxBus.PAYLOAD);
        viewModel.setTask(task);

        setViewModel();
        viewModel.onReadyForSubscriptions();
        return binding.getRoot();
    }

    private void initListener(Context context) {
        try {
            activity = (FormFragmentListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.clearSubscriptions();
    }

    protected abstract void setViewModel();

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void onViewModelUpdated() {
    }

    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void changeToView(int viewId) {

    }

    @Override
    public int getNextViewId() {
        return 0;
    }

    @Override
    public MaintenanceControl getMaintenanceControl() {
        return viewModel.getMaintenanceControl();
    }

    @Override
    public Task getTask() {
        return viewModel.getTask();
    }

    @Override
    public boolean canGoToNextScreen() {
        return false;
    }

    @Override
    public void onSendParcelableTriggered() {
        if (getActivity() != null) {
            Intent resultIntent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();
        }
    }

    @Override
    protected Parcelable getParcelable() {
        return viewModel.getMaintenanceControl();
    }
}
