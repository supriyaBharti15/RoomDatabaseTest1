package com.example.roomdatabasetest1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabasetest1.model.Person;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    private static String dbName = "myDB";
    private static final Object lock = new Object();

    public static AppDatabase getDatabaseInstance(Context context) {
        if (appDatabase == null) {
            synchronized (lock) {
                appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.dbName).build();
            }
        }
        return appDatabase;
    }

    public abstract PersonDao personDao();
}
