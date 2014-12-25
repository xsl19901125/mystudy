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
 * @Description: ��λ����UI
 * @author xushenglin
 * @date 2014-12-25 ����2:42:17
 **/
public class LocationUI extends Activity {
	// ��ť
	Button button;
	// �ٶȵ�ͼ��λ��������
	public LocationClient mLocationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_ui);

		// ������λ�ͻ���
		mLocationClient = new LocationClient(this);
		// ���ͻ������ü�����
		mLocationClient.registerLocationListener(new LocationListener());
		// TODO
		// ��ʼ����λѡ��
		initLocalOptition();

		button = (Button) findViewById(R.id.bt_get_location);
		button.setOnClickListener(new OnClickListener() {

			private String tag="clickLocation";

			@Override
			public void onClick(View v) {
				mLocationClient.start();
				
				Log.v(tag, "�����");
			}
		});
	}

	/**
	 * 
	 * @Title: initLocalOptition
	 * @Description: ��ʼ����λѡ��
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 **/
	private void initLocalOptition() {
		LocationClientOption option = new LocationClientOption();
		// �Ƿ���Ҫ��ַ
		option.setLocationMode(LocationMode.Battery_Saving);//���ö�λģʽ
		option.setCoorType(LocationConfig.BaiduGetPwd);//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		option.setScanSpan(1000);//���÷���λ����ļ��ʱ��Ϊ5000ms
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
