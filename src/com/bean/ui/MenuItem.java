package com.bean.ui;
/**
 * 
 * @ClassName: MenuItem 
 * @Description: 主界面服务界面的MenuItem 
 * @author xushenglin
 * @date 2014-12-22 下午4:55:01   
* */
public class MenuItem {
	private int menuIcon;
	private String menuTile;
	private String menuMsg;
	public MenuItem(int menuIcon, String menuTile, String menuMsg) {
		super();
		this.menuIcon = menuIcon;
		this.menuTile = menuTile;
		this.menuMsg = menuMsg;
	}
	public int getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(int menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuTile() {
		return menuTile;
	}
	public void setMenuTile(String menuTile) {
		this.menuTile = menuTile;
	}
	public String getMenuMsg() {
		return menuMsg;
	}
	public void setMenuMsg(String menuMsg) {
		this.menuMsg = menuMsg;
	}

}
