package com.badgeview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Viнt@rь on 14.09.2016
 */
public class BadgeActionView extends FrameLayout {
    private final LayoutParams mBadgeLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP | Gravity.END);

    private TextView mTextView;
    private ImageView mImageView;
    private BadgeView mBadgeView;
    private MenuItem mMenuItem;

    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;

    public BadgeActionView(Context context) {
        this(context, null);
    }

    public BadgeActionView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.actionButtonStyle);
    }

    public BadgeActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int itemWidth = getResources().getDimensionPixelSize(R.dimen.action_button_width);
        int itemPadding = getResources().getDimensionPixelSize(R.dimen.action_button_padding);
        LayoutParams layoutParams = new LayoutParams(itemWidth - itemPadding, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

        mTextView = new TextView(context);
        mImageView = new ImageView(context);
        mBadgeView = new BadgeView(context);
        mBadgeView.hide(false);

        mTextView.setDuplicateParentStateEnabled(true);
        mImageView.setDuplicateParentStateEnabled(true);
        mBadgeView.setDuplicateParentStateEnabled(true);

        addView(mTextView, layoutParams);
        addView(mImageView, layoutParams);
        addView(mBadgeView, mBadgeLayoutParams);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
                if (mOnMenuItemClickListener != null) {
                    mOnMenuItemClickListener.onMenuItemClick(mMenuItem);
                }
            }
        });

        super.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return BadgeActionView.this.onLongClick(v);
            }
        });
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mOnClickListener = l;
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        mOnLongClickListener = l;
    }

    public void setOnMenuItemClick(MenuItem.OnMenuItemClickListener l) {
        mOnMenuItemClickListener = l;
    }

    protected boolean onLongClick(@NonNull View v) {
        if (mMenuItem != null && !TextUtils.isEmpty(mMenuItem.getTitle())) {
            final int[] screenPos = new int[2];
            final Rect displayFrame = new Rect();
            getLocationOnScreen(screenPos);
            getWindowVisibleDisplayFrame(displayFrame);
            final Context context = getContext();
            final int width = getWidth();
            final int height = getHeight();
            final int middleY = screenPos[1] + height / 2;
            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            Toast cheatSheet = Toast.makeText(context, mMenuItem.getTitle(), Toast.LENGTH_SHORT);
            if (middleY < displayFrame.height()) {
                cheatSheet.setGravity(Gravity.TOP | Gravity.END, screenWidth - screenPos[0] - width / 2, height);
            } else {
                cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
            }
            cheatSheet.show();
        }
        return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
    }

    @Nullable
    public MenuItem getMenuItem() {
        return mMenuItem;
    }

    public void setMenuItem(@NonNull MenuItem menuItem) {
        if (mMenuItem != menuItem) {
            mMenuItem = menuItem;

            View actionView = menuItem.getActionView();
            if (actionView != null && actionView.equals(this)) {
                if (menuItem.getIcon() != null) {
                    mImageView.setImageDrawable(menuItem.getIcon());
                } else if (menuItem.getTitle() != null) {
                    mTextView.setText(menuItem.getTitle());
                }
            }
        }
    }

    public void toggle() {
        mBadgeView.toggle();
    }

    public void show() {
        mBadgeView.show();
    }

    public void show(boolean animate) {
        mBadgeView.show(animate);
    }

    public void show(String text) {
        mBadgeView.show(text);
    }

    public void show(String text, boolean animate) {
        mBadgeView.show(text, animate);
    }

    public void hide() {
        mBadgeView.hide();
    }

    public void hide(boolean animate) {
        mBadgeView.hide(animate);
    }

    public int getGravity() {
        return mBadgeLayoutParams.gravity;
    }

    public void setGravity(int gravity) {
        mBadgeLayoutParams.gravity = gravity;
        requestLayout();
    }

    public int getBackgroundColor() {
        return mBadgeView.getBackgroundColor();
    }

    public void setBackgroundColor(@ColorInt int color) {
        mBadgeView.setBackgroundColor(color);
    }

    public CharSequence getText() {
        return mBadgeView.getText();
    }

    public void setText(@StringRes int resId) {
        mBadgeView.setText(resId);
    }

    public void setText(String text) {
        mBadgeView.setText(text);
    }

    public boolean isShowing() {
        return mBadgeView.isShowing();
    }

    public BadgeView getBadgeView() {
        return mBadgeView;
    }
}