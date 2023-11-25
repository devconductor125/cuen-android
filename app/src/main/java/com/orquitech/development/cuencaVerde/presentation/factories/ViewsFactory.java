package com.orquitech.development.cuencaVerde.presentation.factories;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface ViewsFactory {

    ItemView getItemView(int type, AppListView parentView);

    BaseFragment getAppFragmentView(int type, Bundle bundle);

    Class getActivityClass(int type);
}
