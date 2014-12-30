package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.config.LocationConfig;
import com.example.gooutuser.R;

public class CallCarUI extends Activity {
	// 是否是第一次打开
	boolean isFirstTimeOpen = true;
	// 布局相关控件
	// 百度地图MapView控件
	private MapView mapView;
	// 百度地图控件
	private BaiduMap mBaiduMap;

	// 定位控件
	private Button buttonLocation;
/*	//获取周边服务
	private Button buttonPoi;*/
	// 叫车控件
	private Button buttonCallCar;
	// 预约控件
	private Button buttonOrderCar;

	// 定位相关

	// 定位客户端
	private LocationClient mLocClient;
	// 定位监听器
	/* private LocationListener locationListener = new LocationListener(); */

	// 图片描述信息
	BitmapDescriptor mCurrentMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		// 全屏
		initWindows();
		setContentView(R.layout.activity_call_car_ui);
		// 初始化控件
		initView();
		// 定位相关的初始化
		initLoc();
		//进入页面默认开始定位
		mLocClient.start();

	}

	/*
	 * 初始化定位服务的方法
	 */
	private void initLoc() {
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		// 设置监听
		mLocClient.registerLocationListener(new MyLocatinoListener());
		// 设置定位选项
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType(LocationConfig.BaiduGetPwd); // 设置坐标类型
		option.setScanSpan(1000);
		option.setIsNeedAddress(true);
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
		mLocClient.setLocOption(option);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
		super.onDestroy();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 
	 * @Title: initView
	 * @Description: 初始化控件
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void initView() {
		mapView = (MapView) findViewById(R.id.bmapView);
	
		mBaiduMap = mapView.getMap();
		buttonLocation = (Button) findViewById(R.id.button_get_location);
	/*	buttonPoi=(Button) findViewById(R.id.button_get_poi);*/
		buttonCallCar = (Button) findViewById(R.id.button_call_car);
		buttonOrderCar = (Button) findViewById(R.id.button_order_car);
		buttonLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mLocClient.start();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.call_car_ui, menu);
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

	/**
	 * 
	 * @ClassName: MyLocatinoListener
	 * @Description: 用于获取位置监听的类
	 * @author xushenglin
	 * @date 2014-12-26 下午4:55:59
	 * 
	 * 
	 */
	public class MyLocatinoListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null) {
				return;
			}
			/**
			 * 创建个人位置信息
			 */
			MyLocationData locationData = new MyLocationData.Builder()
					.accuracy(location.getRadius()).direction(100)
					.latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			// 往地图设置位置信息
			mBaiduMap.setMyLocationData(locationData);
			if (isFirstTimeOpen) {
				isFirstTimeOpen = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}

		}

	}

	/**
	 * 
	 * @Title: initWindows
	 * @Description:(初始化窗口)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void initWindows() {
		// 去掉标题栏，必须在setCOntentView之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 第一个参数是需要添加的新的窗口属性的标志位（相当于值）
		// 第二个 参数是窗口的哪一个特性标志位需要修改（相当于开关）
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
