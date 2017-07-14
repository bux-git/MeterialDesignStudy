# Design新控件
谷歌在推出Android5.0的同时推出了全新的设计Material Design，谷歌为了给我们提供更加规范的MD设计风格的控件，在2015年IO大会上推出了Design支持包，Design常用的新控件有下面几种。
## 目录
* [**1.官方侧滑菜单DrawerLayout**](#1)
* [**2.导航栏NavigationView**](#2)
* [**3.AppbarLayout**](#3)
* [**4.CollapsingToolbarLayout**](#4)
* [**5.TextInputLayout**](#5)

## 1
## 官方侧滑菜单DrawerLayout 

![imgshttps://github.com/bux-git/MeterialDesignStudy/raw/master/imges/drawerlayout01.gif1)     

__一.概念__    
    DrawerLayout其实是一个布局控件，跟LinearLayout等控件是一种东西，但是drawerLayout带有滑动的功能。只要按照drawerLayout的规定布局方式写完布局，就能有侧滑的效果

__二.使用__    
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
__三.DrawerLayout常用方法__  

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
    
__四.ActionBarDrawerToggle与ToolBar__    

    ActionBarDrawerToggle实现了DrawerListener，所以他能做DrawerListener可以做的任何事情，同时他还能将drawerLayout的展开和隐藏与Toolbar的app 图标关联起来，
    点击图标的时候还能展开或者隐藏菜单
    
    将抽屉滑动与Toolbar图标关联
    1. mToolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(mToolbar);
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

## 2
## NavigationView

__一.概念__    
    NavigationView顾名思义是指导航菜单栏,一般配合DrawerLayout使用作为侧滑菜单栏  
__二.使用__    
    NavigationView需要接收几个必要的参数、一个用于显示头部的布局app:headerLayout="@layout/main_header_layout"（可选） 以及用于建立导向选项的菜单app:menu="@menu/main_nav_menu"，这些都设置完之后，你就只添加监听选中事件的listener就行了。   
    其中app:menu配置的是一个菜单文件    
    
    <menu xmlns:android="http://schemas.android.com/apk/res/android">
        <group android:id="@+id/group1"
               android:checkableBehavior="single">
            <item
                android:id="@+id/app_bar"
                android:icon="@android:drawable/ic_delete"
                android:title="AppbarActivity"></item>
        </group>
    
        <group android:id="@+id/group2"
               android:checkableBehavior="single">
            <item
                android:icon="@android:drawable/ic_menu_save"
                android:title="Start"></item>
        </group>
    <item android:id="@+id/sub1" android:title="sub item">
        <menu>
            <item
                android:icon="@android:drawable/ic_menu_save"
                android:title="Start"></item>
        </menu>
    </item>
    </menu>
__其中:__     
        __1.group表示分组 checkableBehavior用来设置item选中模式 有3个值 表示选中状态 single单选 all多选 none 默认   
           item可以表示一个子项 也可在其中加入menu添加子菜单来实现带有头部的分组效果  
         每个group 和menu子菜单都会在顶部产生移到横线__    
         
__2.设置item选中事件__
        
        
         mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    }
                });
                
__注意：这样只可以设置group中的item选中状态__   
__item子菜单设置选中状态__   

            1.首先设置子菜单item android:checkable="true"
            2.public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setChecked(true);
                     if (mPreMenuItem != null) mPreMenuItem.setChecked(false);
                     item.setChecked(true);
                      drawerLayout.closeDrawers();
                      mPreMenuItem = item;
                      return true;
             }
         
__三.常用属性__

    app:itemIconTint="" 修改图标颜色
    app:itemBackground="" item背景颜色
    app:itemTextColor=""  item 文字颜色
## 3
#### AppBarLayout
![imgshttps://github.com/bux-git/MeterialDesignStudy/raw/master/imges/appbarlayout01.gif1)  
__一.概念__    

    AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。
    AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，其内部的子View实现何种动作   
__解释:__  
__1.AppBarLayout内部子View可以和一个可滚动的View的滑动事件产生关联，从而使AppBarLayout内部的子View执行相关联的滑动动作，   
内部子View可以是Toolbar、任何View或者布局__   
>注意：AppbarLayout 严重依赖于CoordinatorLayout，必须用于CoordinatorLayout 的直接子View，如果你将AppbarLayout 放在其他的ViewGroup 里面，那么它的这些功能是无效的

__2.AppBarLayout如何与可滚动的View关联:__    

    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        
        app:layout_behavior="@string/appbar_scrolling_view_behavior">
       <!--将你的内容放在这里-->
    </android.support.v4.widget.NestedScrollView>

>属性：app:layout_behavior="@string/appbar_scrolling_view_behavior",它就是将此View与AppBarLayout关联，指定Behavior的，appbar_scrolling_view_behavior对应的类的名称是：android.support.design.widget.AppBarLayout$ScrollingViewBehavior

__3.AppBarLayout可以与那些View关联__   
>__3.1,根据概念首先这个View必须是可以滚动的如ScrollView RecyclerView等     
3.2,还有一个就是此View必须实现NestedScrollingChild接口__
        
    所以ScrollView ListView GridView是不能直接和AppBarLayout联合使用的，需要自己实现NestedScrollingChild接口后才行
    已经实现此接口的如：RecyclerView,NestedScrollView等

__4.AppBarLayout内部子View如何执行动作__
>__1.内部的子View通过在布局中加app:layout_scrollFlags设置执行的动作__   
>__layout_scrollFlagsyou 有如下几个值：__  

>>* __1、 scroll ,子View 添加layout_scrollFlags属性 的值scroll 时，这个View将会随着可滚动View（如：NestedScrollView,NestedScrollView 来代替可滚动的View ）一起滚动，就好像子View 是属于ScrollView的一部分一样。__

>>* __2、 enterAlways ,子View 添加layout_scrollFlags属性 的值有enterAlways 时, NestedScrollView 滑动时，子View将剥夺滑动事件先执行动作后 ，NestedScrollView 再滑动。
注意：要与scroll 搭配使用，否者是不能滑动的。__

>>* __3、 enterAlwaysCollapsed ,必须配合scroll|enterAlways 一起使用, enterAlwaysCollapsed 是对enterAlways 的补充，
NestedScrollView 向上滑动和Scroll，enterAlways效果一样 ，向下滑动时 滑动View先下滑到最小高度 minHeight（最小高度）指定的，然后让NestedScrollView滑动到顶点后，滑动View再继续下滑到最大值。__  
  
>>* __4、exitUntilCollapsed,必须配合scroll 当NestedScrollView向上滑动时，滑动View先响应滑动事件，滑动至最小高度，(也就是通过minHeight 设置的最小高度）后，就固定不动了，再把滑动事件交给 NestedScrollView 继续滑动。__   

>>* __5、snap,意思是：会给滑动view的滑动事件添加一个自动滚动属性，   
在滚动结束后，view滑动到部分可见时，如果隐藏区域比显示区域大则它将滚动离开屏幕，显示区域比影藏区域大，它将自动滚动到完全显示。必须配合scroll使用__     

__5.app:layout_scrollInterpolator属性指定滚动动画效果的插值器.__      
   
__6.AppBarLayout常用方法__
>* addOnOffsetChangedListener 当AppbarLayout 的偏移发生改变的时候回调，也就是子View滑动。

>* getTotalScrollRange 返回AppbarLayout 所有子View的滑动范围

>* removeOnOffsetChangedListener 移除监听器

>* setExpanded (boolean expanded, boolean animate)设置AppbarLayout 是展开状态还是折叠状态，animate 参数控制切换到新的状态时是否需要动画

>* setExpanded (boolean expanded) 设置AppbarLayout 是展开状态还是折叠状态,默认有动画
  
## 4
## CollapsingToolbarLayout
[imageshttps://github.com/bux-git/MeterialDesignStudy/raw/master/imges/collspasingtoolbarlayout01.gif1)
__一.概念__    
CollapsingToolbarLayout是用来对Toolbar进行再次包装的ViewGroup，主要是用于实现折叠的App Bar效果。      
它需要作为AppBarLayout的直接子View，并且需要作为AppBarLayout的关联滑动View   

CollapsingToolbarLayout包含以下功能：  

* 1.Collapsing title(折叠标题) 当布局全部可见的时候，title 是最大的，当布局开始滑出屏幕，title 将变得越来越小，你可以通过setTitle（CharSequence） 来设置要显示的标题


    >注意：Toolbar 和CollapsingToolbarLayout 同时设置了title时，不会显示Toolbartitle而是显示CollapsingToolbarLayout 的title，如果要显示Toolbar 的title，你可一在代码中添加如下代码：collapsingToolbarLayout.setTitle("");
    
* 2.Content scrim(内容纱布) 当CollapsingToolbarLayout滑动到一个确定的阀值时将显示或者隐藏内容纱布，可以通过setContentScrim(Drawable)来设置纱布的图片。
 
 >提醒：纱布可以是图片也可以是颜色色值，如果要显示颜色，在xml 布局文件中用contentScrim属性添加，代码如下：
 app:contentScrim="@color/colorPrimary" 
 
* 3.Status bar scrim（状态栏纱布） 当CollapsingToolbarLayout滑动到一个确定的阀值时，状态栏显示或隐藏纱布，你可以通过setStatusBarScrim(Drawable)来设置纱布图片。
 
 >注意：同内容纱布一样，状态栏纱布可以是图片也可以是一个颜色值，如果要显示颜色值，在xml 中用statusBarScrim 属性指定。
 
* 4.Parallax scrolling children（有视差地滚动子View） 让CollapsingToolbarLayout 的子View 可以有视差的滚动，需要在xml中用 添加如下代码：
 
 >app:layout_collapseMode="parallax"
 
* 5.Pinned position children（固定子View的位置）子View可以固定在全局空间内，这对于实现了折叠并且允许通过滚动布局来固定Toolbar 这种情况非常有用。在xml 中将collapseMode设为pin，代码如下：
 
 > app:layout_collapseMode="pin"
 
* 其他属性： 

    >collapsedTitleGravity：折叠时Toolbar标题位置   
    expandedTitleGravity:展开时Toolbar标题位置     
    titleEnabled：滑动时,设置是否应该显示自己的标题。标题将根据滚动偏移而收缩和增长。默认为true

CollapsingToolbarLayout使用时  
>1.作为AppBarLayout的子View并设置滑动关联动作如: app:layout_scrollFlags="scroll|exitUntilCollapsed"  
2.根据需求设置自身标题等一些相关属性     
3.添加Toolbar和其他子View 

## 5
## TextInputLayout

__1.概念__  
TextInputLayout 将EditText包裹起来能够辅助EditText实现一些如hint 以浮动标签的形式显示出来，同时可以通过setErrorEnabled(boolean)和setError(CharSequence)来显示错误信息等   
每一个TextInputLayout中只能有一个EditText
  
__XML属性&常用方法__  
  
* counterEnabled 对应方法 setCounterEnabled(boolean)

    >用于设置字符计数器的显示与隐藏，会在布局右下角显示输入字符的进度：1/10这样
    
* counterMaxLength 对应方法 setCounterMaxLength(int)
    
  >设置字符计数器的最大长度。（仅用于设置计数器最大值，并不影响文本实际能输入的最大长度）

* errorEnabled 对应方法 setErrorEnabled(boolean)
    
  >用于设置错误提示是否显示

* hint 对应方法 setHint(CharSequence)
    
  >设置输入框的提示语

* hintAnimationEnabled 对应方法 setHintAnimationEnabled(boolean)
    
  >开启或关闭hint浮动成标签的动画效果
  
* hint 对应方法 setHint(CharSequence)
    
  >设置输入框的提示语
  
* hintEnabled 对应方法 setHintEnabled(boolean)
    
  >开启或关闭hint浮动的功能，设为false的话就和之前的EditText一样，在输入文字后，提示语就消失了
  
* hintTextAppearance 对应方法 setHintTextAppearance(int)
    
  >设置hint的style，字体颜色，字体大小等，可引用系统自带的也可以自定义。若要使用请统一使用，以保证APP体验的统一性
  
__当文本输入类型为密码时，系统提供了一个开关来控制密码是否可见（默认为眼睛👁）。此为design包24.0.2新提供的功能。__

* passwordToggleEnabled 对应方法 setPasswordVisibilityToggleEnabled(boolean)
    
  >控制密码可见开关是否启用。设为false则该功能不启用，密码输入框右侧也没有控制密码可见与否的开关

* passwordToggleDrawable 对应方法 setPasswordVisibilityToggleDrawable(Drawable)
    
  >设置密码可见开关的图标。通常我们会在不同的情况下设定不同的图标，可通过自定义一个selector，根据“state_checked”属性来控制图标的切换
  
* passwordToggleTint 对应方法 setPasswordVisibilityToggleTintList(ColorStateList)
    
  >控制密码可见开关图标的颜色。在开启或关闭的状态下我们可以设定不同的颜色，可通过自定义一个color的selector，根据“state_checked”和“state_selected”属性来控制颜色的切换
  
* hintEnabled 对应方法 setHintEnabled(boolean)
    
  >开启或关闭hint浮动的功能，设为false的话就和之前的EditText一样，在输入文字后，提示语就消失了   
  
[TextInputLayout详解](https://zhuanlan.zhihu.com/p/22402340)
### 学习资料               
[Material Design之 AppbarLayout 开发实践总结](http://www.jianshu.com/p/ac56f11e7ce1)    
[玩转AppBarLayout，更酷炫的顶部栏](http://blog.csdn.net/huachao1001/article/details/51558835)    
[CoordinatorLayout, AppBarLayout, CollapsingToolbarLayout使用](http://www.cnblogs.com/mengdd/p/5641187.html)    
[]()    
[]()    
[]()    
[]()    
[回到顶部](#目录) 