//
//  common.h
//  ios-bridge
//
//  Created by Quan.Hoang on 6/30/15.
//  Copyright (c) 2015 Logigear. All rights reserved.
//

#ifndef ios_bridge_common_h
#define ios_bridge_common_h

#include <jni.h>
#include <string>
using namespace std;

/*
 * Method:    convertJStringToString
 * Signature: (J)I
 * Used to convert a jstring object to a string object.
 */
string convertJStringToString(JNIEnv *env, jstring jstr);

#endif
