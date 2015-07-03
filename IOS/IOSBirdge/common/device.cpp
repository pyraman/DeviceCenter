//
//  device.cpp
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#include <stdio.h>
#include "device.h"
#include "common.h"

#include <libimobiledevice/libimobiledevice.h>
#include <libimobiledevice/lockdown.h>

JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_DeviceNativeAccess_getDevice
(JNIEnv * env, jobject thiz, jstring jdeviceid)
{
    string szDeviceId = convertJStringToString(env, jdeviceid);
    
    idevice_t device = NULL;
    lockdownd_client_t lckd = NULL;
    
    if (IDEVICE_E_SUCCESS != idevice_new(&device, szDeviceId.c_str()))
    {
        return -1;
    }
    
    if (LOCKDOWN_E_SUCCESS != lockdownd_client_new_with_handshake(device, &lckd, NULL)) {
        idevice_free(device);
        return -1;
    }
    
    return long(device);
}

JNIEXPORT jboolean JNICALL Java_com_logigear_automation_mobile_ios_jni_DeviceNativeAccess_freeDevice
(JNIEnv * env, jobject thiz, jlong jndevice){
    idevice_t device = (idevice_t)jndevice;
    if (device == NULL) {
        return false;
    }
    idevice_free(device);
    return true;
}