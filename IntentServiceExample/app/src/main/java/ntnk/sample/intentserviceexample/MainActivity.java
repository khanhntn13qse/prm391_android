package ntnk.sample.intentserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView textView1 ;
    private static TextView textView2;
    private static TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
    }

    public void buttonAction(View view){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void startBtnAction(View view){
        Intent intent = new Intent(this, ColorService.class);
        startService(intent);
    }

    public void stopBtnAction(View view){
        Intent intent = new Intent(this, ColorService.class);
        stopService(intent);
    }

    public static void changeColor(int color){
        textView1.setTextColor(color);
        textView2.setTextColor(color);
        textView3.setTextColor(color);
    }
}
