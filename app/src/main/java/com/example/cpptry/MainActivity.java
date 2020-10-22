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
        final Button tv = findViewById(R.id.sample_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Method m = NativeTry.class.getDeclaredMethod("stringFromJNI6");
                    Method m2 = NativeTry.class.getDeclaredMethod("stringFromJNI11");
                    NativeTry nativeTry = new NativeTry();
                    String s1 = NativeTry.javaStringMethod(nativeTry, m, NativeTry.SIGABRT);
                    String s2 = NativeTry.javaStringMethod(nativeTry, m2, NativeTry.SIGSEGV);
                    tv.setText("s=" + s1 + "," + s2);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
