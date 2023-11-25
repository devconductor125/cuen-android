package com.orquitech.development.cuencaVerde.presentation.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.AutoCompleteFormTextFieldBinding;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AutoCompleteFormTextFieldMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.adapters.TextWatcherAdapter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class AutoCompleteFormTextField extends BaseAppView implements AutoCompleteFormTextFieldMvvm.View {

    public static final String FIELD_TYPE_NUMBER = "number";
    public static final String FIELD_TYPE_EMAIL = "email";
    public static final String FIELD_TYPE_PASSWORD = "password";
    private String fieldType = "";
    private String hint;
    private boolean editable = true;
    private boolean isMandatory = false;
    private boolean mandatoryAlertOn = false;

    protected AutoCompleteFormTextFieldBinding binding;
    private AutoCompleteFormTextFieldMvvm.ViewModel viewModel;
    protected CompositeDisposable disposables;
    private TextWatcher textChangedListener;

    @Inject
    ViewModelsFactory viewModelFactory;

    @Inject
    Bus bus;

    public AutoCompleteFormTextField(Context context) {
        super(context);
        init(context);
    }

    public AutoCompleteFormTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public AutoCompleteFormTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormTextField);
            if (typedArray != null) {
                final int arraySize = typedArray.getIndexCount();

                for (int i = 0; i < arraySize; ++i) {

                    int attr = typedArray.getIndex(i);

                    switch (attr) {
                        case R.styleable.FormTextField_hint:
                            hint = typedArray.getString(attr);
                            break;
                        case R.styleable.FormTextField_fieldType:
                            fieldType = typedArray.getString(attr);
                            break;
                        case R.styleable.FormTextField_editable:
                            editable = typedArray.getBoolean(attr, true);
                            break;
                        case R.styleable.FormTextField_mandatory:
                            isMandatory = typedArray.getBoolean(attr, true);
                            break;
                    }
                }
                typedArray.recycle();
            }
        }
    }

    protected void init(Context context) {
        if (!isInEditMode()) {
            disposables = new CompositeDisposable();
            getComponent().inject(this);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(inflater, R.layout.auto_complete_form_text_field, this, true);
            viewModel = (AutoCompleteFormTextFieldMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL);
            viewModel.setHint(hint);
            binding.setViewModel(viewModel);
            setEditable();
            subscribeToObservables();
            initListeners();
        }
    }

    private void initListeners() {
        textChangedListener = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (mandatoryAlertOn) {
                    mandatoryAlertOn = false;
                    Drawable drawable = getResources().getDrawable(R.drawable.form_text_field_rounded_outline);
                    binding.formFieldContainer.setBackground(drawable);
                    binding.textFieldContainer.setHintTextAppearance(R.style.TextLabelHint);
                }
            }
        };
        binding.textField.addTextChangedListener(textChangedListener);
    }

    private void subscribeToObservables() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.TEXT_FIELD_VALIDATION:
                            showIsMandatoryAlert();
                            break;
                    }
                })
                .subscribe());
    }

    private void showIsMandatoryAlert() {
        if (isMandatory && TextUtils.isEmpty(binding.textField.getText())) {
            mandatoryAlertOn = true;
            Drawable drawable = getResources().getDrawable(R.drawable.form_text_field_rounded_outline_alert);
            binding.formFieldContainer.setBackground(drawable);
            binding.textFieldContainer.setHintTextAppearance(R.style.TextLabelHintError);
        }
    }

    public void setEditable(boolean isEditable) {
        binding.textField.setEnabled(isEditable);
        if (!isEditable) {
            binding.formFieldContainer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.form_text_field_rounded_outline_disabled));
        } else {
            binding.formFieldContainer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.form_text_field_rounded_outline));
        }
    }

    private void setEditable() {
        binding.textField.setEnabled(editable);
        if (!editable) {
            binding.formFieldContainer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.form_text_field_rounded_outline_disabled));
        } else {
            binding.formFieldContainer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.form_text_field_rounded_outline));
        }
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
        setUpFieldType();
    }

    private void setUpFieldType() {
        switch (fieldType) {
            case FIELD_TYPE_NUMBER:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                break;
            case FIELD_TYPE_EMAIL:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case FIELD_TYPE_PASSWORD:
                binding.textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                break;
        }
    }

    @Override
    public String getValue() {
        return viewModel.getValue();
    }

    @Override
    public void setValue(String value) {
        viewModel.setValue(value);
        binding.setViewModel(viewModel);
    }

    public TextView getEditText() {
        return binding.textField;
    }

    public void setError(String error) {
        binding.textFieldContainer.setError(error);
    }

    @Override
    protected void onDetachedFromWindow() {
        binding.textField.removeTextChangedListener(textChangedListener);
        disposables.clear();
        textChangedListener = null;
        super.onDetachedFromWindow();
    }

    @Override
    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        binding.textField.setThreshold(1);
        binding.textField.setAdapter(adapter);
    }

    public void setData(String[] municipalitiesArray) {
        viewModel.setAutoCompleteData(municipalitiesArray);
    }
}
