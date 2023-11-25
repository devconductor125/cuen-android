package com.orquitech.development.cuencaVerde.presentation.views.utils;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.views.dialogs.CustomDialog;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AnimationBaseCallback;

public class AnimationUtil {

    public static void enterReveal(View view) {
        ViewUtil.measureView(view, () -> enterReveal(view, CustomDialog.DEFAULT_DIALOG_LOCATION, 450, null));
    }

    public static void enterReveal(View view, float[] locationOnScreen) {
        ViewUtil.measureView(view, () -> enterReveal(view, locationOnScreen, 450, null));
    }

    private static void enterReveal(View view, float[] locationOnScreen, int duration, AnimationBaseCallback callback) {
        /*int originX = (int) (view.getMeasuredWidth() * locationOnScreen[0]);
        int originY = (int) (view.getMeasuredHeight() * locationOnScreen[1]);

        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();

        int finalRadius = (int) Math.sqrt(width * width + height * height);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, originX, originY, 0, finalRadius);

        view.setVisibility(View.VISIBLE);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(duration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (callback != null) {
                    callback.onCompleted();
                }
            }
        });
        anim.start();*/

        if (callback != null) {
            callback.onCompleted();
        }
    }

    public static void exitReveal(final View view, float[] locationOnScreen, final AnimationBaseCallback callback) {
        /*int dx = (int) (view.getMeasuredWidth() * locationOnScreen[0]);
        int dy = (int) (view.getMeasuredHeight() * locationOnScreen[1]);

        final Animator anim = ViewAnimationUtils.createCircularReveal(view, dx, dy, view.getWidth(), 0);

        anim.setDuration(350);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                if (callback != null) {
                    callback.onCompleted();
                }
            }
        });

        anim.start();*/

        if (callback != null) {
            callback.onCompleted();
        }
    }

    public static void animateViewColorChange(View view, int fromColor, int toColor) {
        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(animation -> {
            // Use animation position to blend colors.
            float position = animation.getAnimatedFraction();
            int blended = blendColors(fromColor, toColor, position);
            // Apply blended color to the view.
            view.setBackgroundColor(blended);
        });
        anim.setDuration(400).start();
    }

    private static int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;
        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
