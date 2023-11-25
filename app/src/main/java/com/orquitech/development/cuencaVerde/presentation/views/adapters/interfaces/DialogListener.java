package com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

public interface DialogListener {

    void onButtonOne();

    void onButtonTwo(Bundle bundle);

    void onItemClick(Bundle bundle);

    void onDismiss();

    void onMidButtonClick(Parcelable data);

    void onMidButtonClick(Serializable data);
}
