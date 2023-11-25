package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;

public class SaveTrackDialog extends CustomDialog {

    public SaveTrackDialog(Context context, DialogListener dialog) {
        super(context, dialog);
        this.width = 0.65f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogTitle(R.string.save_track_question);
    }

    @Override
    public String getString(String content, int resource) {
        return null;
    }
}
