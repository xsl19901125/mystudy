package com.application;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.config.ConfigInterface;
public class GoOuterApp extends Application {

	private String tag="GoOuterApp";

	@Override
	public void onCreate() {
		super.onCreate();
		 AVOSCloud.initialize(this, ConfigInterface.uID,
				 ConfigInterface.uKey);
		 Log.v(tag, "使用集成的application启动");
		
	}

}
