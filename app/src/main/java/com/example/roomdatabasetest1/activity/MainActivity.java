package com.example.roomdatabasetest1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.roomdatabasetest1.R;
import com.example.roomdatabasetest1.adapter.MyPersonAdapter;
import com.example.roomdatabasetest1.database.AppDatabase;
import com.example.roomdatabasetest1.database.AppExecutors;
import com.example.roomdatabasetest1.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton addBtn;
    private AppDatabase database;
    private RecyclerView recyclerView;
    private MyPersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.addPerson);
        recyclerView = findViewById(R.id.recyclerView);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
        database = AppDatabase.getDatabaseInstance(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveDataFromDB();
    }

    private void retrieveDataFromDB() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Person> listPerson = database.personDao().getAllDataFromDB();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecyclerView(listPerson);
                    }
                });
            }
        });
    }

    private void initRecyclerView(List<Person> allPerson) {
        adapter = new MyPersonAdapter(this, allPerson);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}