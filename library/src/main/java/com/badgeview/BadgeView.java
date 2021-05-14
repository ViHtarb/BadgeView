package com.badgeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Implementation of BadgeView
 */
@Keep
public class BadgeView extends AppCompatTextView {
    private static final int DEFAULT_PADDING = 16; // px
    private static final int DEFAULT_DURATION = 200;

    private boolean isShowing = true;

    private int mBackgroundColor;
    private int mPadding;
    private long mDuration;

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
        mPadding = a.getDimensionPixelSize(R.styleable.BadgeView_android_padding, DEFAULT_PADDING);
        mDuration = a.getInt(R.styleable.BadgeView_android_duration, DEFAULT_DURATION);

        a.recycle();

        setBackgroundInternal(createBackgroundDrawable());
    }

    /**
     * not supported
     */
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == VISIBLE) {
            show();
        } else {
            hide();
        }
    }

    /**
     * not supported
     */
    @Override
    public void setGravity(int gravity) {
    }

    /**
     * Sets the background color
     *
     * @param color the color of the background
     * @attr ref R.styleable#BadgeView_backgroundColor
     */
    @Override
    public void setBackgroundColor(@ColorInt int color) {
        if (mBackgroundColor != color) {
            mBackgroundColor = color;
            setBackgroundInternal(createBackgroundDrawable());
        }
    }

    /**
     * not supported
     */
    @Override
    public Drawable getBackground() {
        return null;
    }

    @Override
    public void setBackground(Drawable background) {
    }

    /**
     * not supported
     */
    @Override
    public void setBackgroundDrawable(Drawable background) {
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        setBackgroundInternal(createBackgroundDrawable());
    }

    /**
     * not supported
     */
    @Override
    public void setSingleLine(boolean singleLine) {
    }

    /**
     * not supported
     */
    @Override
    public void setMaxLines(int maxLines) {
    }

    /**
     * @return {@code true} if the badge is showed, {@code false} otherwise
     */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * Toggle badge showing
     */
    public void toggle() {
        toggle(true);
    }

    /**
     * Toggle badge showing
     *
     * @param animate {@code true} to animated showing, {@code false} otherwise
     */
    public void toggle(boolean animate) {
        if (isShowing) {
            hide(animate);
        } else {
            show(animate);
        }
    }

    /**
     * Show badge
     */
    public void show() {
        show(true);
    }

    /**
     * Show badge
     *
     * @param animate {@code true} to animated showing, {@code false} otherwise
     */
    public void show(boolean animate) {
        show(null, animate);
    }

    /**
     * Show badge
     *
     * @param resId the resource identifier of the string resource to be displayed
     */
    public void show(@StringRes int resId) {
        show(resId, true);
    }

    /**
     * Show badge
     *
     * @param resId   the resource identifier of the string resource to be displayed
     * @param animate {@code true} to animated showing, {@code false} otherwise
     */
    public void show(@StringRes int resId, boolean animate) {
        show(getResources().getText(resId), animate);
    }

    /**
     * Show badge
     *
     * @param text text to be displayed
     */
    public void show(CharSequence text) {
        show(text, true);
    }

    /**
     * Show badge
     *
     * @param text    text to be displayed
     * @param animate {@code true} to animated showing, {@code false} otherwise
     */
    public void show(CharSequence text, boolean animate) {
        super.setVisibility(VISIBLE);

        isShowing = true;
        if (text != null) {
            setText(text);
        }

        if (animate) {
            animate()
                    .setDuration(mDuration)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1)
                    .start();
        }
    }

    /**
     * Hide badge
     */
    public void hide() {
        hide(true);
    }

    /**
     * Hide badge
     *
     * @param animate {@code true} to animated hiding, {@code false} otherwise
     */
    public void hide(boolean animate) {
        isShowing = false;

        if (animate) {
            animate()
                    .setDuration(mDuration)
                    .alpha(0)
                    .scaleX(0)
                    .scaleY(0)
                    .start();
        } else {
            super.setVisibility(GONE);
        }
    }

    /**
     * @return background color
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * @return padding in pixels
     */
    public int getPadding() {
        return mPadding;
    }

    /**
     * Sets badge padding
     *
     * @param padding the padding in pixels
     * @attr ref R.styleable#BadgeView_android_padding
     */
    public void setPadding(int padding) {
        if (mPadding != padding) {
            mPadding = padding;

            super.setPadding(mPadding, mPadding / 3, mPadding, mPadding / 3);
        }
    }

    /**
     * @return animation duration
     */
    public long getDuration() {
        return mDuration;
    }

    /**
     * Sets animation duration
     *
     * @param duration the animation duration
     * @attr ref R.styleable#BadgeView_android_duration
     */
    public void setDuration(long duration) {
        mDuration = duration;
    }

    /**
     * Wrapper method of {@link super#setBackgroundDrawable(Drawable)}
     *
     * @param background the drawable to use as the background, or null to remove the
     *                   background
     * @see super#setBackgroundDrawable(Drawable)
     */
    protected final void setBackgroundInternal(Drawable background) {
        // noinspection deprecation
        super.setBackgroundDrawable(background);
    }

    /**
     * Creates background drawable
     *
     * @return the drawable for used as background
     */
    protected Drawable createBackgroundDrawable() {
        if (getText().length() > 2) {
            super.setPadding(mPadding, mPadding / 3, mPadding, mPadding / 3);

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
