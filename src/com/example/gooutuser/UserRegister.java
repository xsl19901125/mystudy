package com.example.gooutuser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @ClassName: UserRegister
 * @Description: TODO(用户注册的活动界面)1.给注册添加监听
 * @author xushenglin
 * @date 2014-12-18 上午10:54:21
 **/
public class UserRegister extends Activity {
	// 用户名输入框
	private EditText editTextUserName;
	// 用户密码输入框
	private EditText editTextUserPass;
	// 用户密码确认框
	private EditText editTextUserPassConfirm;
	// 用户注册手机
	private EditText editTextUserRegisterPhone;
	// 确认注册
	private Button buttonLogin;
	// 取消注册
	private Button buttonCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化窗口
		initWindows();

		setContentView(R.layout.activity_user_register);
		// 初始化控件
		initWidget();
	}

	/**
	 * 
	 * @Title: initWidget
	 * @Description: (初始化控件)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void initWidget() {
		editTextUserName = (EditText) findViewById(R.id.userLoginNameTxt);
		editTextUserPass = (EditText) findViewById(R.id.userPwdTxt);
		editTextUserPassConfirm = (EditText) findViewById(R.id.user_pwdconfirmTxt);
		editTextUserRegisterPhone = (EditText) findViewById(R.id.userPhoneTxt);
		buttonLogin = (Button) findViewById(R.id.bt_reg_ok);
		buttonCancel = (Button) findViewById(R.id.bt_reg_cancel);
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			//取消注册，跳回登录页面
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(UserRegister.this,MainLoginActivity.class);
				startActivity(intent);
				
			}
		});
		/**
		 * 给注册添加监听
		 * TODO
		 */
		buttonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_register, menu);
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
	 * @Title: initWindows
	 * @Description: (初始化窗口)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
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
