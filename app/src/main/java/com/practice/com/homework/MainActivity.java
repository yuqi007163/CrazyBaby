package com.practice.com.homework;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity implements View.OnTouchListener, MyScrollView.OnScrollListener {

    /**绿色控件的位置序号。*/
    private static int GREEN_VIEW_POSITION = 6;
    /**ScrollView中滑动可见区域的顶部位置。*/
    private int mTopY;
    /**ScrollView中滑动可见区域的底部位置.*/
    private int mBottomY;
    /**子ScrollView控件。*/
    private MyScrollView mChildScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChildScrollView = (MyScrollView) findViewById(R.id.child);
        mChildScrollView.setOnTouchListener(this);
        mChildScrollView.setOnScrollListener(this);

        int itemHeight = getResources().getDimensionPixelOffset(R.dimen.item_height)+
            getResources().getDimensionPixelOffset(R.dimen.top)+
            getResources().getDimensionPixelOffset(R.dimen.bottom);
        int childScrollViewHeight = getResources().getDimensionPixelOffset(R.dimen.scroll_view_height);
        mTopY = itemHeight *GREEN_VIEW_POSITION- childScrollViewHeight;
        mBottomY = itemHeight *(GREEN_VIEW_POSITION-1);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            v.getParent().requestDisallowInterceptTouchEvent(true);
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            v.getParent().requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }

    @Override
    public void onScroll(int scrollY,boolean isDown) {
        if(scrollY>mBottomY&&!isDown){  //向上滑动，并且滑动距离超出指定区域
            mChildScrollView.scrollTo(0,mBottomY);
            mChildScrollView.getParent().requestDisallowInterceptTouchEvent(false);
        }else if(scrollY<mTopY&&isDown){ //向下滑动，并且滑动距离超出指定区域
            mChildScrollView.scrollTo(0,mTopY);
            mChildScrollView.getParent().requestDisallowInterceptTouchEvent(false);
        }else{
            mChildScrollView.getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
}
