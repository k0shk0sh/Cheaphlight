package com.fastaccess.cheaphlight.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by Kosh on 27 May 2016, 9:04 PM
 */

public class AnimHelper {

    @UiThread public static void animateVisibityWithTranslate(final boolean show, @Nullable final View view) {
        if (view == null) return;
        if (!ViewCompat.isAttachedToWindow(view)) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    animateSafelyViewVisibility(show, view);
                    return false;
                }
            });
        } else {
            animateSafelyViewVisibility(show, view);
        }
    }

    @UiThread private static void animateSafelyViewVisibility(final boolean show, final View view) {
        Logger.e(show, view);
        ViewPropertyAnimator animator = view.animate().alpha(show ? 1F : 0F).setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        if (show) {
                            view.setScaleX(1);
                            view.setScaleY(1);
                            view.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override public void onAnimationEnd(@NonNull Animator animation) {
                        super.onAnimationEnd(animation);
                        if (!show) {
                            view.setVisibility(View.GONE);
                            view.setScaleX(0);
                            view.setScaleY(0);
                        }
                    }
                });
        animator.scaleX(show ? 1 : 0).scaleY(show ? 1 : 0);
    }
}
