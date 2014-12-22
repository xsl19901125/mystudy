package com.my.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
	//用来存储ViewPager内部的fragment
	private Fragment[] fragments;

	public MyViewPagerAdapter(FragmentManager fm) {
		super(fm);
		fragments=new Fragment[2];
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
