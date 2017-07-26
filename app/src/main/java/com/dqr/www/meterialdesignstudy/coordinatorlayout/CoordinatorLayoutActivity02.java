package com.dqr.www.meterialdesignstudy.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
        final ImageView ivImg= (ImageView) findViewById(R.id.iv_head_bg);
       final Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
       // final TextView tvText= (TextView) findViewById(R.id.tv_text);

        ivImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "ivImg: top:"+ ivImg.getTop()+" left:"+ivImg.getLeft()+"    bottom:"+ivImg.getBottom()+"    right:"+ivImg.getRight()+" Y:"+ivImg.getY());
                return false;
            }
        });


    }
}
