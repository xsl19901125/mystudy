package com.map.listener;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
/**
 * 
 * @ClassName: LocationListener 
 * @Description: ���ڶ�λ��Ϣ��ȡ�ļ�����
 * @author xushenglin
 * @date 2014-12-25 ����2:18:57   
*
 */
public class LocationListener implements BDLocationListener {

	@Override
	public void onReceiveLocation(BDLocation location) {
		String Tag="Testlocation";
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("ʱ��:"+location.getTime()+"\n");
		stringBuffer.append("����"+location.getLatitude()+"\n");
		stringBuffer.append("γ��"+location.getLongitude()+"\n");
		stringBuffer.append("���"+location.getRadius()+"\n");
		stringBuffer.append("����������Ϣ"+location.getAddrStr());
		
		Log.v(Tag, stringBuffer.toString());

	}

}
