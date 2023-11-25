package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class FormTextFieldViewModel extends BaseViewModel implements FormTextFieldMvvm.ViewModel {

    private final FormTextFieldMvvm.View textField;
    private String value;
    private String hint;

    public FormTextFieldViewModel(AppView view) {
        super(view);
        this.textField = (FormTextFieldMvvm.View) view;
    }

    @Override
    public String getValue() {
        if (value == null) {
            value = "";
        }
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }
}
