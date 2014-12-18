package com.example.gooutuser;

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
/**
 * 
 * @ClassName: MainLoginActivity 
 * @Description: TODO(登录界面) 
 * @author xushenglin
 * @date 2014-12-17 下午5:01:44   
 */
public class MainLoginActivity extends Activity {
	/**
	 *登录的用户名
	 */
	private String userName=null;
	/**
	 * 登录密码（MD5加密后）
	 */
	private String userPwd=null;
	/**
	 * 用户登录用户名框
	 */
	private EditText editText;
	/**
	 * 用户登录密码框
	 */
	private EditText editPwd;
	//登录控件
	private Button buttonLogin;
	//注册控件
	private Button buttonReg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化窗口
		initWindows();  
		setContentView(R.layout.user_login_main);
		//初始化控件
		initButton();
		//给按钮添加监听
		addButtonListener();
		
	}
	/**
	 * 
	 * @Title: addButtonListener 
	 * @Description:(给按钮添加监听) 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	*
	 */
	private void addButtonListener() {
		//给登录按钮添加监听
		//TODO 实现登录界面
		buttonLogin.setOnClickListener(new OnClickListener() {
			private String tag="buttonLogin";

			@Override
			public void onClick(View v) {
				userName=editText.getText().toString();
				
				userPwd=editPwd.getText().toString();
				Log.v(tag, "用户名："+userName);
				Log.v(tag,"密码："+userPwd);
				
			}
		});	
		//注册按钮的监听
		buttonReg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//跳转到注册界面
				Intent intent=new Intent(MainLoginActivity.this,UserRegister.class);
				startActivity(intent);				
			}
		});
	}
	/**
	 * 
	 * @Title: initWindows 
	 * @Description:(初始化窗口) 
	 * @param     设定文件 
	 * @return void    返回类型 
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
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	private void initButton() {
		String tag="initButton";
		Log.v(tag, "用户初始化控件");
		editText=(EditText) findViewById(R.id.userLoginNameTxt);
		editPwd=(EditText) findViewById(R.id.userPwdTxt);
		buttonLogin=(Button) findViewById(R.id.btLogin);
		buttonReg=(Button) findViewById(R.id.btRegist);
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
}
