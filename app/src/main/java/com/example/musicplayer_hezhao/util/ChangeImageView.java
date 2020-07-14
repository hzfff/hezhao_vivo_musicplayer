package com.example.musicplayer_hezhao.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.gjiazhe.panoramaimageview.PanoramaImageView;

/**
 * Created by 11120555 on 2020/7/9 18:41
 */
public class ChangeImageView extends PanoramaImageView {


    float width,height;


    public ChangeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > 15 && height > 15) {
            Path path = new Path();
            path.moveTo(15, 0);
            path.lineTo(width - 15, 0);
            path.quadTo(width, 0, width, 15);
            path.lineTo(width, height - 15);
            path.quadTo(width, height, width - 15, height);
            path.lineTo(15, height);
            path.quadTo(0, height, 0, height - 15);
            path.lineTo(0, 15);
            path.quadTo(0, 0, 15, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }

    public ChangeImageView(Context context) {
        super(context);
    }
}
