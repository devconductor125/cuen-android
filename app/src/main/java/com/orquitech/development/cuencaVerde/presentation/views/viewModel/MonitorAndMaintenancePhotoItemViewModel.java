package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.graphics.Bitmap;
import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class MonitorAndMaintenancePhotoItemViewModel implements MonitorAndMaintenancePhotoItemViewMvvm.ViewModel {

    private final MonitorAndMaintenancePhotoItemViewMvvm.View view;
    private final MonitorAndMaintenancePhoto monitorAndMaintenancePhoto;
    private Bitmap bitmap;
    private String imageName;

    public MonitorAndMaintenancePhotoItemViewModel(AppView view, Item item) {
        this.view = (MonitorAndMaintenancePhotoItemViewMvvm.View) view;
        this.monitorAndMaintenancePhoto = ((MonitorAndMaintenancePhoto) item);

        this.bitmap = monitorAndMaintenancePhoto.getBitmap();
        this.imageName = monitorAndMaintenancePhoto.getPhotoName();
    }

    @Override
    public void onClick(View view) {
        this.view.onClick();
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public Bitmap getImage() {
        return bitmap;
    }

    @Override
    public void setImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
