package com.my.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.main.ui.fragment.MainServiceFragment;
import com.test.TestFragmentA;
import com.test.TestFragmentB;
/**
 * 
 * @ClassName: MyViewPagerAdapter 
 * @Description: �����������Adapter
 * @author xushenglin
 * @date 2014-12-23 ����9:56:23   
*
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
	//�����洢ViewPager�ڲ���fragment
	private Fragment[] fragments;

	public MyViewPagerAdapter(FragmentManager fm) {
		super(fm);
		fragments=new Fragment[2];
		//TODO
		//��������Fragment
		fragments[0]=new MainServiceFragment();
		fragments[1]=new TestFragmentB();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}

}
