#include "NativeCalc.h"

JNIEXPORT jdouble JNICALL Java_com_example_turingmac_programbox_NativeCalc_calc(JNIEnv *env, jobject instance, jstring str_)
{
    const char *str = env->GetStringUTFChars(str_, 0);

    // TODO

    return 1.0;

    env->ReleaseStringUTFChars(str_, str);
}