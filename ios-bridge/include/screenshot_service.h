//
//  screenshot_service.h
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#ifndef ios_bridge_screenshot_service_h
#define ios_bridge_screenshot_service_h

#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif

/* method declaration */
JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_getScreenshotService
    (JNIEnv *, jobject, jlong);
    
JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_getScreenshotClient
    (JNIEnv *, jobject, jlong, jlong);
    
JNIEXPORT jbyteArray JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_takeScreenshot
(JNIEnv *, jobject, jlong);
    
JNIEXPORT jboolean JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_freeScreenshot
(JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
