package com.logigear.automation.mobile.ios.jni;

public class ScreenshotServiceNativeAccess {
private static ScreenshotServiceNativeAccess m_instance = null;
	
	public static synchronized ScreenshotServiceNativeAccess getInstance() {
		if (m_instance == null){
			m_instance = new ScreenshotServiceNativeAccess();
		}
		
		return m_instance;		
	}
	
	public boolean isLoader = false;
	
	public ScreenshotServiceNativeAccess(){
		isLoader = IOSBridgeLoader.getInstance().isLoaded();
	}
	
	public native synchronized long getScreenshotService(long nDevice);
	
	public native synchronized long getScreenshotClient(long nDevice, long nService);
	
	public native synchronized byte[] takeScreenshot(long nScreenshotClient);
	
	public native synchronized boolean freeScreenshot();
}
