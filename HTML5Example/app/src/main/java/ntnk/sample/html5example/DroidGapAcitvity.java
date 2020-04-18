package ntnk.sample.html5example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.apache.cordova.DroidGap;

public class DroidGapAcitvity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid_gap_acitvity);


        loadUrl("file:///android_asset/html/welcome.html");
    }
}
