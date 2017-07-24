package com.dqr.www.meterialdesignstudy.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-19 15:57
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private static final String TAG = "CoordinatorLayoutActivi";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        
        findViewById(R.id.view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: ");
                return false;
            }
        });
    }
}
