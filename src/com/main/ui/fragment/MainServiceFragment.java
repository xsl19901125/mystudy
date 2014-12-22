package com.main.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bean.ui.MenuItem;
import com.example.gooutuser.R;
/**
 * 
 * @ClassName: MainServiceFragment 
 * @Description:�ṩ�����Fragment
 * @author xushenglin
 * @date 2014-12-22 ����4:44:50   
* */
public class MainServiceFragment extends Fragment {
	private GridView gvService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_service, container,false);
		
		initView(view);
		List<MenuItem> menus=new ArrayList<MenuItem>();
		menus.add(new MenuItem(R.drawable.menu_leidian, "�����ֻ���Դ", "������ ���� ��ֽ ����"));
		menus.add(new MenuItem(R.drawable.menu_downloaded, "������", "����������"));
		menus.add(new MenuItem(R.drawable.menu_photo, "ͼƬ", ""));
		menus.add(new MenuItem(R.drawable.menu_video, "��Ƶ", ""));
		menus.add(new MenuItem(R.drawable.menu_file, "�ĵ�", ""));
		menus.add(new MenuItem(R.drawable.menu_music, "����", ""));
		// ����margin
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		return view;
	}
	
	private void initView(View view) {
		gvService=(GridView) view.findViewById(R.id.gv_menu);
	}

}
