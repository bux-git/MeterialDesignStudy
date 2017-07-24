## 8
## CoordinatorLayout    
__一.概念__    
译为：协调者布局    
>1.首先可以理解Coordinatorlayout是一个FrameLayout升级版本 

>2.重要功能：CoordinatorLayout可以用来协调其子view之间动作的交互     
如：协调滑动控件和AppBarLayout之间的交互等等，  
CoordinatorLayout 实现子View之间的交互是靠Behavior来实现的        


__二.使用__    
CoordinatorLayout子View之间是如何协调的： 
>1.根据前面与：    
       3.AppbarLayout  
       4.CollapsingToolbarLayout  
       6.SnackBar  
       6.FloatingActionButton  
       等使用的情况，子View之间相互协调是通过CoordinatorLayout的布局属性app:layout_behavior来设置的   
       layout_behavior 属性定义了这个View如何和其他View互相交互的行为, 其值填写的是一个class的名字(全称带包名) 
       这个值指定的类必须是 CoordinatorLayout.Behavior<V> 的子类, 我们也可以自定义一个该类继承于它, 以此来写自己想要的交互效果.   
       
__三.Behavior__   
   
__1.概述__
>CoordinatorLayout的诸多功能全部依赖与CoordinatorLayout.Behavior来实现   
通过为CoordinatorLayout的直接子view设置一个Behavior，CoordinatorLayout会遍历一遍自己的直接子View，      
一个一个的调用子view中的Behavio就可以拦截touch events, window insets, measurement, layout, 和 nested scrolling等动作。      
Design Library大量利用了Behaviors来实现你所看到的功能

__2.Behavior的创建__
>2.1、创建behavior,需要继承 [CoordinatorLayout.Behavior](https://developer.android.google.cn/reference/android/support/design/widget/CoordinatorLayout.Behavior.html)
或其子View
>>  

    public class FollowUpDownBehavior extends CoordinatorLayout.Behavior {
    
      public FollowUpDownBehavior(Context context, AttributeSet attrs) {
          super(context, attrs);
  
        }
    }
>>这样就可以将这个Behavior设置给任何View,如果只想设置给某一些特定类型的View则可以传入泛型如:  

    FollowUpDownBehavior extends CoordinatorLayout.Behavior<Button>
__3.设置Behavior__    
设置Behavior一共有3种方式:  
>3.1 在代码中设置Behavior     
>>CoordinatorLayout.LayoutParam中可以存储Behavior布局属性,所以在代码中：

        FollowUpDownBehavior behavior = new FollowUpDownBehavior();
        CoordinatorLayout.LayoutParams layoutParams= (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);

>3.2 在XML中设置        
>>在xml布局中直接设置属性值    

    <View android:layout_width="50dp"
          android:layout_height="20dp"
          android:background="@color/black"
          app:layout_behavior="com.dqr.www.meterialdesignstudy.coordinatorlayout.behavior.FollowUpDownBehavior"
          app:target="@id/moveView"></View>
>>在XML设置属性，初始化Behavior时，是使用的FollowUpDownBehavior(Context context, AttributeSet attrs)   
两个参数的构造函数,有传入AttributeSet所以可以自定义一些属性然后在xml中设置，Behavior中接收并使用，   
如app:target属性设置一个目标ID   


>3.3 在View类上添加默认的Behavior   
>>在自定义View时我们希望这个View自带一个Behavior，而不需要去另外设置，我们可以在自定义View类上设置    
注释:

    @CoordinatorLayout.DefaultBehavior(BtnTestBehavior.class)
    public class TempView extends View {
    }

__Behavior的功能__     

__1.拦截Touch Events__    
>CoordinatorLayout会在他的onInterceptTouchEvent()中将事件MotionEvent传递到子View的    
Behavior.onInterceptTouchEvent()中，让Behavior也可以拦截触摸事件,   
如果Behavior.onInterceptTouchEvent()返回true,则Behavior.onTouchEvent()将会收到   
后续触摸事件，而View将不会收到后续的触摸事件    

CoordinatorLayout的事件分发过程
>>首先ViewGroup/View 的事件分派, 事件分派是有两个过程的:  [深入理解CoordinatorLayout原文](http://www.jianshu.com/p/26439595ffef)    

>>捕获过程：从根元素到子元素依次调用onInterceptTouchEvent,检测是否有View要拦截触摸事件 
如果有View拦截了立即进入冒泡过程，否则一直传递到最末尾的元素再进入到冒泡过程.   

>>冒泡过程:从底层往上冒泡,一次调用onTouchEvent,如果有View消耗了事件,则不再继续向上传递，否则一直传递 
到根元素.

>CoordinatorLayout事件分发  
>>CoordinatorLayout在他的onInterceptTouchEvent中去遍历所有的子View,并调用子View的Behavior.onInterceptTouchEvent方法 
如果在Behavior.onInterceptTouchEvent方法中返回了true拦截了该事件，则该Behavior就可以在onTouchEvent    
中处理触摸事件，而这个Behavior对应的View将不会收到触摸时间 

>这样的设置可以使得处理例如手势的逻辑可以完全从具体的某个View解耦出来，可以给不同的View设置相同的   
Behavior来获得处理相同手势的功能，代码复用率极高.   

__2.子View之间的依赖__       
>Behaviors的强大之处在于在View之间建立依赖关系－当另一个View改变的时候，你的Behavior会得到一个callback，根据外部条件改变它的功能    

在 CoordinatorLayout.LayoutParams 中定义了一个View是否依赖( dependsOn ) 另一个View:

    boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency == mAnchorDirectChild
                || (mBehavior != null && mBehavior.layoutDependsOn(parent, child, dependency));
    }


Behaviors依赖于View有两种形式:
>>1.在Behavior.layoutDependsOn()中返回true  
>>2.使用CoordinatorLayout的layout_anchor 属性之时。它和layout_anchorGravity 属性结合，可以让你有效的把两个View捆绑在一起。比如，你可以把一个FloatingActionButton锚定在一个AppBarLayout上，那么如果AppBarLayout滚动出屏幕，FloatingActionButton.Behavior将使用隐式的依赖去隐藏FAB    

>View之间关联之后，当依赖View被移除的时候，将会回调Behavior.onDependentViewRemoved() 
 当依赖的View发生变化的时候（比如：调整大小或者重置自己的position），得到回调 onDependentViewChanged()  
 我们可以在这2个方法中处理关于自身View的一些事情     
 >>这个把View绑定在一起的能力正是Design Library那些酷炫功能的工作原理   
 －以FloatingActionButton与Snackbar之间的交互为例。FAB的 Behavior依赖于被添加到CoordinatorLayout的Snackbar，.
 然后它使用onDependentViewChanged()  callback来将FAB向上移动，以避免和Snackbar重叠。

CoordinatorLayout的三个子View A B  C之间的依赖可以有如下几种:
可以 多个View同时依赖同一个View: B C 同时依赖A
被依赖的View可以继续依赖其他View A 依赖B  B 依赖C
也可以单独依赖 A 依赖 B 
但是不能循环依赖 ：A 依赖B  B 依赖C C依赖A 



__3.嵌套滚动__  

NestedScrollingChild接口  

    public interface NestedScrollingChild {
        /**
         * 设置是否开启嵌套滑动
         * @param enabled true to enable nested scrolling, false to disable
         * @see #isNestedScrollingEnabled()
         */
        public void setNestedScrollingEnabled(boolean enabled);
    
        /**是否已经开启嵌套滑动
         * @return true if nested scrolling is enabled
         * @see #setNestedScrollingEnabled(boolean)
         */
        public boolean isNestedScrollingEnabled();
    
        /**
         * Begin a nestable scroll operation along the given axes.
         *
         * <p>A view starting a nested scroll promises to abide by the following contract:</p>
         *
         * <p>The view will call startNestedScroll upon initiating a scroll operation. In the case
         * of a touch scroll this corresponds to the initial {@link MotionEvent#ACTION_DOWN}.
         * In the case of touch scrolling the nested scroll will be terminated automatically in
         * the same manner as {@link ViewParent#requestDisallowInterceptTouchEvent(boolean)}.
         * In the event of programmatic scrolling the caller must explicitly call
         * {@link #stopNestedScroll()} to indicate the end of the nested scroll.</p>
         *
         * <p>If <code>startNestedScroll</code> returns true, a cooperative parent was found.
         * If it returns false the caller may ignore the rest of this contract until the next scroll.
         * Calling startNestedScroll while a nested scroll is already in progress will return true.</p>
         *
         * <p>At each incremental step of the scroll the caller should invoke
         * {@link #dispatchNestedPreScroll(int, int, int[], int[]) dispatchNestedPreScroll}
         * once it has calculated the requested scrolling delta. If it returns true the nested scrolling
         * parent at least partially consumed the scroll and the caller should adjust the amount it
         * scrolls by.</p>
         *
         * <p>After applying the remainder of the scroll delta the caller should invoke
         * {@link #dispatchNestedScroll(int, int, int, int, int[]) dispatchNestedScroll}, passing
         * both the delta consumed and the delta unconsumed. A nested scrolling parent may treat
         * these values differently. See
         * {@link NestedScrollingParent#onNestedScroll(View, int, int, int, int)}.
         * </p>
         *
         * @param axes Flags consisting of a combination of {@link ViewCompat#SCROLL_AXIS_HORIZONTAL}
         *             and/or {@link ViewCompat#SCROLL_AXIS_VERTICAL}.
         * @return true if a cooperative parent was found and nested scrolling has been enabled for
         *         the current gesture.
         *
         * @see #stopNestedScroll()
         * @see #dispatchNestedPreScroll(int, int, int[], int[])
         * @see #dispatchNestedScroll(int, int, int, int, int[])
         */
        public boolean startNestedScroll(int axes);
    
        /**
         * Stop a nested scroll in progress.
         *
         * <p>Calling this method when a nested scroll is not currently in progress is harmless.</p>
         *
         * @see #startNestedScroll(int)
         */
        public void stopNestedScroll();
    
        /**
         * Returns true if this view has a nested scrolling parent.
         *
         * <p>The presence of a nested scrolling parent indicates that this view has initiated
         * a nested scroll and it was accepted by an ancestor view further up the view hierarchy.</p>
         *
         * @return whether this view has a nested scrolling parent
         */
        public boolean hasNestedScrollingParent();
    
        /**
         * Dispatch one step of a nested scroll in progress.
         *
         * <p>Implementations of views that support nested scrolling should call this to report
         * info about a scroll in progress to the current nested scrolling parent. If a nested scroll
         * is not currently in progress or nested scrolling is not
         * {@link #isNestedScrollingEnabled() enabled} for this view this method does nothing.</p>
         *
         * <p>Compatible View implementations should also call
         * {@link #dispatchNestedPreScroll(int, int, int[], int[]) dispatchNestedPreScroll} before
         * consuming a component of the scroll event themselves.</p>
         *
         * @param dxConsumed Horizontal distance in pixels consumed by this view during this scroll step
         * @param dyConsumed Vertical distance in pixels consumed by this view during this scroll step
         * @param dxUnconsumed Horizontal scroll distance in pixels not consumed by this view
         * @param dyUnconsumed Horizontal scroll distance in pixels not consumed by this view
         * @param offsetInWindow Optional. If not null, on return this will contain the offset
         *                       in local view coordinates of this view from before this operation
         *                       to after it completes. View implementations may use this to adjust
         *                       expected input coordinate tracking.
         * @return true if the event was dispatched, false if it could not be dispatched.
         * @see #dispatchNestedPreScroll(int, int, int[], int[])
         */
        public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow);
    
        /**
         * Dispatch one step of a nested scroll in progress before this view consumes any portion of it.
         *
         * <p>Nested pre-scroll events are to nested scroll events what touch intercept is to touch.
         * <code>dispatchNestedPreScroll</code> offers an opportunity for the parent view in a nested
         * scrolling operation to consume some or all of the scroll operation before the child view
         * consumes it.</p>
         *
         * @param dx Horizontal scroll distance in pixels
         * @param dy Vertical scroll distance in pixels
         * @param consumed Output. If not null, consumed[0] will contain the consumed component of dx
         *                 and consumed[1] the consumed dy.
         * @param offsetInWindow Optional. If not null, on return this will contain the offset
         *                       in local view coordinates of this view from before this operation
         *                       to after it completes. View implementations may use this to adjust
         *                       expected input coordinate tracking.
         * @return true if the parent consumed some or all of the scroll delta
         * @see #dispatchNestedScroll(int, int, int, int, int[])
         */
        public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow);
    
        /**
         * Dispatch a fling to a nested scrolling parent.
         *
         * <p>This method should be used to indicate that a nested scrolling child has detected
         * suitable conditions for a fling. Generally this means that a touch scroll has ended with a
         * {@link VelocityTracker velocity} in the direction of scrolling that meets or exceeds
         * the {@link ViewConfiguration#getScaledMinimumFlingVelocity() minimum fling velocity}
         * along a scrollable axis.</p>
         *
         * <p>If a nested scrolling child view would normally fling but it is at the edge of
         * its own content, it can use this method to delegate the fling to its nested scrolling
         * parent instead. The parent may optionally consume the fling or observe a child fling.</p>
         *
         * @param velocityX Horizontal fling velocity in pixels per second
         * @param velocityY Vertical fling velocity in pixels per second
         * @param consumed true if the child consumed the fling, false otherwise
         * @return true if the nested scrolling parent consumed or otherwise reacted to the fling
         */
        public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed);
    
        /**
         * Dispatch a fling to a nested scrolling parent before it is processed by this view.
         *
         * <p>Nested pre-fling events are to nested fling events what touch intercept is to touch
         * and what nested pre-scroll is to nested scroll. <code>dispatchNestedPreFling</code>
         * offsets an opportunity for the parent view in a nested fling to fully consume the fling
         * before the child view consumes it. If this method returns <code>true</code>, a nested
         * parent view consumed the fling and this view should not scroll as a result.</p>
         *
         * <p>For a better user experience, only one view in a nested scrolling chain should consume
         * the fling at a time. If a parent view consumed the fling this method will return false.
         * Custom view implementations should account for this in two ways:</p>
         *
         * <ul>
         *     <li>If a custom view is paged and needs to settle to a fixed page-point, do not
         *     call <code>dispatchNestedPreFling</code>; consume the fling and settle to a valid
         *     position regardless.</li>
         *     <li>If a nested parent does consume the fling, this view should not scroll at all,
         *     even to settle back to a valid idle position.</li>
         * </ul>
         *
         * <p>Views should also not offer fling velocities to nested parent views along an axis
         * where scrolling is not currently supported; a {@link android.widget.ScrollView ScrollView}
         * should not offer a horizontal fling velocity to its parents since scrolling along that
         * axis is not permitted and carrying velocity along that motion does not make sense.</p>
         *
         * @param velocityX Horizontal fling velocity in pixels per second
         * @param velocityY Vertical fling velocity in pixels per second
         * @return true if a nested scrolling parent consumed the fling
         */
        public boolean dispatchNestedPreFling(float velocityX, float velocityY);
    }

[自定义Behavior的艺术探索-仿UC浏览器主页](http://www.jianshu.com/p/f7989a2a3ec2#)     

__4.拦截Window Insets__   
__5.拦截Measurement 和 layout__    
[拦截一切的CoordinatorLayout Behavior](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0224/3991.html)
