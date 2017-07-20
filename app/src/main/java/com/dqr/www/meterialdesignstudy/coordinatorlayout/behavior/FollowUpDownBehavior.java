package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-20 10:37
 */

public class FollowUpDownBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "FollowUpDownBehavior";
    private int target;

    public FollowUpDownBehavior() {
    }

    public FollowUpDownBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Follow);
        for(int i=0;i<array.getIndexCount();i++){
            //根据位置索引获取属性名称ID
            int attr = array.getIndex(i);
            if(attr==R.styleable.Follow_target){
                //根据属性名称ID获取属性值
                target = array.getResourceId(attr, -1);
            }

        }

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId()==target;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY()+dependency.getHeight());
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(parent, child, ev);
    }
}
