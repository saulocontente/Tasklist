package com.aulaudemy.tasklist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aulaudemy.tasklist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements iTaskDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {
        DBhelper dBhelper = new DBhelper(context);
        write = dBhelper.getWritableDatabase();
        read = dBhelper.getReadableDatabase();
    }

    @Override
    public boolean create(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", task.getDescription());
        try {
            write.insert(DBhelper.TABLE_TASKS, null,contentValues);
            Log.i("INFO.DB", "Success on saving item");
        } catch (Exception exception) {
            Log.e("INFO.DB", "ERROR "+exception.getMessage()+" on save item");
            return false;
        }
        return true;
    }

    @Override
    public List<Task> read() {
        List<Task> taskList = new ArrayList<>();
        String sql = "SELECT * FROM " +DBhelper.TABLE_TASKS+";";
        Cursor cursor = read.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Task task = new Task();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            task.setId(id);
            task.setDescription(description);
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public boolean update(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("description", task.getDescription());
        try {
            String[] args = {task.getId().toString()} ;
            write.update(
                    DBhelper.TABLE_TASKS,
                    contentValues,
                    "id=?",
                    args
            );
            Log.i("INFO.DB", "Success on update item");
            return false;
        } catch (Exception exception) {
            Log.e("INFO.DB", "ERROR "+exception.getMessage()+" on update item");
        }
        return true;
    }

    @Override
    public boolean delete(Task task) {
        return false;
    }
}
