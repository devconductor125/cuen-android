package com.orquitech.development.cuencaVerde.presentation.views.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AnimationBaseCallback;

public class ViewUtil {

    public static boolean isPortrait(Resources res) {
        return res.getBoolean(R.bool.is_portrait);
    }

    public static void measureView(View view, AnimationBaseCallback callback) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                callback.onCompleted();
            }
        });
    }

    public static void onLayoutChange(View view, AnimationBaseCallback callback) {
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                callback.onCompleted();
            }
        });
    }

    public static Bitmap getBitmapFromView(View view, int screenVerticalWidth) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.TRANSPARENT);
            view.draw(canvas);
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (screenVerticalWidth * 0.45), (int) (screenVerticalWidth * 0.45), false);
    }

    public static boolean isInMultiWindowMode(Context context) {
        boolean isInMultiWindowMode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (((Activity) context).isInMultiWindowMode()) {
                isInMultiWindowMode = true;
            }
        }
        return isInMultiWindowMode;
    }
}
