package com.dqr.www.meterialdesignstudy.appbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dqr.www.meterialdesignstudy.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-07-12 16:35
 */

public class AppBarLayout01 extends AppCompatActivity {
    private static final String TAG = "AppBarLayout01";
    AppBarLayout.LayoutParams  appParams;
    View appChildScrollView;
    AppBarLayout mAppBarLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_01);
        appChildScrollView = findViewById(R.id.child_scroll_view);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appParams= (AppBarLayout.LayoutParams) appChildScrollView.getLayoutParams();

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: verticalOffset:"+verticalOffset+"  TotalScrollRange:"+mAppBarLayout.getTotalScrollRange());
            }
        });



    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.app_bar_menu,menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_scroll:
                appParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.menu_enterAlways:
                appParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case R.id.menu_enterAlwaysCollapsed:
                appParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
                break;
            case R.id.menu_exitUntilCollapsed:
                appParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                break;
            case R.id.menu_snap:
                appParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED| AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
                break;
        }
        appParams.setScrollInterpolator(new AccelerateDecelerateInterpolator());
        appChildScrollView.requestLayout();
        return super.onOptionsItemSelected(item);
    }
}
