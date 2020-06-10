package com.example.roomdatabasetest1.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "personTable")
public class Person {
    @PrimaryKey(autoGenerate = true)
    int primaryKey;
    String name;
    String email;
    String pincode;
    String city;
    String phoneNumber;

    public Person(int primaryKey, String name, String email, String pincode, String city, String phoneNumber) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.email = email;
        this.pincode = pincode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    @Ignore
    public Person(String name, String email, String pincode, String city, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.pincode = pincode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
