package com.example.gooutuser;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.bean.User;
import com.ui.MainUI;
import com.util.MD5Util;

/**
 * 
 * @ClassName: MainLoginActivity
 * @Description: (登录界面登录成功后界面跳转)
 * @author xushenglin
 * @date 2014-12-17 下午5:01:44
 */
public class MainLoginActivity extends Activity {
	/**
	 * 登录的用户名
	 */
	private String userName = null;
	/**
	 * 登录密码（MD5加密后）
	 */
	private String userPwd = null;
	/**
	 * 用户登录用户名框
	 */
	private EditText editText;
	/**
	 * 用户登录密码框
	 */
	private EditText editPwd;
	// 登录控件
	private Button buttonLogin;
	// 注册控件
	private Button buttonReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化窗口
		initWindows();
		setContentView(R.layout.user_login_main);
		// 初始化控件
		initButton();
		// 给按钮添加监听
		addButtonListener();

	}

	/**
	 * 
	 * @Title: addButtonListener
	 * @Description:(给按钮添加监听)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void addButtonListener() {
		// 给登录按钮添加监听
		buttonLogin.setOnClickListener(new OnClickListener() {
			private String tag = "buttonLogin";

			@Override
			public void onClick(View v) {

				userName = editText.getText().toString().trim();

				userPwd = editPwd.getText().toString().trim();
				if (userName.isEmpty() || userPwd.isEmpty()) {
					Toast.makeText(MainLoginActivity.this, "用户名或者密码不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 将用户密码用MD5加密
				try {
					userPwd = MD5Util.getMD5(userPwd);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				User user = new User();
				user.setUserName(userName);
				user.setUserPwdMD5(userPwd);
				// 登录验证
				LoginQuery(user);

			}

			/**
			 * 
			 * @Title: LoginQuery 
			 * @Description: 用户登录验证
			 * @param @param user    验证用户bean 
			 * @return void    返回类型 
			 * @throws 
			*
			 */
			private void LoginQuery(User user) {
				AVQuery<AVObject> query = new AVQuery<AVObject>("UserInfo");
				query.whereEqualTo("userName", user.getUserName());
				query.whereEqualTo("userPwdMD5", user.getUserPwdMD5());
				query.findInBackground(new FindCallback<AVObject>() {
					
					@Override
					public void done(List<AVObject> list, AVException e) {
						if (list.size() != 0) {
							// TODO（最好能记录用户登录信息）
							Intent intent =new Intent(MainLoginActivity.this,MainUI.class);
							startActivity(intent);
							Log.v(tag, "登陆成功");
						} else {
							editText.setText("");
							editPwd.setText("");
						}
					}
				});
			}
		});
		// 注册按钮的监听
		buttonReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到注册界面
				Intent intent = new Intent(MainLoginActivity.this,
						UserRegister.class);
				startActivity(intent);
			}
		});
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
	 * @Title: initButton
	 * @Description:(初始化，用户登录框，密码框，登录，注册的按钮)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void initButton() {
		String tag = "initButton";
		Log.v(tag, "用户初始化控件");
		editText = (EditText) findViewById(R.id.userLoginNameTxt);
		editPwd = (EditText) findViewById(R.id.userPwdTxt);
		buttonLogin = (Button) findViewById(R.id.btLogin);
		buttonReg = (Button) findViewById(R.id.btRegist);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	protected void onStop() {
		super.onStop();
		MainLoginActivity.this.finish();
	}
}
