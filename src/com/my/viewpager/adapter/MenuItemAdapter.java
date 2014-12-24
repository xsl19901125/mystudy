package com.my.viewpager.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.ui.MenuItem;
import com.example.gooutuser.R;


/**
 * 
 * @ClassName: MenuItemAdapter
 * @Description: ServiceUI�����GridView ����������
 * @author xushenglin
 * @date 2014-12-22 ����5:12:37
 * 
 */
public class MenuItemAdapter extends BaseAdapter {
	private List<MenuItem> menus;
	// ���ڲ���layout����Ĳ����ļ�
	private LayoutInflater inflater;

	private int margin;
	private String tag="TestNull";

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param context
	 *            //������ȡlayout���ֵ�������
	 * @param menus
	 *            //��ʼ����menuItem����
	 * @param margin
	 *            //MenuItem���
	 * 
	 */
	public MenuItemAdapter(Context context, List<MenuItem> menus, int margin) {

		this.menus = menus;
		this.inflater = LayoutInflater.from(context);
		this.margin = margin;
	}
	//�������ж������
	@Override
	public int getCount() {
		return  menus.size();
	}

	@Override
	public Object getItem(int position) {
		return menus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int height = parent.getHeight() / 3 - margin;
		AbsListView.LayoutParams param = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, height);
		ViewHolder holder = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.menu_item, parent, false);
			holder = new ViewHolder();
			holder.ivImageView = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
			holder.tvMenuTitle = (TextView) convertView.findViewById(R.id.tv_menu_title);
			holder.tvMenuMsg= (TextView) convertView.findViewById(R.id.tv_menu_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	/*	convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v(tag, "�������");
				
			}
		});*/
		//�������������� 
		setMenuItemData(position, holder);
		convertView.setLayoutParams(param);
		return convertView;
	}
	/**
	 * 
	 * @Title: setMenuItemData 
	 * @Description: �������������� 
	 * @param @param position
	 * @param @param holder    �趨�ļ� 
	 * @return void    �������� 
	 * @throws 
	*
	 */
	private void setMenuItemData(int position, ViewHolder holder) {
		MenuItem item = menus.get(position);
		holder.ivImageView.setImageResource(item.getMenuIcon());
		holder.tvMenuTitle.setText(item.getMenuTile());
		if (item.getMenuMsg().length() == 0) {
			holder.tvMenuMsg.setVisibility(View.GONE);
		} else {
			holder.tvMenuMsg.setVisibility(View.VISIBLE);
			holder.tvMenuMsg.setText(item.getMenuMsg());
		}
	}
	/**
	 * 
	 * @Title: reUserView 
	 * @Description: ����view�ؼ� 
	 * @param @param convertView
	 * @param @param parent
	 * @param @return    �趨�ļ� 
	 * @return ViewHolder    �������� 
	 * @throws 
	**/
	private ViewHolder reUserView(View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.menu_item, parent, false);
			holder = new ViewHolder();
			holder.ivImageView = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
			holder.tvMenuTitle = (TextView) convertView.findViewById(R.id.tv_menu_title);
			holder.tvMenuMsg= (TextView) convertView.findViewById(R.id.tv_menu_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return holder;
	}
	/**
	 * 
	 * @ClassName: ViewHolder 
	 * @Description: ���������ֿؼ����ڲ��� 
	 * @author xushenglin
	 * @date 2014-12-22 ����5:21:41   
	 */
	private class ViewHolder{
		ImageView ivImageView;
		TextView tvMenuTitle;
		TextView tvMenuMsg;
	}

}
