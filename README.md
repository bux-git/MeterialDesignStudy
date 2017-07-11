# Design新控件
谷歌在推出Android5.0的同时推出了全新的设计Material Design，谷歌为了给我们提供更加规范的MD设计风格的控件，在2015年IO大会上推出了Design支持包，Design常用的新控件有下面几种。
## 目录
* [**官方侧滑菜单DrawerLayout**](#DrawerLayout)

##### DrawerLayout  

__1.概念__    
    DrawerLayout其实是一个布局控件，跟LinearLayout等控件是一种东西，但是drawerLayout带有滑动的功能。只要按照drawerLayout的规定布局方式写完布局，就能有侧滑的效果

__2.使用__    
DrawerLayout分为侧边菜单和主内容区两部分，侧边菜单可以根据手势展开与隐藏（drawerLayout自身特性），主内容区的内容可以随着菜单的点击而变化（这需要使用者自己实现）    
```
 <android.support.v4.widget.DrawerLayout
        android:id="@+id/dl_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent">


        <android.support.v7.widget.RecyclerView
            android:id="@+id/rl_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@android:color/holo_red_light">
        </android.support.v7.widget.RecyclerView>


        <android.support.design.widget.NavigationView
            android:id="@+id/ng_view"
            android:layout_width="200dp"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:headerLayout="@layout/main_header_layout"
            app:menu="@menu/main_nav_menu">
        </android.support.design.widget.NavigationView>
    </android.support.v4.widget.DrawerLayout>
```
    其中：DrawerLayout最好为界面的根布局，官网是这样说的，否则可能会出现触摸事件被屏蔽的问题；
    主内容区的布局代码要放在侧滑菜单布局的前面, 因为 XML 顺序意味着按 z 序（层叠顺序）排序，并且抽屉式导航栏必须位于内容顶部；
    侧滑菜单部分的布局（NavigationView）必须设置layout_gravity属性，他表示侧滑菜单是在左边还是右边，而且如果不设置在打开关闭抽屉的时候会报错，
    设置了layout_gravity="start/left"的视图才会被认为是侧滑菜单
__3.DrawerLayout常用方法__  

    /**
      * Adds the specified listener to the list of listeners that will be notified of drawer events.
      *将指定的监听添加到DrawerLayout监听列表中
      * @param listener Listener to notify when drawer events occur.
      * @see #removeDrawerListener(DrawerListener)
    */
    addDrawerListener(@NonNull DrawerListener listener) 
    
    //移除对应的监听
    removeDrawerListener(DrawerListener)
    
    //关闭指定的抽屉视图
    closeDrawer()
    
    //打开指定的抽屉视图
    openDrawer()
    
    检查给定的抽屉视图当前是否处于打开状态。 *被认为是“开放”的抽屉必须已经处于完全可见的状态。检查部分可见性使用情况isDrawerVisible()
    isDrawerOpen()
    
    
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
                    Log.d(TAG, "onDrawerStateChanged: "+newState);
                }
            });
    
__4.ActionBarDrawerToggle与ToolBar__    

    ActionBarDrawerToggle实现了DrawerListener，所以他能做DrawerListener可以做的任何事情，同时他还能将drawerLayout的展开和隐藏与Toolbar的app 图标关联起来，
    点击图标的时候还能展开或者隐藏菜单
    
    将抽屉滑动与Toolbar图标关联
    1. mToolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(mToolbar);
       //显示ToolBar左侧图标
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    2.  
     初始化ActionBarDrawerToggle
     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open_string, R.string.close_string)
    
    3.将ActionBarDrawerToggle添加到DrawerLayout监听列表中
      drawerLayout.addDrawerListener(toggle);
      
    4.将 ActionBarDrawerToggle与Toolbar图标关联
      toggle.syncState();
      这样在抽屉打开关闭时 Toolbar图标将会变动，且点击图标时可以切换抽屉的打开关闭状态
      
      同时一下主题可以设置图标的颜色
        <style name="AppTheme">
              <item name="drawerArrowStyle">@style/DrawerArrowStyle</item>
          </style>
      
          <style name="DrawerArrowStyle" parent="Widget.AppCompat.DrawerArrowToggle">
              <item name="spinBars">true</item>
              <item name="color">@android:color/holo_red_light</item>
          </style>

[回到顶部](#目录)