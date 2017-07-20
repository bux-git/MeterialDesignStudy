package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-19 19:24
 */

public class TempView extends View {
    private int lastX;
    private int lastY;

    public TempView(Context context) {
        super(context);
    }

    public TempView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;


                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }

            case MotionEvent.ACTION_UP: {

                break;
            }

        }
        lastX = x;
        lastY = y;
        return true;
    }
}
