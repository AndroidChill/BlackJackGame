package com.example.blackjackgame.ui.fragment.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatSeekBar;

public class CustomSeekBar extends AppCompatSeekBar {

    @SuppressWarnings("unused")
    private static final String TAG = CustomSeekBar.class.getSimpleName();

    private Paint paint;
    private Rect bounds;

    public String dimension;

    public CustomSeekBar(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(sp2px(14));

        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String label = String.valueOf(getProgress()) + dimension;
        paint.getTextBounds(label, 0, label.length(), bounds);
        float x = (float) getProgress() * (getWidth() - 2 * getThumbOffset()) / getMax() +
                (1 - (float) getProgress() / getMax()) * bounds.width() / 2 - bounds.width() / 2
                + getThumbOffset() / (label.length() - 1);
        canvas.drawText(label, x, paint.getTextSize(), paint);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

}
