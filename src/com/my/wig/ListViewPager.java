package com.my.wig;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * @ClassName: ListViewPager
 * @Description: �Լ���дviewPager
 * @author xushenglin
 * @date 2014-12-22 ����3:17:31
 * */
public class ListViewPager extends ViewPager {
	// mViewTouchMode��ʾViewPager�Ƿ�ȫȨ���ƻ����¼���Ĭ��Ϊfalse����������
	private boolean mViewTouchMode = false;

	public ListViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setViewTouchMode(boolean b) {
		if (b && !isFakeDragging()) {
			// ȫȨ���ƻ����¼�
			beginFakeDrag();
		} else if (!b && isFakeDragging()) {
			// ��ֹ���ƻ����¼�
			endFakeDrag();
		}
		mViewTouchMode = b;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (mViewTouchMode) {
			return false;
		}
		return super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try {
			return super.onTouchEvent(ev);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean arrowScroll(int direction) {
		if (mViewTouchMode) return false;
        if (direction != FOCUS_LEFT && direction != FOCUS_RIGHT) return false;
        return super.arrowScroll(direction);
	}

}
