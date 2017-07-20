package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-19 19:53
 */

public class BtnTestBehavior extends CoordinatorLayout.Behavior<Button> {
    private static final String TAG = "BtnTestBehavior";
    private int width;
    public BtnTestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;
    }

    /**
     * 判断child的布局是否依赖dependency
     * 返回false表示child不依赖dependency，ture表示依赖
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
       if(dependency instanceof TempView){
            return true;
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        //根据dependency的位置，设置Button的位置

        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - child.getWidth();
        int y = top;

        setPosition(child, x, y);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, Button child, MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, Button child, MotionEvent ev) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(parent, child, ev);
    }
}
