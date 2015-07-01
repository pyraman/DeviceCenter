package com.logigear.automation.mobile.ios.jni;

public class IOSBridgeLoader {
	private boolean bLoaded = false;
	private static final String LIB_DEFAULT = "IOSBridge";

	// instance
	private static class JNILibraryLoaderHolder{
		private static final IOSBridgeLoader INSTANCE = new IOSBridgeLoader(LIB_DEFAULT);
	}

	/**
	 * get a unique function, use as singleton class
	 * 
	 * @return
	 */
	public static IOSBridgeLoader getInstance() {		
		return JNILibraryLoaderHolder.INSTANCE;
	}

	/**
	 * load dynamic link library
	 */
	private void loadLibrary() {
		try {
			System.loadLibrary(LIB_DEFAULT);
			bLoaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * constructor with path library
	 * 
	 * @param lib
	 */
	private IOSBridgeLoader(String lib) {
		loadLibrary();
	}

	/**
	 * check the library loading is successful
	 * 
	 * @return
	 */
	public boolean isLoaded() {
		return bLoaded;
	}
}
