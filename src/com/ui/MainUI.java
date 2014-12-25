package com.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gooutuser.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.main.ui.fragment.MenuFragment;
import com.my.viewpager.adapter.MyViewPagerAdapter;
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
		//初始化ViewPager
		initViewPager();
		//初始化sliding按钮监听
		intiButtonListener();
		//初始化设置滑动窗口
		initSlidingMenue();
	}
	private void initSlidingMenue() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE); // 滑动方式
		slidingMenu.setShadowDrawable(R.drawable.shadow_right); // 阴影
		slidingMenu.setShadowWidth(30); // 阴影宽度
		slidingMenu.setBehindOffset(80); // 前面的视图剩下多少
		slidingMenu.setMode(SlidingMenu.RIGHT); // 左滑出不是右滑出
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu_frame); // 设置menu容器
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();
	}
	/**
	 * 
	 * @Title: intiButtonListener 
	 * @Description: 初始化按钮监听 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	**/
	private void intiButtonListener() {
		imageButtonTriggerSlidingMenue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
	}
	/**
	 * 
	 * @Title: initViewPager 
	 * @Description: 初始化ViewPager
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	**/
	private void initViewPager() {
		//设置ViewPage的适配器
		vpContent.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		//设置vPage的监听器
		vpContent.setOnPageChangeListener(new OnPageChangeListener() {
			
	

			@Override
			public void onPageSelected(int position) {
				//设置头部textView变化
	
				setCurrentPage(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
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
	/**
	 * 
	 * @Title: setCurrentPage 
	 * @Description: 设置当前Fragment对应的头部textView
	 * @param @param current    设定文件 
	 * @return void    返回类型 
	 * @throws 
	**/
	private void setCurrentPage(int current) {
		if (current == 0) {
			tvService.setBackgroundResource(R.drawable.title_menu_current);
			tvService.setTextColor(getResources().getColor(R.color.blue));
			tvHistoryOrder.setBackgroundResource(R.drawable.title_menu_bg);
			tvHistoryOrder.setTextColor(getResources().getColor(R.color.grey));
			//slidingMenu不可被左右滑动
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		} else {
			tvHistoryOrder.setBackgroundResource(R.drawable.title_menu_current);
			tvHistoryOrder.setTextColor(getResources().getColor(R.color.blue));
			tvService.setBackgroundResource(R.drawable.title_menu_bg);
			tvService.setTextColor(getResources().getColor(R.color.grey));
			//slidingMenu可被左右滑动
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
	}

}
