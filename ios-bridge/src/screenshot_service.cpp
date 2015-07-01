//
//  screenshot_service.cpp
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include "screenshot_service.h"

#include <libimobiledevice/libimobiledevice.h>
#include <libimobiledevice/screenshotr.h>

JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_getScreenshotService
(JNIEnv * env, jobject thiz, jlong jndevice)
{
    idevice_t device = (idevice_t) jndevice;
    lockdownd_client_t lckd = NULL;
    lockdownd_service_descriptor_t service = NULL;
    
    if (LOCKDOWN_E_SUCCESS != lockdownd_client_new_with_handshake(device, &lckd, NULL)) {
        idevice_free(device);
        return false;
    }
    
    lockdownd_start_service(lckd, "com.apple.mobile.screenshotr", &service);
    if (service == NULL && service->port <= 0) {
        return false;
    }
    
    return (long)service;
}

JNIEXPORT jlong JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_getScreenshotClient
(JNIEnv * env, jobject thiz, jlong jndevice, jlong jnservice){
    
    screenshotr_client_t shotr = NULL;
    
    idevice_t device = (idevice_t)jndevice;
    lockdownd_service_descriptor_t service = (lockdownd_service_descriptor_t)jnservice;
    
    if (screenshotr_client_new(device, service, &shotr) != SCREENSHOTR_E_SUCCESS) {
        return -1;
    }
    
    return (long) shotr;
}

JNIEXPORT jbyteArray JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_takeScreenshot
(JNIEnv * env, jobject thiz, jlong jnscreenshotclient){
    
    screenshotr_client_t shotr = (screenshotr_client_t)jnscreenshotclient;

    
    char *imgdata = NULL;
    uint64_t imgsize = 0;
    if (screenshotr_take_screenshot(shotr, &imgdata, &imgsize) != SCREENSHOTR_E_SUCCESS) {
        return NULL;
    }
    
    jbyteArray array = env->NewByteArray (imgsize);
    env->SetByteArrayRegion (array, 0, imgsize, reinterpret_cast<jbyte*>(imgdata));
    free(imgdata);
    return array;
}

JNIEXPORT jboolean JNICALL Java_com_logigear_automation_mobile_ios_jni_ScreenshotServiceNativeAccess_freeScreenshot
(JNIEnv *, jobject, jlong){
    return true;
}