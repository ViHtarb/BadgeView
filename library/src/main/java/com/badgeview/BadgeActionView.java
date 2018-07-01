package com.badgeview;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.MenuItem;

/**
 * Created by Viнt@rь on 14.09.2016
 *
 * @deprecated Use {@link BadgeActionMenuItemView} instead.
 */
@Deprecated
public class BadgeActionView extends BadgeActionMenuItemView {

    public BadgeActionView(Context context) {
        super(context);
    }

    public BadgeActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BadgeActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @deprecated Implementation not longer needed. Use {@link BadgeActionMenuItemView} instead.
     */
    @Deprecated
    public void setVisible(boolean visible) {

    }

    /**
     * @deprecated Implementation not longer needed. Use {@link BadgeActionMenuItemView} instead.
     */
    @Deprecated
    @Nullable
    public MenuItem getMenuItem() {
        return null;
    }

    /**
     * @deprecated Implementation not longer needed. Use {@link BadgeActionMenuItemView} instead.
     */
    @Deprecated
    public void setMenuItem(@NonNull MenuItem menuItem) {

    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#isBadgeShowing()} instead.
     */
    @Deprecated
    public boolean isShowing() {
        return super.isBadgeShowing();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#toggleBadge()} instead.
     */
    @Deprecated
    public void toggle() {
        super.toggleBadge();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge()} instead.
     */
    @Deprecated
    public void show() {
        super.showBadge();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge(boolean)} instead.
     */
    @Deprecated
    public void show(boolean animate) {
        super.showBadge(animate);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge(CharSequence)} instead.
     */
    @Deprecated
    public void show(CharSequence text) {
        super.showBadge(text);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge(CharSequence, boolean)} instead.
     */
    @Deprecated
    public void show(CharSequence text, boolean animate) {
        super.showBadge(text, animate);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#hideBadge()} instead.
     */
    @Deprecated
    public void hide() {
        super.hideBadge();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#hideBadge(boolean)} instead.
     */
    @Deprecated
    public void hide(boolean animate) {
        super.hideBadge(animate);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#getBadgeGravity()} instead.
     */
    @Deprecated
    public int getGravity() {
        return super.getBadgeGravity();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#setBadgeGravity(int)} instead.
     */
    @Deprecated
    public void setGravity(int gravity) {
        super.setBadgeGravity(gravity);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#getBadgeBackgroundColor()} instead.
     */
    @Deprecated
    public int getBackgroundColor() {
        return super.getBadgeBackgroundColor();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#setBadgeBackgroundColor(int)} instead.
     */
    @Deprecated
    public void setBackgroundColor(@ColorInt int color) {
        super.setBadgeBackgroundColor(color);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#getBadgeDuration()} instead.
     */
    @Deprecated
    public long getDuration() {
        return super.getBadgeDuration();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#setBadgeDuration(int)} instead.
     */
    @Deprecated
    public void setDuration(int duration) {
        super.setBadgeDuration(duration);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#getBadgeText()} instead.
     */
    @Deprecated
    public CharSequence getText() {
        return super.getBadgeText();
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge(int, boolean)} instead.
     */
    @Deprecated
    public void setText(@StringRes int resId) {
        super.showBadge(resId, false);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#showBadge(CharSequence, boolean)} instead.
     */
    @Deprecated
    public void setText(CharSequence text) {
        super.showBadge(text, false);
    }

    /**
     * @deprecated Use {@link BadgeActionMenuItemView#isBadgeShowing()} instead.
     */
    @Deprecated
    public BadgeView getBadgeView() {
        return super.getBadgeView();
    }

    /**
     * @deprecated Implementation not longer needed. Use {@link BadgeActionMenuItemView} instead.
     */
    @Deprecated
    public void setOnMenuItemClick(MenuItem.OnMenuItemClickListener l) {
    }
}