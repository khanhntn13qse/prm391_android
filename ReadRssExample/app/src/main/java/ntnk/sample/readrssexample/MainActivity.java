package ntnk.sample.readrssexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private List<RssItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<RssItem>();
    }

    class ReadRss extends AsyncTask<String, Void, String>{
        private StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://vnexpress.net/rss/thoi-su.rss");
                InputStream inputStream = url.openStream();
                byte[] data = new byte[512];
                while (inputStream.read(data) > 0){
                    stringBuilder.append(new String(data));
                }
            }catch (MalformedURLException e){

            }catch (IOException e){

            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ItemHandler handler = new ItemHandler();
            parser.parse(s, handler);
            RssItem =


        }
    }
}
