package com.orquitech.development.cuencaVerde.presentation.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewsComponent;

public abstract class BaseAppView extends FrameLayout {

    private ViewsComponent component;
    protected Context context;

    public BaseAppView(Context context) {
        super(context);
        init();
    }

    public BaseAppView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseAppView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        component = DaggerViewsComponent.builder().appComponent(CuencaVerdeApp.getApp().getAppComponent()).build();
    }

    protected ViewsComponent getComponent() {
        return component;
    }
}
