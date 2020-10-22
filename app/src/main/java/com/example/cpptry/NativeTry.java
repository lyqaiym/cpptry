package com.example.cpptry;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class NativeTry {

    public static int SIGABRT = 6;
    public static int SIGSEGV = 11;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("cpptry");
    }

    public static String javaStringMethod(Object obj, Method method, int signal) {
        String s = stringMethod(obj, method, signal);
        return s;
    }

    native static String stringMethod(Object obj, Method method, int signal);
}
