package com.badgeview;

import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Viнt@rь on 12.09.2016
 */
final class Util {

    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
