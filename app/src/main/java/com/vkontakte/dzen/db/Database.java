package com.vkontakte.dzen.db;

import android.content.Context;
import android.database.Cursor;

import com.vkontakte.dzen.category.Category;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final android.database.sqlite.SQLiteDatabase db;

    public Database(Context context) {
        db = new SQLiteDatabase(context).getWritableDatabase();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.join("", "SELECT * FROM ", Constants.TABLE_NAME_CATEGORY, ";"), null);
        while(cursor.moveToNext()) {
            categories.add(new Category(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_ID)), cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_NAME))));
        }
        cursor.close();
        return categories;
    }
}
