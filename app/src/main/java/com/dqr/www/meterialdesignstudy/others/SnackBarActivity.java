package com.dqr.www.meterialdesignstudy.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-14 16:48
 */

public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snackbar_layout);
        mBtnShow = (Button) findViewById(R.id.btn_show);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
               Snackbar snackbar= Snackbar.make(mBtnShow,"测试",Snackbar.LENGTH_LONG);
                snackbar.setAction("编辑", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarActivity.this,"点击",Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.black));
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        super.onShown(transientBottomBar);
                    }
                });
                snackbar.show();
                break;
        }
    }
}
