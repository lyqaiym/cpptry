package com.example.cpptry;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class NativeTry {

    public static String javaStringMethod(Object obj, Method method) {
        String s = stringMethod(obj, method);
        return s;
    }

    native static String stringMethod(Object obj, Method method);
}
