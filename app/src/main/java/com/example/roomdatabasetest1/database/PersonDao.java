package com.example.roomdatabasetest1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabasetest1.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertInDB(Person person);

    @Query("DELETE FROM personTable where primaryKey = :primaryKey")
    void deleteFromDB(int primaryKey);

    @Update
    void updateDataInDB(Person person);

    @Query("SELECT * FROM personTable ORDER BY primaryKey")
    List<Person> getAllDataFromDB();

    @Query("SELECT * FROM personTable where primaryKey=:primaryKey")
    Person getDataByID(int primaryKey);
}
