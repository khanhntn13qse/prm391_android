package ntnk.sample.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

//        File file = getFileStreamPath("data.txt");
//        writeFile(file);
//        readFile(file);


//        LoadData loadData = new LoadData();
//        loadData.execute();

    }

    public void writeFile(File file){
//        File file = getFileStreamPath("data.txt");
        if(file.exists()){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                String s = "Hello world";
                fileOutputStream.write(s.getBytes());
                fileOutputStream.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void readFile(File file){
//        File file = getFileStreamPath("data.txt");
     if(file.exists()){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[256];
            fileInputStream.read(data);
            fileInputStream.close();

            textView.setText(new String(data));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }

    class LoadData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL("https://vnexpress.net/");
                InputStream inputStream = url.openStream();
                byte[] data = new byte[256];

                while (inputStream.read(data) > 0){
                    String s = new String(data);
                    stringBuilder.append(s);
                }
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){

            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
