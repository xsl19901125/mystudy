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
 * @Description: 主界面的UI
 * @author xushenglin
 * @date 2014年12月21日 下午8:41:46 
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
