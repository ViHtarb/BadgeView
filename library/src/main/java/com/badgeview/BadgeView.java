package com.badgeview;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Implementation of BadgeView
 * // TODO on empty text small background
 */
public class BadgeView extends TextView {
    private boolean isShowing = true;

    private int mBackgroundColor;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.badgeViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setGravity(Gravity.CENTER);
        super.setSingleLine(true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BadgeView, defStyleAttr, 0);

        mBackgroundColor = a.getColor(R.styleable.BadgeView_backgroundColor, Color.RED);

        a.recycle();

        setBackgroundInternal(getBackgroundDrawable());
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        // not supported
    }

    @Override
    public void setVisibility(int visibility) {
        // not supported
    }

    @Override
    public void setGravity(int gravity) {
        // not supported
    }

    @Override
    public Drawable getBackground() {
        // not supported
        return null;
    }

    @Override
    public void setBackground(Drawable background) {
        // not supported
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        // not supported
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        setBackgroundInternal(getBackgroundDrawable());
    }

    @Override
    public void setSingleLine(boolean singleLine) {
        // not supported
    }

    @Override
    public void setMaxLines(int maxLines) {
        // not supported
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean animate) {
        if (isShowing) {
            hide(animate);
        } else {
            show(animate);
        }
    }

    public void show() {
        show(true);
    }

    public void show(boolean animate) {
        show(null, animate);
    }

    public void show(String text) {
        show(text, true);
    }

    public void show(String text, boolean animate) {
        super.setVisibility(VISIBLE);

        isShowing = true;
        if (text != null) {
            setText(text);
        }

        if (animate) {
            animate()
                    .setDuration(200)
                    .alpha(1)
                    .start();
        }
    }

    public void hide() {
        hide(true);
    }

    public void hide(boolean animate) {
        isShowing = false;

        if (animate) {
            animate()
                    .setDuration(200)
                    .alpha(0)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            BadgeView.super.setVisibility(GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();
        } else {
            super.setVisibility(GONE);
        }
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(@ColorInt int color) {
        if (mBackgroundColor != color) {
            mBackgroundColor = color;
            setBackgroundInternal(getBackgroundDrawable());
        }
    }

    protected final void setBackgroundInternal(Drawable background) {
        // noinspection deprecation
        super.setBackgroundDrawable(background);
    }

    protected Drawable getBackgroundDrawable() {
        if (getText().length() > 2) {
            int padding = (int) Util.dpToPx(4);
            super.setPadding(padding, padding / 3, padding, padding / 3);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(mBackgroundColor);
            drawable.setCornerRadius(100f);
            return drawable;
        }
        super.setPadding(0, 0, 0, 0);

        int backgroundSize = (int) (getTextSize() * 1.65);
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setIntrinsicWidth(backgroundSize);
        drawable.setIntrinsicHeight(backgroundSize);
        drawable.getPaint().setColor(mBackgroundColor);
        return drawable;
    }
}
