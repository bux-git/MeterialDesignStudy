package com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dqr.www.meterialdesignstudy.MathUtils;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-25 10:42
 */

public class TopToDownBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "TopToDownBehavior";
    private float maxY;//dependency Y变化最大值
    private float childHeight;

    public TopToDownBehavior() {
    }

    public TopToDownBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if(dependency instanceof AppBarLayout){
            maxY=((AppBarLayout)dependency).getTotalScrollRange();
            childHeight=child.getHeight();
            return true;
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        //dependency 滑动百分比
        double percent=Math.abs(MathUtils.div(dependency.getY(),maxY));
        double result=MathUtils.mul(childHeight,percent) -childHeight ;


        Log.d(TAG, "onDependentViewChanged: percent:"+percent
                +" child.y:"+child.getY()
                +" result:"+result
                +"   maxY:"+maxY
                +"  childHeight:"+childHeight);
        child.setY((float) result);
        return true;
    }
}
