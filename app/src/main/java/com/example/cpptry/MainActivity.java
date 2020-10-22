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
                    Method m = MainActivity.class.getDeclaredMethod("stringFromJNI6");
                    Method m2 = MainActivity.class.getDeclaredMethod("stringFromJNI11");
                    String s1 = NativeTry.javaStringMethod(MainActivity.this, m, NativeTry.SIGABRT);
                    String s2 = NativeTry.javaStringMethod(MainActivity.this, m2, NativeTry.SIGSEGV);
                    tv.setText("s=" + s1 + "," + s2);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI6();

    public native String stringFromJNI11();
}
