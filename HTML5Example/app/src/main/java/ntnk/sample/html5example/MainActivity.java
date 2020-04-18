package ntnk.sample.html5example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use webview
        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/html/welcome.html");

        AssetManager assetManager = getAssets();
        try {
            assetManager.open("html/welcome.html");
        }catch (Exception e){

        }
    }
}
