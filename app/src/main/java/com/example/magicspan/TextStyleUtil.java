package com.example.magicspan;

import android.support.annotation.Dimension;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by linpeiyou on 2017/12/7.
 */

public class TextStyleUtil {

    /**
     * 颜色
     */
    public static CharSequence color(CharSequence content, int color) {
        return apply(content, new ForegroundColorSpan(color));
    }

    /**
     * 字体大小
     */
    public static CharSequence size(CharSequence content, @Dimension(unit = Dimension.SP) int size) {
        return apply(content, new AbsoluteSizeSpan(size, true));
    }

    private static CharSequence apply(CharSequence content, Object... tags) {
        if(content == null || tags == null)
            return "";
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        openTags(ssb, tags);
        ssb.append(content);
        closeTags(ssb, tags);
        return ssb;
    }

    private static void openTags(Spannable text, Object[] tags) {
        for (Object tag : tags) {
            text.setSpan(tag, 0, 0, Spannable.SPAN_MARK_MARK);
        }
    }

    private static void closeTags(Spannable text, Object[] tags) {
        int len = text.length();
        for (Object tag : tags) {
            if (len > 0) {
                text.setSpan(tag, 0, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                text.removeSpan(tag);
            }
        }
    }
}
