package com.example.vploaia.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vploaia on 2/14/2017.
 */

public class AddTaskActivity extends Activity implements OnClickListener {

    private Button addToDoBtn;
    private EditText titleEditText;
    private EditText descriptionEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add task");

        setContentView(R.layout.activity_add_record);

        titleEditText = (EditText) findViewById(R.id.title_edittext);
        descriptionEditText = (EditText) findViewById(R.id.description_edittext);

        addToDoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addToDoBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_record:

                final String title = titleEditText.getText().toString();
                final String description = descriptionEditText.getText().toString();

                dbManager.insertTask(title, description);

                Intent main = new Intent(AddTaskActivity.this, TaskListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

                break;
        }
    }



}
