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
 * @Description: TODO(�û�ע��Ļ����)1.��ע����Ӽ���
 * @author xushenglin
 * @date 2014-12-18 ����10:54:21
 **/
public class UserRegister extends Activity {
	// �û��������
	private EditText editTextUserName;
	// �û����������
	private EditText editTextUserPass;
	// �û�����ȷ�Ͽ�
	private EditText editTextUserPassConfirm;
	// �û�ע���ֻ�
	private EditText editTextUserRegisterPhone;
	// ȷ��ע��
	private Button buttonLogin;
	// ȡ��ע��
	private Button buttonCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������
		initWindows();

		setContentView(R.layout.activity_user_register);
		// ��ʼ���ؼ�
		initWidget();
	}

	/**
	 * 
	 * @Title: initWidget
	 * @Description: (��ʼ���ؼ�)
	 * @param �趨�ļ�
	 * @return void ��������
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
			//ȡ��ע�ᣬ���ص�¼ҳ��
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(UserRegister.this,MainLoginActivity.class);
				startActivity(intent);
				
			}
		});
		/**
		 * ��ע����Ӽ���
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
	 * @Description: (��ʼ������)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
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
