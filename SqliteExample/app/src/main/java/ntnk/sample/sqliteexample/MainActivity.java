package ntnk.sample.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPersonId;
    private EditText editTextName;
    private EditText editTextSalary;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private TextView textView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonId = findViewById(R.id.editTextPersonId);
        editTextName = findViewById(R.id.editTextName);
        editTextSalary = findViewById(R.id.editTextSalary);

        textView = findViewById(R.id.textView2);
        spinner = findViewById(R.id.spinner);

        dbHelper = new DBHelper(this, "humanresource", 1);
//        db = dbHelper.getReadableDatabase();
        loadSpinner();
    }

    public void loadSpinner(){
        ArrayList<Department> departments = getAllDepartment();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public ArrayList<Department> getAllDepartment(){
        ArrayList<Department> departments = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Department", new String[]{"id", "name"}, null, null, null, null, null);
        while (cursor.moveToNext()){
            Department department = new Department();
            department.setId(cursor.getInt(cursor.getColumnIndex("id")));
            department.setName(cursor.getString(cursor.getColumnIndex("name")));
            departments.add(department);
        }
        return departments;
    }

    public void addPerson(View view){
        db = dbHelper.getWritableDatabase();

        //way1--------------------
//        db.execSQL("INSERT INTO Person(name, salary) VALUES(?, ?)",
//                new String[]{editTextName.getText().toString(), editTextSalary.getText().toString()});

        //way2--------------------
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", editTextName.getText().toString());
        contentValues.put("salary", editTextSalary.getText().toString());
        contentValues.put("department_id", ((Department) spinner.getSelectedItem()).getId());
        Toast.makeText(this, "dep id : " + ((Department) spinner.getSelectedItem()).getId(), Toast.LENGTH_LONG).show();
        db.insert("Person", null, contentValues);
    }

    public void showAction(View view){
        db = dbHelper.getReadableDatabase();

        //way1---------------
//        Cursor cursor = db.rawQuery("SELECT * FROM Person", null);
        //way2----------------
        Cursor cursor = db.query("Person", new String[]{"id", "name", "salary", "department_id"},
                "salary > ?", new String[]{"2000"}, null, null, "salary" );
        //-----------------

        String persons = "";
        while (cursor.moveToNext()){
           persons += cursor.getString(cursor.getColumnIndex("id")) + ", "
                   + cursor.getString(cursor.getColumnIndex("name")) + ", "
                   + cursor.getString(cursor.getColumnIndex("salary")) + ", dep: "
                   + cursor.getInt(cursor.getColumnIndex("department_id")) + ", \n";
        }
        textView.setText(persons);

    }

    public void deleteAction(View view){
        String personId = editTextPersonId.getText().toString();

        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Person where id = ?", new String[]{personId});
    }
    public void updateAction(View view){
        String personId = editTextPersonId.getText().toString();
        String name = editTextName.getText().toString();
        String salary = editTextSalary.getText().toString();

        db = dbHelper.getWritableDatabase();

        //way1-----------------
//        db.execSQL("UPDATE Person SET name = ?, salary =? WHERE id = ?", new String[]{name, salary, personId});

        //way2-----------------
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("salary", salary);
        db.update("Person", contentValues, "id = ?", new String[]{personId});
    }
}
