package ntnk.sample.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private String databaseName;
    private int version;

    public DBHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, 3);
        this.context = context;
        this.databaseName = name;
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_person = "CREATE TABLE Person( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT,"
                + "salary FLOAT, "
                + "department_id INTEGER"
                + ")";
        db.execSQL(create_person);

        String create_department = "CREATE TABLE Department( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT"
                + ")";
        db.execSQL(create_department);

        //
        db.execSQL("INSERT INTO Department(name) VALUES(?)", new String[]{"Dep1"});
        db.execSQL("INSERT INTO Department(name) VALUES(?)", new String[]{"Dep2"});
        db.execSQL("INSERT INTO Department(name) VALUES(?)", new String[]{"Dep3"});
        db.execSQL("INSERT INTO Department(name) VALUES(?)", new String[]{"Dep4"});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            String drop_table_person = "DROP TABLE Person";
            db.execSQL(drop_table_person);
            String drop_table_department = "DROP TABLE Department";
            db.execSQL(drop_table_department);
            onCreate(db);
        }
    }

}
