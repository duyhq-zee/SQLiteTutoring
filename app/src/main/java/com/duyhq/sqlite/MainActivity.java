package com.duyhq.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;

    ArrayList<Task> taskList = new ArrayList<>();

    ListView taskLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create task database
        database = new Database(this, "Task.SQLite", null, 1);

        database.queryData("CREATE TABLE IF NOT EXISTS Task(id Integer PRIMARY KEY AUTOINCREMENT, title nvarchar(200))");

        // Fetch task data
        Cursor taskData = database.getData("SELECT * FROM Task");

        while (taskData.moveToNext()) { // If remain next row
            int id = taskData.getInt(0);
            String title = taskData.getString(1);

            taskList.add(new Task(id, title));

            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        }


        taskLV = findViewById(R.id.task_lv);
        TaskAdapter taskAdapter = new TaskAdapter(this, R.layout.task_item,
                taskList);

        taskLV.setAdapter(taskAdapter);
    }
}