package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import androidx.databinding.DataBindingUtil;

import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AddPhoto;
import com.orquitech.development.cuencaVerde.databinding.MonitorAndMaintenancePhotoItemBinding;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.ItemList;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.MonitorAndMaintenancePhotoItemViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Inject;

public class MonitorAndMaintenancePhotoItemView extends BaseListView implements ItemView, MonitorAndMaintenancePhotoItemViewMvvm.View {

    private MonitorAndMaintenancePhotoItemBinding binding;
    private MonitorAndMaintenancePhotoItemViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelFactory;

    public MonitorAndMaintenancePhotoItemView(Context context) {
        super(context);
        init(context);
    }

    public MonitorAndMaintenancePhotoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MonitorAndMaintenancePhotoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        getComponent().inject(this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.monitor_and_maintenance_photo_item, this, true);
    }

    @Override
    public void bind(Item item, int position) {
        viewModel = (MonitorAndMaintenancePhotoItemViewMvvm.ViewModel) viewModelFactory.getItemViewModel(this, AppViewModelsFactory.MONITOR_AND_MAINTENANCE_PHOTO_ITEM_VIEW_MODEL, item);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDetachedFromWindow() {
        // Drawable drawable = ((BitmapDrawable) binding.image.getDrawable());
        // drawable.getBitmap().recycle();
        super.onDetachedFromWindow();
    }

    @Override
    public void onClick() {
        ((ItemList) parentView).onItemClicked(new AddPhoto());
    }
}
