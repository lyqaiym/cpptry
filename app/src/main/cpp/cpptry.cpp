#include <jni.h>
#include <android/log.h>
#include <bits/signal_types.h>
#include <signal.h>
#include <setjmp.h>
#include <stdlib.h>

#define LOG_TAG "cpptry" // 这个是自定义的LOG的标识
#define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG,  __VA_ARGS__) // 定义LOGD类型

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_cpptry_MainActivity_stringFromJNI6(
        JNIEnv *env,
        jobject obj) {
    abort();
    return env->NewStringUTF("Hello from C++");
}

struct Test {
    int a;
};

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_cpptry_MainActivity_stringFromJNI11(
        JNIEnv *env,
        jobject obj) {
    Test *t = nullptr;
    int m = t->a;
    return env->NewStringUTF("Hello from C++");
}

jmp_buf envdump;

void sighandlerdump(int sig) {
    LOG_DEBUG("sighandlerdump:sig=%d", sig);
    longjmp(envdump, 1);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_cpptry_NativeTry_stringMethod(JNIEnv *env, jclass clazz, jobject obj,
                                               jobject method, int sig) {
    LOG_DEBUG("stringMethod:sig=%d", sig);
    sighandler_t olddump = signal(sig, sighandlerdump);
    int res = setjmp(envdump);
    LOG_DEBUG("stringMethod:res=%d,sig=%d", res, sig);
    if (res == 0) {
        jmethodID jmethodId = env->FromReflectedMethod(method);
        jobject jobject1 = env->CallObjectMethod(obj, jmethodId);
        signal(sig, olddump);
        return (jstring) jobject1;
    }
    signal(sig, olddump);
    return env->NewStringUTF("cache");
}