package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.databinding.SamplingPointItemBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseActivity;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.TaskItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class SamplingPointListItemView extends BaseListView implements ItemView, TaskItemViewMvvm.View {

    private SamplingPointItemBinding binding;
    private TaskItemViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public SamplingPointListItemView(Context context) {
        super(context);
        init(context);
    }

    public SamplingPointListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SamplingPointListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.sampling_point_item, this, true);
    }

    @Override
    public void bind(Item item, int position) {
        if (item instanceof SamplingPointForm) {
            viewModel = (TaskItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.SAMPLING_POINT_ITEM_VIEW_MODEL, item);
            binding.setViewModel(viewModel);
        }
        if (item instanceof ErosiveProcessForm) {
            viewModel = (TaskItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.EROSIVE_PROCESS_ITEM_VIEW_MODEL, item);
            binding.setViewModel(viewModel);
        }
    }

    @Override
    public void onClick(Item item) {
        ((ItemList) parentView).onItemClicked(item);
    }

    @Override
    public Drawable getDueDateBackground(int dueDateStatus) {
        Drawable result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_green);
        switch (dueDateStatus) {
            case Task.ON_TIME:
                result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_green);
                break;
            case Task.DELAYED:
                result = ContextCompat.getDrawable(getContext(), R.drawable.task_status_badge_red);
                break;
        }
        return result;
    }

    @Override
    public String getDueDateText(int dueDateStatus) {
        String result = "";
        switch (dueDateStatus) {
            case Task.ON_TIME:
                result = getContext().getString(R.string.on_time);
                break;
            case Task.DELAYED:
                result = getContext().getString(R.string.delayed);
                break;
        }
        return result;
    }

    @Override
    public void onPhotographyRegistry(BaseItem item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, item);
        ((BaseActivity) ((BaseFragment) parentView).getContext()).changeToActivity(AppViewsFactory.PHOTOGRAPHY_REGISTRY_VIEW, bundle);
    }
}
