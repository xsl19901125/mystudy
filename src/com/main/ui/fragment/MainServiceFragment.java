package com.main.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.bean.ui.MenuItem;
import com.example.gooutuser.R;
import com.my.viewpager.adapter.MenuItemAdapter;
import com.ui.LocationUI;
/**
 * 
 * @ClassName: MainServiceFragment 
 * @Description:�ṩ�����Fragment
 * @author xushenglin
 * @date 2014-12-22 ����4:44:50 
 * С�᣺�Բ��ֿؼ��������ݲ��裺1.�����ؼ���eg��gridView��;2.��ʼ���ؼ���eg:findViewById��;3.��������������4.�����������ؼ���������ֵ  
* */
public class MainServiceFragment extends Fragment {
	private GridView gvService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_service, container,false);
		//��ʼ���ؼ�
		initView(view);
		
		
		//����gridView������������
		setGridViewData();
		return view;
	}
	/**
	 * 
	 * @Title: setGridViewData 
	 * @Description: ����gridView������������ 
	 * @param     �趨�ļ� 
	 * @return void    �������� 
	 * @throws 
	*
	 */
	private void setGridViewData() {
		//��List������������
		List<MenuItem> menus = setMenuItemData();
		// ����margin
		int margin = calMargin();
		//����������
		MenuItemAdapter menuItemAdapter=new MenuItemAdapter(getActivity(), menus, margin);
		//��gridView����������
		gvService.setAdapter(menuItemAdapter);
	}
	/**
	 * 
	 * @Title: calMargin 
	 * @Description: ����ؼ����
	 * @param @return   ��϶��С
	 * @return int    �������� 
	 * @throws 
	**/
	private int calMargin() {
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		return margin;
	}
	/**
	 * 
	 * @Title: setMenuItemData 
	 * @Description: ���������MenuItem������������
	 * @param @return    ��������������Ŀbean����
	 * @return List<MenuItem>    �������� 
	 * @throws 
	**/
	private List<MenuItem> setMenuItemData() {
		List<MenuItem> menus=new ArrayList<MenuItem>();
		menus.add(new MenuItem(R.drawable.service_call_car_out, "�����ó�", "�ṩ�����⳵����"));
		menus.add(new MenuItem(R.drawable.service_search_parking, "��λԤ��", "�ṩ��λԤ������"));
		menus.add(new MenuItem(R.drawable.menu_photo, "ͼƬ", ""));
		menus.add(new MenuItem(R.drawable.menu_video, "��Ƶ", ""));
		menus.add(new MenuItem(R.drawable.menu_file, "�ĵ�", ""));
		menus.add(new MenuItem(R.drawable.menu_music, "����", ""));
		return menus;
	}
	
	private void initView(View view) {
		gvService=(GridView) view.findViewById(R.id.gv_menu);
		
		
		
		gvService.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String Tag="TestOnclick";
				Log.v(Tag, "View:"+view);
				Log.v(Tag, "position:"+position);
				Log.v(Tag, "id:"+id);
				/*if(position==0){
					Log.v(Tag, "�򿪶���ҳ��");
				}*/
				switch (position) {
				case 0:
					Intent intent=new Intent(getActivity(),LocationUI.class);
					startActivity(intent);
					
					break;
				case 1:
					Log.v(Tag, "����λ����");
					break;

				default:
					Log.v(Tag, "��������");
					break;
				}
				
			}
		});
		
	}

}
