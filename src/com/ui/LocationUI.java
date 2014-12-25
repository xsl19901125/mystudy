package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.avos.avoscloud.LogUtil.log;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.config.LocationConfig;
import com.example.gooutuser.R;
import com.map.listener.LocationListener;

/**
 * 
 * @ClassName: LocationUI
 * @Description: 定位服务UI
 * @author xushenglin
 * @date 2014-12-25 下午2:42:17
 **/
public class LocationUI extends Activity {
	// 按钮
	Button button;
	// 百度地图定位对象引用
	public LocationClient mLocationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_ui);

		// 创建定位客户端
		mLocationClient = new LocationClient(this);
		// 给客户端设置监听器
		mLocationClient.registerLocationListener(new LocationListener());
		// TODO
		// 初始化定位选项
		initLocalOptition();

		button = (Button) findViewById(R.id.bt_get_location);
		button.setOnClickListener(new OnClickListener() {

			private String tag="clickLocation";

			@Override
			public void onClick(View v) {
				mLocationClient.start();
				
				Log.v(tag, "点击了");
			}
		});
	}

	/**
	 * 
	 * @Title: initLocalOptition
	 * @Description: 初始化定位选项
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 **/
	private void initLocalOptition() {
		LocationClientOption option = new LocationClientOption();
		// 是否需要地址
		option.setLocationMode(LocationMode.Battery_Saving);//设置定位模式
		option.setCoorType(LocationConfig.BaiduGetPwd);//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(1000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);

	}



	@Override
	protected void onStop() {
		super.onStop();
		mLocationClient.stop();
		LocationUI.this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.location_ui, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
