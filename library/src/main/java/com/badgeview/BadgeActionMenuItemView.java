package com.badgeview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Viнt@rь on 14.09.2016
 *
 * Badge action view implementation
 */
public class BadgeActionMenuItemView extends FrameLayout {
    private static final int MAX_ICON_SIZE = 32; // dp

    private final LayoutParams mBadgeLayoutParams;

    private boolean mAllowTextWithIcon;

    private int mMaxIconSize;

    private CharSequence mTitle;

    private MenuItem mMenuItem;
    private TextView mTextView;
    private BadgeView mBadgeView;

    private MenuBuilder.ItemInvoker mItemInvoker;

    public BadgeActionMenuItemView(Context context) {
        this(context, null);
    }

    public BadgeActionMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.actionButtonStyle);
    }

    public BadgeActionMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);

        final Resources res = context.getResources();
        final float density = res.getDisplayMetrics().density;
        mMaxIconSize = (int) (MAX_ICON_SIZE * density + 0.5f);

        mAllowTextWithIcon = shouldAllowTextWithIcon();

        mTextView = new AppCompatTextView(context);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextColor(getAttr(context, android.R.attr.actionMenuTextColor));
        TextViewCompat.setTextAppearance(mTextView, getAttr(context, android.R.attr.actionMenuTextAppearance));

        LayoutParams textLayoutParams = generateDefaultLayoutParams();
        textLayoutParams.gravity = Gravity.CENTER;
        addView(mTextView, textLayoutParams);

        mBadgeView = new BadgeView(context);
        mBadgeView.hide(false);

        mBadgeLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mBadgeLayoutParams.gravity = Gravity.TOP | Gravity.END;
        addView(mBadgeView, mBadgeLayoutParams);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean performClick() {
        if (mItemInvoker != null) {
            mItemInvoker.invokeItem((MenuItemImpl) mMenuItem);
        }
        return super.performClick();
    }

    /**
     * Only for internal usage.
     */
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (mMenuItem != null) {
            initialize(mMenuItem);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (mMenuItem != null) {
            mMenuItem.setEnabled(enabled);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ActionMenuView actionMenuView = (ActionMenuView) getParent();

        mItemInvoker = actionMenuView;

        Menu menu = actionMenuView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getItemId() == getId()) {
                initialize(menuItem);
                return;
            }
        }
    }

    private void initialize(@NonNull MenuItem menuItem) {
        mMenuItem = menuItem;

        setIcon(menuItem.getIcon());
        setTitle(menuItem.getTitleCondensed());
        super.setEnabled(menuItem.isEnabled());

        updateTextButtonVisibility();
    }

    private void setIcon(Drawable icon) {
        if (icon != null) {
            int width = icon.getIntrinsicWidth();
            int height = icon.getIntrinsicHeight();
            if (width > mMaxIconSize) {
                final float scale = (float) mMaxIconSize / width;
                width = mMaxIconSize;
                height = (int) (height * scale);
            }
            if (height > mMaxIconSize) {
                final float scale = (float) mMaxIconSize / height;
                height = mMaxIconSize;
                width = (int) (width * scale);
            }
            icon.setBounds(0, 0, width, height);
        }
        mTextView.setCompoundDrawables(icon, null, null, null);
    }

    private void setTitle(CharSequence title) {
        mTitle = title;
    }

    @SuppressLint("RestrictedApi")
    private void updateTextButtonVisibility() {
        boolean visible = !TextUtils.isEmpty(mTitle);
        visible &= mMenuItem.getIcon() == null || (((MenuItemImpl) mMenuItem).showsTextAsAction() && mAllowTextWithIcon);

        mTextView.setText(visible ? mTitle : null);

        // Show the tooltip for items that do not already show text.
        final CharSequence contentDescription = MenuItemCompat.getContentDescription(mMenuItem);
        if (TextUtils.isEmpty(contentDescription)) {
            // Use the uncondensed title for content description, but only if the title is not shown already.
            setContentDescription(visible ? null : mMenuItem.getTitle());
        } else {
            setContentDescription(contentDescription);
        }

        final CharSequence tooltipText = MenuItemCompat.getTooltipText(mMenuItem);
        if (TextUtils.isEmpty(tooltipText)) {
            // Use the uncondensed title for tooltip, but only if the title is not shown already.
            TooltipCompat.setTooltipText(this, visible ? null : mMenuItem.getTitle());
        } else {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
    }

    public boolean isBadgeShowing() {
        return mBadgeView.isShowing();
    }

    public void toggleBadge() {
        mBadgeView.toggle();
    }

    public void showBadge() {
        mBadgeView.show();
    }

    public void showBadge(boolean animate) {
        mBadgeView.show(animate);
    }

    public void showBadge(@StringRes int resId) {
        mBadgeView.show(resId);
    }

    public void showBadge(@StringRes int resId, boolean animate) {
        mBadgeView.show(resId, animate);
    }

    public void showBadge(CharSequence text) {
        mBadgeView.show(text);
    }

    public void showBadge(CharSequence text, boolean animate) {
        mBadgeView.show(text, animate);
    }

    public void hideBadge() {
        mBadgeView.hide();
    }

    public void hideBadge(boolean animate) {
        mBadgeView.hide(animate);
    }

    public int getBadgeGravity() {
        return mBadgeLayoutParams.gravity;
    }

    public void setBadgeGravity(int gravity) {
        mBadgeLayoutParams.gravity = gravity;
        requestLayout();
    }

    public int getBadgeBackgroundColor() {
        return mBadgeView.getBackgroundColor();
    }

    public void setBadgeBackgroundColor(@ColorInt int color) {
        mBadgeView.setBackgroundColor(color);
    }

    public int getBadgeDuration() {
        return mBadgeView.getDuration();
    }

    public void setBadgeDuration(int duration) {
        mBadgeView.setDuration(duration);
    }

    public CharSequence getBadgeText() {
        return mBadgeView.getText();
    }

    public BadgeView getBadgeView() {
        return mBadgeView;
    }

    /**
     * @return The resource ID value in the {@code context} specified by {@code attr}.
     */
    private static int getAttr(@NonNull Context context, @AttrRes int attr) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attr, value, true);
        return value.resourceId;
    }

    /**
     * Whether action menu items should obey the "withText" showAsAction flag. This may be set to
     * false for situations where space is extremely limited. -->
     */
    private boolean shouldAllowTextWithIcon() {
        final Configuration config = getContext().getResources().getConfiguration();
        final int widthDp = config.screenWidthDp;
        final int heightDp = config.screenHeightDp;

        return widthDp >= 480 || (widthDp >= 640 && heightDp >= 480) || config.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}