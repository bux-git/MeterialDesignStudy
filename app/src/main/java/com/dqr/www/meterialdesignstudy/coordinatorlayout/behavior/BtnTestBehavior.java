package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-19 19:53
 */

public class BtnTestBehavior extends CoordinatorLayout.Behavior<Button> {
    private static final String TAG = "BtnTestBehavior";
    private int width;
    private int target;
    public BtnTestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.Follow);
        for(int i=0;i<array.getIndexCount();i++){
            //获取属性名称ID根据位置索引
            int attrId = array.getIndex(i);
            //根据属性名称ID获取属性值
            if(attrId==R.styleable.Follow_target){
                target = array.getResourceId(attrId, -1);
            }
        }
        array.recycle();
    }

    /**
     * 判断child的布局是否依赖dependency
     * 返回false表示child不依赖dependency，ture表示依赖
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        Log.d(TAG, "layoutDependsOn: ");
      return dependency.getId()==target;
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
        Log.d(TAG, "onDependentViewChanged: ");
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
