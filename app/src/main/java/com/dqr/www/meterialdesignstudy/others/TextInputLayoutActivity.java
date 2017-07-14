package com.dqr.www.meterialdesignstudy.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-14 10:27
 */

public class TextInputLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout mTlLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_layout);
        mTlLayout = (TextInputLayout) findViewById(R.id.til_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                mTlLayout.setError("错误信息");
                break;
        }
    }
}
