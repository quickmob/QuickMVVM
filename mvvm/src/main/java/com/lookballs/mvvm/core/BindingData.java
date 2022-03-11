package com.lookballs.mvvm.core;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：BindingAdapter基础类
 */
public class BindingData {
    @BindingAdapter(value = {"isVisibility"})
    public static void setVisibility(View view, boolean isVisibility) {
        view.setVisibility(isVisibility ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter(value = {"isGone"})
    public static void setGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {"isSelected"})
    public static void setSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }

    @BindingAdapter(value = {"isEnabled"})
    public static void setEnabled(View view, boolean isEnabled) {
        view.setEnabled(isEnabled);
    }

    @BindingAdapter(value = {"isClickable"})
    public static void setClickable(View view, boolean isClickable) {
        view.setClickable(isClickable);
    }

    @BindingAdapter(value = {"textObj"})
    public static void setText(TextView view, Object obj) {
        if (obj != null) {
            view.setText(String.valueOf(obj));
        }
    }

    @BindingAdapter(value = {"maxLength", "maxLines"}, requireAll = false)
    public static void setTextLimit(TextView view, int maxLength, int maxLines) {
        if (maxLength > 0) {
            view.setMaxEms(maxLength);
        }
        if (maxLines > 0) {
            view.setMaxLines(maxLines);
        }
        view.setEllipsize(TextUtils.TruncateAt.END);
    }
}
