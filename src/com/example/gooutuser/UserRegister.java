package com.example.gooutuser;

import java.security.NoSuchAlgorithmException;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.util.MD5Util;

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
		 * 
		 */
		buttonLogin.setOnClickListener(new OnClickListener() {
			
			private String tag="TestLogin";

			@Override
			public void onClick(View v) {
				String userName=editTextUserName.getText().toString().trim();
				String userPwd=editTextUserPass.getText().toString().trim();
				String userPwdConfirm=editTextUserPassConfirm.getText().toString().trim();
				String userPhone=editTextUserRegisterPhone.getText().toString().trim();
				String userPwdUpdate=null;
				if(userName.isEmpty()){
					Toast.makeText(UserRegister.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if(userPwd.isEmpty()||userPwdConfirm.isEmpty()){
					Toast.makeText(UserRegister.this, "密码不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if(userPhone.isEmpty()){
					Toast.makeText(UserRegister.this, "注册手机号不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!userPwd.equals(userPwdConfirm)){
					Toast.makeText(UserRegister.this, "密码不一致", Toast.LENGTH_SHORT).show();
					return;
				}else{
					//讲密码转换成MD5
					try {
						userPwdUpdate=MD5Util.getMD5(userPwd);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						return;
					}
					AVObject userInfoReg=new AVObject("UserInfo");
					userInfoReg.put("userName", userName);
					userInfoReg.put("userPwdMD5", userPwdUpdate);
					userInfoReg.put("userPhone", userPhone);
					userInfoReg.saveInBackground(new SaveCallback() {
						//TODO未来要增加一个用户名唯一的验证
						@Override
						public void done(AVException e) {
							if(e==null){
								
								Log.v(tag, "注册成功");
								//跳转到登录界面
								Intent intent=new Intent(UserRegister.this,MainLoginActivity.class);
								startActivity(intent);
							}else{
								Toast.makeText(UserRegister.this, "注册没成功", Toast.LENGTH_SHORT).show();
								return;
							}
						}
					});
				}
			
				
				Log.v(tag, "登录成功");
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
