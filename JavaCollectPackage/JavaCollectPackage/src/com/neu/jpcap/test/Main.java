package com.neu.jpcap.test;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class Main {

	public static void main(String[] args) {
		try{
			//获得网卡设备的实例列表 
			NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			int tem = devices.length;
			for(int i=0;i<tem;i++)
			{
				System.out.println(devices[i].description);
			}
			
			int index = 0;
			JpcapCaptor captor = JpcapCaptor.openDevice(devices[0], 65535, false, 20);
			captor.loopPacket(-1, new PackageReceiver()); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
