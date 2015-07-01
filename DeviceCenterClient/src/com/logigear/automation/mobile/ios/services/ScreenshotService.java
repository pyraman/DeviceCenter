package com.logigear.automation.mobile.ios.services;

import com.logigear.automation.mobile.ios.IOSDevice;
import com.logigear.automation.mobile.ios.jni.ScreenshotServiceNativeAccess;

public class ScreenshotService {
	
	private long m_nService;
	private long m_nDevice;
	private long m_nScreenshotClient;
	
	public ScreenshotService(IOSDevice device){
		m_nDevice = device.get();
		m_nService = ScreenshotServiceNativeAccess.getInstance().getScreenshotService(m_nDevice);
		m_nScreenshotClient = ScreenshotServiceNativeAccess.getInstance().getScreenshotClient(m_nDevice, m_nService);
	}
	
	public byte[] takeScreenshot(){
		byte[] data = ScreenshotServiceNativeAccess.getInstance().takeScreenshot(m_nScreenshotClient);
		return data;
	}
}

