package com.holiday.jetpackstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.holiday.jetpackstudy.R;

/**
 * 加载网络图片
 */
public class NetImageView extends AppCompatImageView {
    private static final String TAG = "NetImageView";

    public NetImageView(Context context) {
        super(context);
    }

    public NetImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NetImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter({"url"})
    public static void load(ImageView view, String url) {
        Log.e(TAG, "load url = " + url);
        Glide.with(view.getContext()).load(url).
                error(R.mipmap.ic_launcher).
                into(view);
    }
}
