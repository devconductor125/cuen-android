package com.orquitech.development.cuencaVerde.presentation.views.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orquitech.development.cuencaVerde.R;

public class LayoutManagerUtil {

    public static RecyclerView.LayoutManager getGridListLayoutManager(Context context, Bundle savedInstanceState, String bundleKey) {
        int spanCount = context.getResources().getInteger(R.integer.span_list_items);
        if (ViewUtil.isInMultiWindowMode(context)) {
            spanCount = 1;
        }
        RecyclerView.LayoutManager manager = new GridLayoutManager(context, spanCount);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(bundleKey);
            if (savedRecyclerLayoutState != null) {
                manager.onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
        return manager;
    }

    public static RecyclerView.LayoutManager getPhotosGridListLayoutManager(Context context, Bundle savedInstanceState, String bundleKey) {
        int spanCount = context.getResources().getInteger(R.integer.span_photos_list_items);
        if (ViewUtil.isInMultiWindowMode(context)) {
            spanCount = 1;
        }
        RecyclerView.LayoutManager manager = new GridLayoutManager(context, spanCount);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(bundleKey);
            if (savedRecyclerLayoutState != null) {
                manager.onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
        return manager;
    }

    public static RecyclerView.LayoutManager getVerticalListLayoutManager(Context context, Bundle savedInstanceState, String bundleKey) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(bundleKey);
            if (savedRecyclerLayoutState != null) {
                manager.onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
        return manager;
    }

    public static RecyclerView.LayoutManager getHorizontalListLayoutManager(Context context, Bundle savedInstanceState, String bundleKey) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(bundleKey);
            if (savedRecyclerLayoutState != null) {
                manager.onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
        return manager;
    }
}
