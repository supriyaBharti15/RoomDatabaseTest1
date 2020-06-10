package com.example.roomdatabasetest1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdatabasetest1.R;
import com.example.roomdatabasetest1.database.AppDatabase;
import com.example.roomdatabasetest1.database.AppExecutors;
import com.example.roomdatabasetest1.model.Person;

public class EditActivity extends AppCompatActivity {
    EditText name, email, city, pincode, phone;
    Button save;
    AppDatabase database;
    private int recordID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        //Find id of all view
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        city = findViewById(R.id.edit_city);
        phone = findViewById(R.id.edit_number);
        pincode = findViewById(R.id.edit_pincode);
        save = findViewById(R.id.addButton);

        //code to get intent for edit request
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("update")) {
            save.setText("Update");
            recordID = intent.getIntExtra("update", -1);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = database.personDao().getDataByID(recordID);
                    name.setText(person.getName());
                    email.setText(person.getEmail());
                    city.setText(person.getCity());
                    pincode.setText(person.getPincode());
                    phone.setText(person.getPhoneNumber());
                }
            });
        }

        //database
        database = AppDatabase.getDatabaseInstance(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = new Person(name.getText().toString(), email.getText().toString(),
                        pincode.getText().toString(), city.getText().toString(), phone.getText().toString());
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (save.getText().toString().equalsIgnoreCase("save")) {
                            database.personDao().insertInDB(person);
                        } else {
                            person.setPrimaryKey(recordID);
                            database.personDao().updateDataInDB(person);
                        }
                        finish();
                    }
                });
            }
        });
    }
}
