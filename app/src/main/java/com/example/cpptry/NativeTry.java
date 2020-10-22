package com.example.cpptry;

import java.lang.reflect.Method;

public class NativeTry {

    public static int SIGABRT = 6;
    public static int SIGSEGV = 11;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("cpptry");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI6();

    public native String stringFromJNI11();

    public static String javaStringMethod(Object obj, Method method, int signal) {
        String s = stringMethod(obj, method, signal);
        return s;
    }

    native static String stringMethod(Object obj, Method method, int signal);
}
