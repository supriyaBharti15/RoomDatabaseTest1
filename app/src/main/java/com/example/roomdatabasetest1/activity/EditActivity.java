package com.example.roomdatabasetest1.activity;

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

        //database
        database = AppDatabase.getDatabaseInstance(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = new Person(name.getText().toString(), email.getText().toString(),
                        pincode.getText().toString(), city.getText().toString(), phone.getText().toString());
                if (save.getText().toString().equalsIgnoreCase("save")) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            database.personDao().insertInDB(person);
                            finish();
                        }
                    });
                }
            }
        });
    }
}
