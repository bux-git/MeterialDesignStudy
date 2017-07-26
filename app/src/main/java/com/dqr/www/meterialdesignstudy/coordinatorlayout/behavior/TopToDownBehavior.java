package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-25 10:42
 */

public class TopToDownBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "TopToDownBehavior";
    private int maxY;//dependency Y变化最大值

    public TopToDownBehavior() {
    }

    public TopToDownBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return super.layoutDependsOn(parent,  child,  dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "onDependentViewChanged: Y:"+dependency.getY()+"  top:"+dependency.getTop()+"    height:"+dependency.getHeight()+"   minHeight:"+dependency.getMinimumHeight()
        +"  child.parent.minHeight:"+((View)dependency.getParent()).getY());
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
