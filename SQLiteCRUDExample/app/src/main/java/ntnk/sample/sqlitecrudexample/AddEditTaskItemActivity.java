package ntnk.sample.sqlitecrudexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddEditTaskItemActivity extends AppCompatActivity {

    TaskItem taskItem;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private int mode;
    private EditText txtTitle;
    private EditText txtNote;
    private EditText txtDate;
    private CheckBox cbIsDone;

    private boolean needRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        assignComponent();

        Intent intent = this.getIntent();
        this.taskItem = (TaskItem) intent.getSerializableExtra("taskItem");
        if(this.taskItem == null){
            this.mode = MODE_CREATE;
        }else {
            this.mode = MODE_EDIT;
            this.txtTitle.setText(taskItem.getTitle());
            this.txtNote.setText(taskItem.getNote());
            this.txtDate.setText(taskItem.getDate().toString());
            this.cbIsDone.setChecked(taskItem.getDome());
        }
    }

    private void assignComponent(){
        this.txtTitle = findViewById(R.id.taskItem_title);
        this.txtNote = findViewById(R.id.taskItem_note);
        this.txtDate = findViewById(R.id.taskItem_date);
        this.cbIsDone = findViewById(R.id.taskItem_isDone);
    }

    public void buttonSaveClicked(View view){
        DatabaseHandler db = new DatabaseHandler(this);

        String title = this.txtTitle.getText().toString().trim();
        String note = this.txtNote.getText().toString().trim();
//        String date = this.txtDate.getText().toString();
        Date date = new java.util.Date();
        boolean isDone = this.cbIsDone.isChecked();

        if(title.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode == MODE_CREATE){
            TaskItem newTaskItem = new TaskItem(title, note, date, isDone);
            db.addTask(newTaskItem);
            Toast.makeText(getApplicationContext(), "new task: " + newTaskItem.getTitle() + newTaskItem.getNote() + newTaskItem.getDate() + newTaskItem.getDome(), Toast.LENGTH_LONG).show();
        }else{
            this.taskItem.setTitle(title);
            this.taskItem.setNote(note);
            this.taskItem.setDate(date);
            this.taskItem.setDome(isDone);
            Toast.makeText(getApplicationContext(), "new task: " + taskItem.getTitle() + taskItem.getNote() + taskItem.getDate() + taskItem.getDome(), Toast.LENGTH_LONG).show();
            db.updateTaskItem(this.taskItem);

        }
        this.needRefresh = true;
        //back to main activity

        this.onBackPressed();
    }

    public void  buttonCancelClicked(View view){
        this.onBackPressed();
    }

    @Override
    public void finish() {

        //prepare Intent date
        Intent intent = new Intent();
        //require MainActivity refresh ListView
        intent.putExtra("needRefresh", this.needRefresh);

        //return data
        this.setResult(Activity.RESULT_OK, intent);
        super.finish();

    }
}
