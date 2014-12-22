package com.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gooutuser.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
/**
 * 
 * @ClassName: MainUI 
 * @Description: 主界面的UI
 * @author xushenglin
 * @date 2014年12月21日 下午8:41:46 
 *  
*
 */
public class MainUI extends FragmentActivity {
	//内部的ViewPager
	private ViewPager vpContent;
	/**
	 * 头部选项卡文本域
	 */
	//需要的服务
	private TextView tvService;
	//历史订单
	private TextView tvHistoryOrder;
	//SlidingMenue控件(slidingMenu内有android 4v包所以要把当前项目下面的包删掉)
	private SlidingMenu slidingMenu;
	//展开SlidingMeue的控件
	private ImageButton imageButtonTriggerSlidingMenue;
	
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWindows();
		setContentView(R.layout.activity_main_ui);
		initView();
	}
	/**
	 * 
	 * @Title: initView 
	 * @Description: 初始化界面控件
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	**/
	private void initView() {
		vpContent=(ViewPager) findViewById(R.id.vp_content);
		tvService=(TextView) findViewById(R.id.tv_service);
		tvHistoryOrder=(TextView) findViewById(R.id.tv_hisorder);
		imageButtonTriggerSlidingMenue=(ImageButton) findViewById(R.id.ibtn_right_menu);
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
