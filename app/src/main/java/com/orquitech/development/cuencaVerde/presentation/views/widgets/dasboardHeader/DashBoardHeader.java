package com.orquitech.development.cuencaVerde.presentation.views.widgets.dasboardHeader;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Quota;
import com.orquitech.development.cuencaVerde.databinding.DashboardHeaderBinding;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashBoardHeaderMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.BaseAppView;

import javax.inject.Inject;

public class DashBoardHeader extends BaseAppView implements DashBoardHeaderMvvm.View {

    private DashboardHeaderBinding binding;
    private DashBoardHeaderMvvm.ViewModel viewModel;
    private DashBoardHeaderListener listener;

    @Inject
    ViewModelsFactory viewModelFactory;

    public DashBoardHeader(Context context) {
        super(context);
        init(context);
    }

    public DashBoardHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DashBoardHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        if (!isInEditMode()) {
            getComponent().inject(this);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_header, this, true);
            viewModel = (DashBoardHeaderMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.DASH_BOARD_HEADER_VIEW_MODEL);
            binding.setViewModel(viewModel);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewModel.onReadyForSubscriptions();
        viewModel.getQuota();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewModel.clearSubscriptions();
    }

    @Override
    public void openPredioPotencialView() {
        listener.openPredioPotencialView();
    }

    @Override
    public void onSettingsClick() {
        listener.onSettingsClick();
    }

    @Override
    public void onPrediosClick() {
        listener.onPrediosClick();
    }

    @Override
    public void setQuota(Quota quota) {
        double quotaValue = quota.getPercentage();
        if (quotaValue >= 1) {
            quotaValue = 1;
        }
        animateProgressBarTo((int) (quotaValue * 100));
    }

    public void animateProgressBarTo(int value) {
        ObjectAnimator animation = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, value);
        animation.setDuration(1200); //in milliseconds
        animation.setInterpolator(new OvershootInterpolator());
        animation.start();
        animateValue(value, binding.progressBarValue);
    }

    private void animateValue(int finalValue, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, finalValue);
        valueAnimator.setDuration(600);
        valueAnimator.addUpdateListener(valueAnimator1 -> textview.setText(TextUtil.getString(getContext(), R.string.progress_value, valueAnimator.getAnimatedValue().toString())));
        valueAnimator.start();
    }

    public void clearAnimation() {
        binding.progressBar.clearAnimation();
        binding.progressBarValue.clearAnimation();
    }

    public void setListener(DashBoardHeaderListener listener) {
        this.listener = listener;
    }

    public void getQuota() {
        animateProgressBarTo(0);
        viewModel.getQuota();
    }

    @Override
    public void hideQuota() {
        binding.quotaContainer.setVisibility(View.GONE);
    }

    @Override
    public void hidePrediosButtons() {
        binding.prediosButton.setVisibility(View.GONE);
        binding.createPredioButton.setVisibility(View.GONE);
    }
}
