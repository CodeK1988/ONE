//
// Created by swplzj on 17/7/10.
//

#include "com_one_mvp_ui_maintab_fragment_ReadingFragment.h"

JNIEXPORT jstring JNICALL Java_com_one_mvp_ui_maintab_fragment_ReadingFragment_SayHello
        (JNIEnv *env, jobject obj) {
    char *str = "String from native C";
    return (*env)->NewStringUTF(env, str);
}