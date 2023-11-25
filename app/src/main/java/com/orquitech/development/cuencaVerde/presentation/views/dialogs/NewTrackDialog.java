package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.content.Context;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;

public class NewTrackDialog extends CustomDialog {

    public NewTrackDialog(Context context, DialogListener dialog) {
        super(context, dialog);
        this.width = 0.65f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogTitle(R.string.new_track_question);
    }

    @Override
    public String getString(String content, int resource) {
        return null;
    }
}
