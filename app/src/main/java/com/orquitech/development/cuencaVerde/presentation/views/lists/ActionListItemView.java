package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.BaseDialogListItem;
import com.orquitech.development.cuencaVerde.databinding.ActionItemBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel.ActionsDialogMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ActionItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class ActionListItemView extends BaseListView implements ItemView, ActionItemViewMvvm.View {

    private ActionItemBinding binding;
    private ActionItemViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public ActionListItemView(Context context) {
        super(context);
        init(context);
    }

    public ActionListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ActionListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
    }

    @Override
    public void bind(Item item, int position) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.action_item, this, true);
        viewModel = (ActionItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.ACTION_ITEM_VIEW_MODEL, item);
        binding.setViewModel(viewModel);
        setBulletColor(viewModel.getBulletColor());
    }

    @Override
    public void onClick(BaseDialogListItem item) {
        ((ActionsDialogMvvm.View) parentView).onItemClicked(item);
    }

    private void setBulletColor(String color) {
        GradientDrawable drawable = (GradientDrawable) binding.bullet.getDrawable();
        if (!TextUtils.isEmpty(color) && drawable != null) {
            binding.bullet.setVisibility(VISIBLE);
            try {
                drawable.setColor(Color.parseColor(color));
            } catch (Exception ex) {
                drawable.setColor(ContextCompat.getColor(getContext(), R.color.black_2));
            }
            binding.bullet.setBackground(drawable);
        }
    }
}
