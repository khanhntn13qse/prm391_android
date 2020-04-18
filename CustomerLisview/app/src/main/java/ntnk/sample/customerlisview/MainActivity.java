package ntnk.sample.customerlisview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = new ArrayList<>();
        personList.add(new Person("Nguyen Van A", "anvhe130001@gmail.com", "0965000111"));
        personList.add(new Person("Nguyen Van B", "bnvhe130001@gmail.com", "0965000111"));
        personList.add(new Person("Nguyen Van C", "cnvhe130001@gmail.com", "0965000111"));
        personList.add(new Person("Nguyen Van D", "dnvhe130001@gmail.com", "0965000111"));
        personList.add(new Person("Nguyen Van E", "envhe130001@gmail.com", "0965000111"));

        PersonAdapter personAdapter = new PersonAdapter(personList, this, R.layout.person_layout);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(personAdapter);
    }
}
