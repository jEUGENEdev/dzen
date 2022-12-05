package com.vkontakte.dzen.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDatabase extends SQLiteOpenHelper {
    public SQLiteDatabase(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.join("", "CREATE TABLE IF NOT EXISTS ", Constants.TABLE_NAME_CATEGORY, " (", Constants.COLUMN_NAME_ID, " INTEGER PRIMARY KEY AUTOINCREMENT, ", Constants.COLUMN_NAME_NAME, " VARCHAR(255));"));
        initCategories(sqLiteDatabase, "Юмор", "Еда", "Кино", "Рестораны", "Прогулки",
                "Политика", "Новости", "Автомобили", "Сериалы", "Рецепты", "Работа", "Отдых",
                "Спорт");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
    }

    private void initCategories(android.database.sqlite.SQLiteDatabase sqLiteDatabase, String... categories) {
        for (String category : categories) {
            sqLiteDatabase.execSQL(String.join("", "INSERT INTO ", Constants.TABLE_NAME_CATEGORY, " (", Constants.COLUMN_NAME_NAME, ") VALUES ('", category, "');"));
        }
    }
}
