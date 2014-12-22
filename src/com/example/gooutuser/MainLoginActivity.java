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
 * @Description: (��¼�����¼�ɹ��������ת)
 * @author xushenglin
 * @date 2014-12-17 ����5:01:44
 */
public class MainLoginActivity extends Activity {
	/**
	 * ��¼���û���
	 */
	private String userName = null;
	/**
	 * ��¼���루MD5���ܺ�
	 */
	private String userPwd = null;
	/**
	 * �û���¼�û�����
	 */
	private EditText editText;
	/**
	 * �û���¼�����
	 */
	private EditText editPwd;
	// ��¼�ؼ�
	private Button buttonLogin;
	// ע��ؼ�
	private Button buttonReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������
		initWindows();
		setContentView(R.layout.user_login_main);
		// ��ʼ���ؼ�
		initButton();
		// ����ť��Ӽ���
		addButtonListener();

	}

	/**
	 * 
	 * @Title: addButtonListener
	 * @Description:(����ť��Ӽ���)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 * 
	 */
	private void addButtonListener() {
		// ����¼��ť��Ӽ���
		buttonLogin.setOnClickListener(new OnClickListener() {
			private String tag = "buttonLogin";

			@Override
			public void onClick(View v) {

				userName = editText.getText().toString().trim();

				userPwd = editPwd.getText().toString().trim();
				if (userName.isEmpty() || userPwd.isEmpty()) {
					Toast.makeText(MainLoginActivity.this, "�û����������벻��Ϊ��",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// ���û�������MD5����
				try {
					userPwd = MD5Util.getMD5(userPwd);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				User user = new User();
				user.setUserName(userName);
				user.setUserPwdMD5(userPwd);
				// ��¼��֤
				LoginQuery(user);

			}

			/**
			 * 
			 * @Title: LoginQuery 
			 * @Description: �û���¼��֤
			 * @param @param user    ��֤�û�bean 
			 * @return void    �������� 
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
							// TODO������ܼ�¼�û���¼��Ϣ��
							Intent intent =new Intent(MainLoginActivity.this,MainUI.class);
							startActivity(intent);
							Log.v(tag, "��½�ɹ�");
						} else {
							editText.setText("");
							editPwd.setText("");
						}
					}
				});
			}
		});
		// ע�ᰴť�ļ���
		buttonReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ת��ע�����
				Intent intent = new Intent(MainLoginActivity.this,
						UserRegister.class);
				startActivity(intent);
			}
		});
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
	 * @Title: initButton
	 * @Description:(��ʼ�����û���¼������򣬵�¼��ע��İ�ť)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	private void initButton() {
		String tag = "initButton";
		Log.v(tag, "�û���ʼ���ؼ�");
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
