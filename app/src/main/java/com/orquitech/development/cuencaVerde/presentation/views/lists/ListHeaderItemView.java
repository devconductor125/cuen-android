package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.databinding.ListHeaderItemBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ListHeaderItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class ListHeaderItemView extends BaseListView implements ItemView, ListHeaderItemViewMvvm.View {

    private ListHeaderItemBinding binding;
    private ListHeaderItemViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public ListHeaderItemView(Context context) {
        super(context);
        init(context);
    }

    public ListHeaderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListHeaderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.list_header_item, this, true);
    }

    @Override
    public void bind(Item item, int position) {
        viewModel = (ListHeaderItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.LIST_HEADER_ITEM_VIEW_MODEL, item);
        binding.setViewModel(viewModel);
    }
}
