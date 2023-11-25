package com.orquitech.development.cuencaVerde.presentation.views.utils;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Editable;
import android.widget.ImageView;

import com.orquitech.development.cuencaVerde.presentation.views.widgets.AutoCompleteFormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.FormTextField;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.Toolbar;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.adapters.TextWatcherAdapter;
import com.squareup.picasso.Picasso;

public class Binding {

    @BindingAdapter(value = "valueAttrChanged")
    public static void setListener(FormTextField textField, final InverseBindingListener listener) {
        if (listener != null) {
            textField.getEditText().addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter(value = "valueAttrChanged")
    public static void setListener(AutoCompleteFormTextField textField, final InverseBindingListener listener) {
        if (listener != null) {
            textField.getEditText().addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter("value")
    public static void setValue(FormTextField textField, String value) {
        if (value == null) {
            value = "";
        }
        if (!textField.getValue().equals(value)) {
            textField.setValue(value);
        }
    }

    @BindingAdapter("custom_title")
    public static void setValue(Toolbar toolbar, String value) {
        if (value == null) {
            value = "";
        }
        toolbar.setTitle(value);
    }

    @InverseBindingAdapter(attribute = "value")
    public static String getValue(FormTextField textField) {
        return textField.getValue();
    }

    @BindingAdapter("value")
    public static void setValue(AutoCompleteFormTextField textField, String value) {
        if (value == null) {
            value = "";
        }
        if (!textField.getValue().equals(value)) {
            textField.setValue(value);
        }
    }

    @InverseBindingAdapter(attribute = "value")
    public static String getValue(AutoCompleteFormTextField textField) {
        return textField.getValue();
    }

    @BindingAdapter("imageBitmap")
    public static void loadImage(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    @BindingAdapter({"cuenca_photo"})
    public static void setImageUrl(ImageView imageView, final Uri uri) {
        Picasso.get().load(uri)
                .config(Bitmap.Config.RGB_565)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
