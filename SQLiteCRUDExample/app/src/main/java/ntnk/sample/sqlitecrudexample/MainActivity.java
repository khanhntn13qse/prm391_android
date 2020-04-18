package ntnk.sample.sqlitecrudexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;

    private static final int MY_REQUEST_CODE = 1000;

    private final List<TaskItem> taskList = new ArrayList<>();
    private ArrayAdapter<TaskItem> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationListView();
    }

    private void initializationListView(){
        this.listView = findViewById(R.id.listView);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        List<TaskItem> taskListFromDB = dbHandler.getAllTaskItem();
        this.taskList.addAll(taskListFromDB);

        this.listViewAdapter = new ArrayAdapter<TaskItem>(this,  android.R.layout.simple_list_item_1, android.R.id.text1, this.taskList);
        this.listView.setAdapter(this.listViewAdapter);

        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("SELECT ACTION");
        menu.add(v.getId(), MENU_ITEM_VIEW, 0, "View Task");
        menu.add(v.getId(), MENU_ITEM_CREATE, 1, "Create Task");
        menu.add(v.getId(), MENU_ITEM_EDIT, 2, "Edit Task");
        menu.add(v.getId(), MENU_ITEM_DELETE, 3, "Delete Task");
//        Toast.makeText(getApplicationContext(), "onCreateContextMenu end", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final TaskItem seletedTask = (TaskItem) this.listView.getItemAtPosition(info.position);
        if(item.getItemId() == MENU_ITEM_VIEW){

            //user Toast t show infor
//            Toast.makeText(getApplicationContext(), seletedTask.getTitle(), Toast.LENGTH_LONG).show();
            //user Dialog to show infor
            String taskInfo = seletedTask.getTitle() + "\n" + seletedTask.getNote() + "\n" + seletedTask.getDate() + "\n" + (seletedTask.getDome()? "Done" : "Not done");
            new AlertDialog.Builder(this)
                    .setTitle("Task infor")
                    .setMessage(taskInfo)
                    .setCancelable(true)
                    .setPositiveButton("Ok", null)
                    .show();
        }else if(item.getItemId() == MENU_ITEM_CREATE){
            Intent intent =new Intent(this, AddEditTaskItemActivity.class);
            this.startActivityForResult(intent, MY_REQUEST_CODE);

        }else if(item.getItemId() == MENU_ITEM_EDIT){
            Intent intent =new Intent(this, AddEditTaskItemActivity.class);
            intent.putExtra("taskItem", seletedTask);
            this.startActivityForResult(intent, MY_REQUEST_CODE);

        }else if(item.getItemId() == MENU_ITEM_DELETE){
            //ask for delete
            new AlertDialog.Builder(this)
                    .setMessage(seletedTask.getTitle() + ". Are you sure to delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteTaskItem(seletedTask);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }else{
            return false;
        }
        return true;
    }

    private void deleteTaskItem(TaskItem taskItem){
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteTaskItem(taskItem.getId());
        this.taskList.remove(taskItem);
        //refresh listview
        this.listViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE){
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            //refresh list view
            if(needRefresh){
                this.taskList.clear();
                DatabaseHandler db = new DatabaseHandler(this);
                List<TaskItem> list = db.getAllTaskItem();
                this.taskList.addAll(list);
                this.listViewAdapter.notifyDataSetChanged();
            }

        }
    }

    public void addButtonClicked(View view){
        Intent intent =new Intent(this, AddEditTaskItemActivity.class);
        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }
}
