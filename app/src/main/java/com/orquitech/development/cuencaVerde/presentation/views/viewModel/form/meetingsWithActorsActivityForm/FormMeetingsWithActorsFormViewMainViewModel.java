package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormMeetingsWithActorsFormViewMainViewModel extends BaseViewModel implements MeetingsWithActorsFormViewMvvm.ViewModel {

    private final MeetingsWithActorsFormViewMvvm.View view;

    public FormMeetingsWithActorsFormViewMainViewModel(AppView view) {
        super(view);
        this.view = (MeetingsWithActorsFormViewMvvm.View) view;
    }

    @Override
    public void onBarButtonClick(int position) {

    }

    @Override
    public void saveParcelable(Parcelable parcelable) {
        prediosManager.saveMeetingsWithActorsForm((MeetingsWithActorsForm) parcelable, view.getTask());
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
