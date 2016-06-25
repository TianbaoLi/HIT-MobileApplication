//
// Created by TuringMac on 2016/6/25.
//

#ifndef PROGRAMBOX_NATIVECALC_H
#define PROGRAMBOX_NATIVECALC_H

#include <jni.h>
#include <stdio.h>
#include <string.h>

extern "C"
{
    JNIEXPORT jdouble JNICALL Java_com_example_turingmac_programbox_NativeCalc_calc(JNIEnv *env, jobject instance, jstring str_);
}


#endif //PROGRAMBOX_NATIVECALC_H
