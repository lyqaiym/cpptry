package com.example.cpptry;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class NativeTry {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("cpptry");
    }

    public static String javaStringMethod(Object obj, Method method) {
        String s = stringMethod(obj, method);
        return s;
    }

    native static String stringMethod(Object obj, Method method);
}
