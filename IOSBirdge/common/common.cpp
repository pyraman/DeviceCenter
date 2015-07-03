//
//  common.cpp
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#include <stdio.h>
#include "common.h"

/*
 * Method:    convertJStringToString
 * Signature: (J)I
 * Used to convert a jstring object to a string object.
 */
string convertJStringToString(JNIEnv *env, jstring jstr){
    const char *temp = env->GetStringUTFChars(jstr, 0);
    string str(temp);
    env->ReleaseStringUTFChars(jstr, temp);
    return str;
}