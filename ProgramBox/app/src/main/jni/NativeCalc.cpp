#include "NativeCalc.h"
#include "Calc.h"

JNIEXPORT jdouble JNICALL Java_com_example_turingmac_programbox_NativeCalc_calc(JNIEnv *env, jobject instance, jstring str_)
{
    const char *str = env->GetStringUTFChars(str_, 0);

    // TODO

    Calc c;
    c.Input(str);
    c.Cac();
    return c.getAns();

    env->ReleaseStringUTFChars(str_, str);
}