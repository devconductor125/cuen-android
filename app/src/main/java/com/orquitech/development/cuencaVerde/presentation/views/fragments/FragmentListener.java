//Georgi fixed
package com.orquitech.development.cuencaVerde.presentation.views.fragments;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import android.view.View;

public interface FragmentListener {

    void onClose(BaseFragment view);

    void showMessage(String string, int color);

    void changeToView(int viewId);

    void changeToView(int viewId, Bundle bundle, boolean shouldReplace);

    void changeToViewWithSharedElement(View sharedElement, int viewId, Bundle bundle);

    void changeToActivity(int viewId, Bundle bundle);

    void finish();

    FragmentManager getSupportFragmentManager();

    void showMessage(String string, int color, int coordinatorView);

    void showMessage(String string, int color, View container);

    void onBackPressed();

    void showToast(String message);

    void requestFileReadPermission(boolean isForCamera);
}
