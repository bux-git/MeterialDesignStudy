package com.dqr.www.meterialdesignstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.dqr.www.meterialdesignstudy.appbar.AppBarLayout01;
import com.dqr.www.meterialdesignstudy.collapsingtoolbar.CollapsingTollBarActivity01;
import com.dqr.www.meterialdesignstudy.coordinatorlayout.CoordinatorLayoutActivity;
import com.dqr.www.meterialdesignstudy.coordinatorlayout.CoordinatorLayoutActivity02;
import com.dqr.www.meterialdesignstudy.others.SnackBarActivity;
import com.dqr.www.meterialdesignstudy.others.TextInputLayoutActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    NavigationView mNavigationView;
    Toolbar mToolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_content);
        mNavigationView = (NavigationView) findViewById(R.id.ng_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //给Toolbar设置自定义图标
        //mToolbar.setNavigationIcon(R.drawable.ic_menu_camera);
        //显示ToolBar左侧图标
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter());

        initDrawerLayoutSetting();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem mPreMenuItem;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                if (mPreMenuItem != null) mPreMenuItem.setChecked(false);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                mPreMenuItem = item;

                switch (item.getItemId()) {
                    case R.id.app_bar:
                        startActivity(new Intent(MainActivity.this, AppBarLayout01.class));
                        break;
                    case R.id.CollapsingToolbarLayout:
                        startActivity(new Intent(MainActivity.this, CollapsingTollBarActivity01.class));
                        break;
                    case R.id.TextInputLayout:
                        startActivity(new Intent(MainActivity.this, TextInputLayoutActivity.class));
                        break;
                    case R.id.snackbar:
                        startActivity(new Intent(MainActivity.this, SnackBarActivity.class));
                        break;
                    case R.id.CoordinatorLayout:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity.class));
                        break;
                    case R.id.CoordinatorLayout02:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity02.class));
                        break;

                }
                return true;
            }
        });
    }

    private void initDrawerLayoutSetting() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            /**
             * 当抽屉被滑动的时候调用月此方法
             *
             * @param drawerView
             * @param slideOffset 表示滑动的幅度(0-1)
             */
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            /**
             * 当抽屉完全打开时调用
             * @param drawerView
             */
            @Override
            public void onDrawerOpened(View drawerView) {

            }

            /**
             * 当抽屉完全关闭时调用
             * @param drawerView
             */
            @Override
            public void onDrawerClosed(View drawerView) {

            }

            /**
             * 当抽屉运动状态改变的时候被调用
             * 状态值分别为 STATE_IDLE  0 抽屉处于闲置状态。没有动画正在进行中。处于打开或者关闭状态
             *  STATE_DRAGGING 1 表示用户当前正在拖动抽屉。
             *  STATE_SETTLING 2 表示抽屉滑动到了关闭或者打开的位置
             *  当用户滑动抽屉时 抽屉此时处于STATE_DRAGGING 滑动到刚好关闭或者打开后是STATE_SETTLING状态 随后状态会变为STATE_IDLE 闲置状态
             * @param newState
             */
            @Override
            public void onDrawerStateChanged(int newState) {
                Log.d(TAG, "onDrawerStateChanged: " + newState);
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open_string, R.string.close_string) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mToolbar.setTitle("DrawerClosed");
                // mToolbar.setNavigationIcon(R.drawable.ic_menu_gallery);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mToolbar.setTitle("DrawerOpened");
                //mToolbar.setNavigationIcon(R.drawable.ic_menu_send);
            }


        };
        //打开侧滑栏
        drawerLayout.openDrawer(Gravity.LEFT);
        drawerLayout.addDrawerListener(toggle);

        //ActionBarDrawerToggle的syncState()方法会和Toolbar关联，将图标放入到Toolbar上
        toggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
