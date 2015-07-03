//
//  device.h
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#ifndef ios_bridge_device_h
#define ios_bridge_device_h

#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif
    
/* method declaration */
JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_DeviceNativeAccess_getDevice
(JNIEnv *, jobject, jstring);
    
JNIEXPORT jboolean JNICALL Java_com_logigear_automation_mobile_ios_jni_DeviceNativeAccess_freeDevice
(JNIEnv *, jobject, jlong);
    
#ifdef __cplusplus
}
#endif

#endif
