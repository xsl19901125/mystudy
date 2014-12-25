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
 * @Description:提供服务的Fragment
 * @author xushenglin
 * @date 2014-12-22 下午4:44:50 
 * 小结：对布局控件设置数据步骤：1.创建控件（eg：gridView）;2.初始化控件（eg:findViewById）;3.创建其适配器；4.用适配器往控件里面设置值  
* */
public class MainServiceFragment extends Fragment {
	private GridView gvService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_service, container,false);
		//初始化控件
		initView(view);
		
		
		//配置gridView适配器和数据
		setGridViewData();
		return view;
	}
	/**
	 * 
	 * @Title: setGridViewData 
	 * @Description: 配置gridView适配器和数据 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws 
	*
	 */
	private void setGridViewData() {
		//往List里面设置数据
		List<MenuItem> menus = setMenuItemData();
		// 计算margin
		int margin = calMargin();
		//创建适配器
		MenuItemAdapter menuItemAdapter=new MenuItemAdapter(getActivity(), menus, margin);
		//给gridView设置适配器
		gvService.setAdapter(menuItemAdapter);
	}
	/**
	 * 
	 * @Title: calMargin 
	 * @Description: 计算控件间隔
	 * @param @return   间隙大小
	 * @return int    返回类型 
	 * @throws 
	**/
	private int calMargin() {
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		return margin;
	}
	/**
	 * 
	 * @Title: setMenuItemData 
	 * @Description: 给主界面的MenuItem里面设置数据
	 * @param @return    主界面服务控制项目bean队列
	 * @return List<MenuItem>    返回类型 
	 * @throws 
	**/
	private List<MenuItem> setMenuItemData() {
		List<MenuItem> menus=new ArrayList<MenuItem>();
		menus.add(new MenuItem(R.drawable.service_call_car_out, "呼叫用车", "提供出行租车服务"));
		menus.add(new MenuItem(R.drawable.service_search_parking, "车位预定", "提供车位预定服务"));
		menus.add(new MenuItem(R.drawable.menu_photo, "图片", ""));
		menus.add(new MenuItem(R.drawable.menu_video, "视频", ""));
		menus.add(new MenuItem(R.drawable.menu_file, "文档", ""));
		menus.add(new MenuItem(R.drawable.menu_music, "音乐", ""));
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
					Log.v(Tag, "打开定车页面");
				}*/
				switch (position) {
				case 0:
					Intent intent=new Intent(getActivity(),LocationUI.class);
					startActivity(intent);
					
					break;
				case 1:
					Log.v(Tag, "定车位界面");
					break;

				default:
					Log.v(Tag, "其他界面");
					break;
				}
				
			}
		});
		
	}

}
