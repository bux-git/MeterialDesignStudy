package com.dqr.www.meterialdesignstudy;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-06-29 9:14
 */

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.RHolder> {
    private static final String TAG = "MainAdapter";

    @Override
    public MainAdapter.RHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_item_layout, parent, false);
        return new RHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.RHolder holder, final int position) {
        holder.mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d(TAG, "onDrawerSlide: " + position);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.d(TAG, "onDrawerOpened: " + position);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.d(TAG, "onDrawerClosed: " + position);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.d(TAG, "onDrawerStateChanged: " + position);
            }
        });


        holder.mTvDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "mTvDrawer: " + position);
            }
        });

        holder.mTvItem.setText("Item "+position);
        holder.mTvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "mTvItem: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RHolder extends RecyclerView.ViewHolder {
        DrawerLayout mDrawerLayout;
        TextView mTvItem;
        TextView mTvDrawer;

        public RHolder(View itemView) {
            super(itemView);
            mDrawerLayout = findViewById(R.id.dl_drawer);
            mTvItem = findViewById(R.id.tv_item);
            mTvDrawer = findViewById(R.id.tv_drawer);
        }

        private <T extends View> T findViewById(int resId) {
            return (T) itemView.findViewById(resId);
        }
    }
}