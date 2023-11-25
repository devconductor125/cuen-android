package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface AppFormView extends AppView {

    void changeToView(int viewId, int newPosition);

    void onFormSent(Bundle bundle);
}
