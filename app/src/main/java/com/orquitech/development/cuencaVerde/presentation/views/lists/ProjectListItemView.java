package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.databinding.ProjectItemBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.ProjectItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.DashboardViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class ProjectListItemView extends BaseListView implements ItemView, ProjectItemViewMvvm.View {

    private ProjectItemBinding binding;
    private ProjectItemViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public ProjectListItemView(Context context) {
        super(context);
        init(context);
    }

    public ProjectListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProjectListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.project_item, this, true);
    }

    @Override
    public void bind(Item item, int position) {
        viewModel = (ProjectItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.PROJECT_ITEM_VIEW_MODEL, item);
        binding.setViewModel(viewModel);
        setItemPadding(binding.projectItemContainer, position);
    }

    @Override
    public void onClick(Procedure project) {
        ((DashboardViewMvvm.View) parentView).onProjectClicked(project);
    }
}
