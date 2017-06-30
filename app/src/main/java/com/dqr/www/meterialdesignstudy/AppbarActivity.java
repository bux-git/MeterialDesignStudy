package com.dqr.www.meterialdesignstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-06-29 10:07
 */

public class AppbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter());
    }
}
