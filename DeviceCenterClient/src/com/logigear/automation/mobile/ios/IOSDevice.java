package com.logigear.automation.mobile.ios;

import com.logigear.automation.mobile.ios.jni.DeviceNativeAccess;

public class IOSDevice {
	private long m_nDevice;
	
	public IOSDevice(String szDeviceId){
		m_nDevice = DeviceNativeAccess.getInstance().getDevice(szDeviceId);
	}
	
	public long get(){
		return m_nDevice;
	}
	
	public boolean free(){
		return DeviceNativeAccess.getInstance().freeDevice(m_nDevice);
	}
}
