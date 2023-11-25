package com.orquitech.development.cuencaVerde.presentation.views.lists;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.factories.ItemView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.utils.ViewUtil;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseListView extends FrameLayout implements ItemView {

    protected AppView parentView = null;
    private ViewsComponent component;
    protected CompositeDisposable disposables;

    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void init(Context context) {
        component = DaggerViewsComponent.builder().appComponent(CuencaVerdeApp.getApp().getAppComponent()).build();
    }

    protected ViewsComponent getComponent() {
        return component;
    }

    @Override
    public abstract void bind(Item item, int position);

    @Override
    public void setParentView(AppListView parentView) {
        this.parentView = parentView;
    }

    protected void setItemPadding(ViewGroup container, int position) {
        int padding = getResources().getDimensionPixelOffset(R.dimen.dimen_large);
        int paddingMiddle = getResources().getDimensionPixelOffset(R.dimen.dimen_small);
        if (getContext().getResources().getBoolean(R.bool.is_portrait) || ViewUtil.isInMultiWindowMode(parentView.getContext())) {
            if (position == 0) {
                container.setPadding(padding, padding, padding, padding);
            }
        } else {
            if (position == 0) {
                container.setPadding(padding, padding, paddingMiddle, padding);
            } else if (position == 1) {
                container.setPadding(paddingMiddle, padding, padding, padding);
            } else if (position % 2 == 0) {
                container.setPadding(padding, 0, paddingMiddle, padding);
            } else {
                container.setPadding(paddingMiddle, 0, padding, padding);
            }
        }
    }
}
