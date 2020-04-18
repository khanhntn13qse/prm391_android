package ntnk.sample.menualertexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = findViewById(R.id.imageView);
        this.listView = findViewById(R.id.listView);

        String[] items = {"Item1", "Item2", "Item3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        registerForContextMenu(imageView);
        registerForContextMenu(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu, menu);
//        menu.add("New");
//        menu.add("Edit");
//        menu.add("Delete");

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_item1){

        }
        switch (item.getItemId()){
            case R.id.menu_item1:{
                Toast.makeText(this, "Selected New item", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menu_item2: {
                Toast.makeText(this, "Selected Edit item", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menu_item3:{
                Toast.makeText(this, "Selected Delete item", Toast.LENGTH_LONG).show();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.imageView:{
                getMenuInflater().inflate(R.menu.context_menu, menu);
                break;
            }
            case R.id.listView:{
                getMenuInflater().inflate(R.menu.example_menu, menu);
                break;
            }
            default: break;

        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        return super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.menuitem_zoomIn:{
                imageView.setScaleX(2);
                imageView.setScaleY(2);
                return true;
            }
            case R.id.menu_item_zoomOut:{
                imageView.setScaleX(0.5F);
                imageView.setScaleY(0.5F);
                return true;
            }
            default: return false;

        }
    }
}
