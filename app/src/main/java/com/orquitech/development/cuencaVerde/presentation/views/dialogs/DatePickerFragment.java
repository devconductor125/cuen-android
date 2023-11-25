package com.orquitech.development.cuencaVerde.presentation.views.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.logic.utils.DateUtils;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseDialogFragment;

public class DatePickerFragment extends BaseDialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment getInstance(Bundle data) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = 0;
        int month = 0;
        int day = 0;
        Bundle bundle = getArguments();
        if (bundle != null) {
            year = Integer.parseInt(bundle.getString(YEAR) != null ? bundle.getString(YEAR) : "0");
            month = Integer.parseInt(bundle.getString(MONTH) != null ? bundle.getString(MONTH) : "0");
            day = Integer.parseInt(bundle.getString(DAY) != null ? bundle.getString(DAY) : "0");
        }
        if (year == 0 && month == 0 && day == 0) {
            AppDate date = DateUtils.getCurrentDate();
            year = Integer.parseInt(date.getYear());
            month = Integer.parseInt(date.getMonth());
            day = Integer.parseInt(date.getDay());
        }
        return new DatePickerDialog(getActivity(), R.style.DatePicker, this, year, (month - 1), day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        listener.onDateSet(view, year, month, day);
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.listener = null;
        super.onDismiss(dialog);
    }
}
