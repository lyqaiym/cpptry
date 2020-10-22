package com.example.cpptry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        final Button bt_testStr = findViewById(R.id.bt_testStr);
        bt_testStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method m = NativeTry.class.getDeclaredMethod("stringFromJNI6");
                    Method m2 = NativeTry.class.getDeclaredMethod("stringFromJNI11");
                    NativeTry nativeTry = new NativeTry();
                    String s1 = NativeTry.javaStringMethod(nativeTry, m, NativeTry.SIGABRT);
                    String s2 = NativeTry.javaStringMethod(nativeTry, m2, NativeTry.SIGSEGV);
                    bt_testStr.setText("s=" + s1 + "," + s2);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
        final Button bt_testInt = findViewById(R.id.bt_testInt);
        bt_testInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method m3 = NativeTry.class.getDeclaredMethod("intFromJNI6");
                    NativeTry nativeTry = new NativeTry();
                    int a1 = NativeTry.javaIntMethod(nativeTry, m3, -1, NativeTry.SIGABRT);
                    bt_testInt.setText("a1=" + a1);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
        final Button bt_testVoid = findViewById(R.id.bt_testVoid);
        bt_testVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method m4 = NativeTry.class.getDeclaredMethod("voidFromJNI6");
                    NativeTry nativeTry = new NativeTry();
                    NativeTry.javaVoidMethod(nativeTry, m4, NativeTry.SIGABRT);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
