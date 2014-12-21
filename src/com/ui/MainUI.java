package com.ui;

import com.example.gooutuser.R;
import com.example.gooutuser.R.id;
import com.example.gooutuser.R.layout;
import com.example.gooutuser.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
/**
 * 
 * @ClassName: MainUI 
 * @Description: �������UI
 * @author xushenglin
 * @date 2014��12��21�� ����8:41:46 
 *  
*
 */
public class MainUI extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWindows();
		setContentView(R.layout.activity_main_ui);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_ui, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
