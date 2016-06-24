#include "NativeCalc.h"

JNIEXPORT jdouble JNICALL Java_com_example_turingmac_programbox_NativeCalc_calc(JNIEnv *env, jobject instance, jstring str_)
{
    const char *str = (*env)->GetStringUTFChars(env, str_, 0);

    // TODO

    return 0.1;

    (*env)->ReleaseStringUTFChars(env, str_, str);
}