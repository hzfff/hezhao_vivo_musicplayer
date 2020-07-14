package com.example.musicplayer_hezhao.util;

/**
 * Created by 11120555 on 2020/7/14 8:47
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import androidx.annotation.Nullable;

public class CheckableTextView extends androidx.appcompat.widget.AppCompatTextView implements Checkable {

    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public CheckableTextView(Context context) {
        super(context);
    }

    public CheckableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        mChecked = !mChecked;
    }
}