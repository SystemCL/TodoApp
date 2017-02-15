package com.example.vploaia.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static com.example.vploaia.todoapp.R.id.description;

/**
 * Created by vploaia on 2/14/2017.
 */
public class TaskListActivity extends Activity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper.TASKS_COLUMN_ID,
                                        DatabaseHelper.TASKS_COLUMN_NAME,
                                        DatabaseHelper.TASKS_COLUMN_DESCRIPTION};

    final int[] to = new int[] { R.id.id, R.id.title, description };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.list_tasks, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(description);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desccription = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), DisplayTaskActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("description", description);
                modify_intent.putExtra("id", id);

                /*startActivity(modify_intent);*/
            }
        });


    }

}
