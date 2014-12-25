package com.application;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.config.ConfigInterface;

public class GoOuterApp extends Application {
	// ≤‚ ‘Tag
	private String tag = "GoOuterApp";
	



	@Override
	public void onCreate() {
		super.onCreate();
		AVOSCloud.initialize(this, ConfigInterface.uID, ConfigInterface.uKey);

	

	}


}
