package com.example.vploaia.todoapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.TextView.BufferType.EDITABLE;
import static com.example.vploaia.todoapp.R.id.info;

/**
 * Created by vploaia on 2/14/2017.
 */

public class EditTaskActivity extends AppCompatActivity {

    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);


        Bundle extras = getIntent().getExtras();

        try {
            if (extras != null) {
                String value = extras.getString("titleTaskText");
                Log.d("D", value);
                EditText et = (EditText) findViewById(R.id.title_editTask);
                et.setText(value, TextView.BufferType.EDITABLE);
            }

        } catch (NullPointerException ex) {
            System.out.println("------" + ex);
        }





       /* String value = intent.getStringExtra("titleTaskText"); //"titleTaskText"
        EditText et = (EditText) findViewById(R.id.title_editTask);
        et.setText(value, TextView.BufferType.EDITABLE);*/

       /* Intent intent = getIntent();
        String value = intent.getStringExtra("titleTaskText"); //"titleTaskText"
        EditText et = (EditText) findViewById(R.id.title_editTask);
        et.setText(value, TextView.BufferType.EDITABLE);*/
    }

    public void saveAfterEditTask(View view) {
/*        EditText et = (EditText) findViewById(R.id.title_editTask);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        mainActivity.getSelectedElementToDelete(info.position);*/
/*        setContentView(R.layout.edit_task);
        EditText titleEditTask = (EditText) findViewById(R.id.title_editTask);
        String itemText = titleEditTask.getText().toString();
       String c ="" + Log.d("D", itemText);
        System.out.println("--------------------" + c);
        if (itemText.length() > 1) {
            mainActivity.itemsAdapter.add(itemText);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Saved !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
        }
        titleEditTask.setText("");*/

        Intent intent = new Intent(this, MainActivity.class);

        try{
            EditText editText = (EditText) findViewById(R.id.title_editTask);
            String message = editText.getText().toString();
            Log.d("D", message);
            intent.putExtra("titleTaskText", message);
            Toast.makeText(getApplicationContext(),
                    "Saved !", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex) {

        }

        startActivity(intent);



    }




}
