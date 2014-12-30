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
	// �Ƿ��ǵ�һ�δ�
	boolean isFirstTimeOpen = true;
	// ������ؿؼ�
	// �ٶȵ�ͼMapView�ؼ�
	private MapView mapView;
	// �ٶȵ�ͼ�ؼ�
	private BaiduMap mBaiduMap;

	// ��λ�ؼ�
	private Button buttonLocation;
/*	//��ȡ�ܱ߷���
	private Button buttonPoi;*/
	// �г��ؼ�
	private Button buttonCallCar;
	// ԤԼ�ؼ�
	private Button buttonOrderCar;

	// ��λ���

	// ��λ�ͻ���
	private LocationClient mLocClient;
	// ��λ������
	/* private LocationListener locationListener = new LocationListener(); */

	// ͼƬ������Ϣ
	BitmapDescriptor mCurrentMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		// ȫ��
		initWindows();
		setContentView(R.layout.activity_call_car_ui);
		// ��ʼ���ؼ�
		initView();
		// ��λ��صĳ�ʼ��
		initLoc();
		//����ҳ��Ĭ�Ͽ�ʼ��λ
		mLocClient.start();

	}

	/*
	 * ��ʼ����λ����ķ���
	 */
	private void initLoc() {
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		// ���ü���
		mLocClient.registerLocationListener(new MyLocatinoListener());
		// ���ö�λѡ��
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType(LocationConfig.BaiduGetPwd); // ������������
		option.setScanSpan(1000);
		option.setIsNeedAddress(true);
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Battery_Saving);// ���ö�λģʽ
		mLocClient.setLocOption(option);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
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
	 * @Description: ��ʼ���ؼ�
	 * @param �趨�ļ�
	 * @return void ��������
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
	 * @Description: ���ڻ�ȡλ�ü�������
	 * @author xushenglin
	 * @date 2014-12-26 ����4:55:59
	 * 
	 * 
	 */
	public class MyLocatinoListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mapView == null) {
				return;
			}
			/**
			 * ��������λ����Ϣ
			 */
			MyLocationData locationData = new MyLocationData.Builder()
					.accuracy(location.getRadius()).direction(100)
					.latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			// ����ͼ����λ����Ϣ
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
	 * @Description:(��ʼ������)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 * 
	 */
	private void initWindows() {
		// ȥ����������������setCOntentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��һ����������Ҫ��ӵ��µĴ������Եı�־λ���൱��ֵ��
		// �ڶ��� �����Ǵ��ڵ���һ�����Ա�־λ��Ҫ�޸ģ��൱�ڿ��أ�
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
