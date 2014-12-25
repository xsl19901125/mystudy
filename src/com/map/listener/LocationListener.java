package com.map.listener;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
/**
 * 
 * @ClassName: LocationListener 
 * @Description: 对于定位信息获取的监听器
 * @author xushenglin
 * @date 2014-12-25 下午2:18:57   
*
 */
public class LocationListener implements BDLocationListener {

	@Override
	public void onReceiveLocation(BDLocation location) {
		String Tag="Testlocation";
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("时间:"+location.getTime()+"\n");
		stringBuffer.append("经度"+location.getLatitude()+"\n");
		stringBuffer.append("纬度"+location.getLongitude()+"\n");
		stringBuffer.append("误差"+location.getRadius()+"\n");
		stringBuffer.append("地理描述信息"+location.getAddrStr());
		
		Log.v(Tag, stringBuffer.toString());

	}

}
