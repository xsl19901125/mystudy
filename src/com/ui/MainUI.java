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
 * @Description: �������UI
 * @author xushenglin
 * @date 2014��12��21�� ����8:41:46 
 *  
*
 */
public class MainUI extends FragmentActivity {
	//�ڲ���ViewPager
	private ViewPager vpContent;
	/**
	 * ͷ��ѡ��ı���
	 */
	//��Ҫ�ķ���
	private TextView tvService;
	//��ʷ����
	private TextView tvHistoryOrder;
	//SlidingMenue�ؼ�(slidingMenu����android 4v������Ҫ�ѵ�ǰ��Ŀ����İ�ɾ��)
	private SlidingMenu slidingMenu;
	//չ��SlidingMeue�Ŀؼ�
	private ImageButton imageButtonTriggerSlidingMenue;
	
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWindows();
		setContentView(R.layout.activity_main_ui);
		initView();
		//��ʼ��ViewPager
		initViewPager();
		//��ʼ��sliding��ť����
		intiButtonListener();
		//��ʼ�����û�������
		initSlidingMenue();
	}
	private void initSlidingMenue() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE); // ������ʽ
		slidingMenu.setShadowDrawable(R.drawable.shadow_right); // ��Ӱ
		slidingMenu.setShadowWidth(30); // ��Ӱ���
		slidingMenu.setBehindOffset(80); // ǰ�����ͼʣ�¶���
		slidingMenu.setMode(SlidingMenu.RIGHT); // �󻬳������һ���
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu_frame); // ����menu����
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();
	}
	/**
	 * 
	 * @Title: intiButtonListener 
	 * @Description: ��ʼ����ť���� 
	 * @param     �趨�ļ� 
	 * @return void    �������� 
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
	 * @Description: ��ʼ��ViewPager
	 * @param     �趨�ļ� 
	 * @return void    �������� 
	 * @throws 
	**/
	private void initViewPager() {
		//����ViewPage��������
		vpContent.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		//����vPage�ļ�����
		vpContent.setOnPageChangeListener(new OnPageChangeListener() {
			
	

			@Override
			public void onPageSelected(int position) {
				//����ͷ��textView�仯
	
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
	 * @Description: ��ʼ������ؼ�
	 * @param     �趨�ļ� 
	 * @return void    �������� 
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
	/**
	 * 
	 * @Title: setCurrentPage 
	 * @Description: ���õ�ǰFragment��Ӧ��ͷ��textView
	 * @param @param current    �趨�ļ� 
	 * @return void    �������� 
	 * @throws 
	**/
	private void setCurrentPage(int current) {
		if (current == 0) {
			tvService.setBackgroundResource(R.drawable.title_menu_current);
			tvService.setTextColor(getResources().getColor(R.color.blue));
			tvHistoryOrder.setBackgroundResource(R.drawable.title_menu_bg);
			tvHistoryOrder.setTextColor(getResources().getColor(R.color.grey));
			//slidingMenu���ɱ����һ���
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		} else {
			tvHistoryOrder.setBackgroundResource(R.drawable.title_menu_current);
			tvHistoryOrder.setTextColor(getResources().getColor(R.color.blue));
			tvService.setBackgroundResource(R.drawable.title_menu_bg);
			tvService.setTextColor(getResources().getColor(R.color.grey));
			//slidingMenu�ɱ����һ���
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
	}

}
