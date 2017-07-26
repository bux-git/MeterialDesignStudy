package com.dqr.www.meterialdesignstudy.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-25 9:28
 */

public class CoordinatorLayoutActivity02 extends AppCompatActivity {
    private static final String TAG = "CoordinatorLayoutActivi";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_tc_layout);
        final AppBarLayout barLayout= (AppBarLayout) findViewById(R.id.app_bar);
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: "+verticalOffset+" totalScroll:"+barLayout.getTotalScrollRange());
            }
        });





    }
}
