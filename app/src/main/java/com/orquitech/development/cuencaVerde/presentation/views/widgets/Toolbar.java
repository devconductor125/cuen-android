package com.orquitech.development.cuencaVerde.presentation.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.ToolBarBinding;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ToolbarMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ToolbarViewModel;

public class Toolbar extends FrameLayout implements ToolbarMvvm.View {

    private String title;
    private Drawable iconLeft;
    private Drawable iconRightMid;
    private Drawable iconRightEnd;
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int textColor;

    private ToolBarBinding binding;
    private ToolBarListener listener;

    public Toolbar(Context context) {
        super(context);
        init(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Toolbar);
            if (typedArray != null) {
                final int arraySize = typedArray.getIndexCount();

                for (int i = 0; i < arraySize; ++i) {

                    int attr = typedArray.getIndex(i);

                    switch (attr) {
                        case R.styleable.Toolbar_custom_title:
                            title = typedArray.getString(attr);
                            break;
                        case R.styleable.Toolbar_start_icon:
                            iconLeft = typedArray.getDrawable(attr);
                            break;
                        case R.styleable.Toolbar_right_mid_icon:
                            iconRightMid = typedArray.getDrawable(attr);
                            break;
                        case R.styleable.Toolbar_right_end_icon:
                            iconRightEnd = typedArray.getDrawable(attr);
                            break;
                        case R.styleable.Toolbar_background_color:
                            backgroundColor = typedArray.getColor(attr, -1);
                            break;
                        case R.styleable.Toolbar_background_drawable:
                            backgroundDrawable = typedArray.getDrawable(attr);
                            break;
                        case R.styleable.Toolbar_text_color:
                            textColor = typedArray.getColor(attr, -1);
                            break;
                    }
                }
                typedArray.recycle();
            }
        }
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(inflater, R.layout.tool_bar, this, true);
            ToolbarMvvm.ViewModel viewModel = new ToolbarViewModel(this);
            binding.setViewModel(viewModel);
            buildViews();
        }
    }

    private void buildViews() {
        setToolbarTitle();
        setIconLeft();
        setIconRightEnd();
        setIconMidRightEnd();
        setBackgroundColor(backgroundColor);
        binding.mainContainer.setBackground(backgroundDrawable);
        setTextColor(textColor);
    }

    public void setTitle(String title) {
        this.title = title;
        setToolbarTitle();
    }

    private void setToolbarTitle() {
        if (!TextUtils.isEmpty(title)) {
            binding.title.setText(title);
        }
    }

    private void setIconLeft() {
        if (iconLeft != null) {
            binding.imageLeft.setImageDrawable(iconLeft);
        } else {
            binding.imageLeft.setVisibility(GONE);
        }
    }

    public void setIconRightEnd(Drawable drawable) {
        if (drawable != null && iconRightEnd != null) {
            binding.imageRightEnd.setImageDrawable(drawable);
        }
    }

    private void setIconRightEnd() {
        if (iconRightEnd != null) {
            binding.imageRightEnd.setImageDrawable(iconRightEnd);
        } else {
            binding.imageRightEnd.setVisibility(GONE);
        }
    }

    private void setIconMidRightEnd() {
        if (iconRightMid != null) {
            binding.imageMidEnd.setImageDrawable(iconRightMid);
        } else {
            binding.imageMidEnd.setVisibility(GONE);
        }
    }

    @Override
    public void setListener(ToolBarListener listener) {
        this.listener = listener;
    }

    @Override
    public void onLeftIconClick() {
        if (listener != null) {
            listener.onToolbarLeftIconClick();
        }
    }

    @Override
    public void onRightMidIconClick() {
        if (listener != null) {
            listener.onToolbarMidIconClick();
        }
    }

    @Override
    public void onRightEndIconClick() {
        if (listener != null) {
            listener.onToolbarRightEndIconClick();
        }
    }

    public void setTextColor(int textColor) {
        if (textColor != 0) {
            binding.title.setTextColor(textColor);
        }
    }

    public void animateIconRightEndWithRotation() {
        RotateAnimation anim = new RotateAnimation(0, (360 * 4),
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(8000);
        anim.setRepeatCount(Animation.INFINITE);
        binding.imageRightEnd.startAnimation(anim);
    }

    public void stopIconRightEndAnimation() {
        binding.imageRightEnd.clearAnimation();
    }

    public void hideRightEndButton() {
        binding.imageRightEnd.setVisibility(GONE);
    }

    public void onLoadingData() {
        binding.imageRightEnd.setVisibility(INVISIBLE);
        binding.loadingSpinnerContainer.setVisibility(VISIBLE);
    }

    public void onFinishedLoadingData() {
        binding.imageRightEnd.setVisibility(VISIBLE);
        binding.loadingSpinnerContainer.setVisibility(GONE);
    }

    public void showErrorMessage(String message) {
        binding.topMessageBar.setVisibility(View.VISIBLE);
        binding.topMessageBar.setText(message);
    }

    public void hideErrorMessage() {
        binding.topMessageBar.setVisibility(View.GONE);
    }
}
