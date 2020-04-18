package ntnk.sample.sqlitecrudexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todolistDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tb_tasks";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ISDONE = "isDone";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskTableScrip = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT,%s LONG,%s INTEGER DEFAULT 0)", TABLE_NAME, COLUMN_ID , COLUMN_TITLE, COLUMN_NOTE, COLUMN_DATE, COLUMN_ISDONE);
        db.execSQL(createTaskTableScrip);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTaskTableScript = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropTaskTableScript);
    }

    /**
     * create record
     * @param taskItem
     */
    public void addTask(TaskItem taskItem){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, taskItem.getTitle());
        values.put(COLUMN_NOTE, taskItem.getNote());
        values.put(COLUMN_DATE, taskItem.getDate().getTime());
        values.put(COLUMN_ISDONE, !taskItem.getDome() ? 0 : 1);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //read record
    //public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)

    /**
     *
     * @param taskItemId
     * @return
     */
    public TaskItem getTaskItem(int taskItemId){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(taskItemId)}, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            TaskItem taskItem = new TaskItem(cursor.getInt(0), cursor.getString(1),  cursor.getString(2),new Date(cursor.getLong(3)), cursor.getInt(4) == 0 ? false : true);
            return taskItem;
        }

        return null;

    }

    public List<TaskItem> getAllTaskItem(){
        List<TaskItem> taskList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            TaskItem taskItem = new TaskItem(cursor.getInt(0), cursor.getString(1),  cursor.getString(2),new Date(cursor.getLong(3)), cursor.getInt(4) == 0 ? false : true);
            taskList.add(taskItem);
            cursor.moveToNext();
        }
        return taskList;
    }

    //db.update(String table, ContentValues values, String whereClause, String[] whereArgs)
    public void updateTaskItem(TaskItem taskItem){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, taskItem.getTitle());
        contentValues.put(COLUMN_NOTE, taskItem.getNote());
        contentValues.put(COLUMN_DATE, taskItem.getDate().getTime());
        contentValues.put(COLUMN_ISDONE, !taskItem.getDome() ? 0 : 1);

        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(taskItem.getId())});
        db.close();
    }

    //db.delete(String table, String whereClause, String[] whereArgs)
    public void deleteTaskItem(Integer taskItemId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { String.valueOf(taskItemId)});
    }

}
