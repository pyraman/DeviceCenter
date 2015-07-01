package com.logigear.automation.mobile.ios.jni;

public class DeviceNativeAccess {
	private static DeviceNativeAccess m_instance = null;
	
	public static synchronized DeviceNativeAccess getInstance() {
		if (m_instance == null){
			m_instance = new DeviceNativeAccess();
		}
		
		return m_instance;		
	}
	
	public boolean isLoader = false;
	
	public DeviceNativeAccess(){
		isLoader = IOSBridgeLoader.getInstance().isLoaded();
	}
	
	public native synchronized long getDevice(String szDeviceId);
	
	public native synchronized boolean freeDevice(long nDevice);
}
