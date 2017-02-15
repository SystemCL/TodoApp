package com.example.vploaia.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    protected ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        lvItems = (ListView) findViewById(R.id.lvItems);
        registerForContextMenu(lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        int number = 1;
        for(int i=0; i<20; i++) {

            items.add("Task_" + number);
            number++;
        }
        /* String saved = "";
        savedInstanceState.putString("titleTaskText",saved);*/

/*         Bundle extras = getIntent().getExtras();
        String value = extras.getString("titleTaskText");
        Log.d("D", value);*/
/*
        String value = "";
        Intent intent = this.getIntent();
        if(intent != null) {
            value = intent.getStringExtra("titleTaskText");
        }
*/

/*        EditText et = (EditText) findViewById(R.id.title_editTask);
        et.setText(value, TextView.BufferType.EDITABLE);*//*
        items.add(value);
       *//* items.add("First Task");
        items.add("Second Task");*/
        //items.add(value);
        setupListViewListener();
        //super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lvItems) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.add:

                return true;
            case R.id.edit:
                editSelectedItem();
                return true;
            case R.id.delete:
                getSelectedElementToDelete(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onListItemClick(ListView lview, View v, int position, long id) {
        startActivity(new Intent(MainActivity.this, DisplayTaskActivity.class));
        //Toast.makeText(this, "Clicked on : " + names[position], Toast.LENGTH_LONG).show();
    }

    private void setupListViewListener() {

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {

                        String str = lvItems.getItemAtPosition(pos).toString();
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(item.getContext(), DisplayTaskActivity.class);
                        String message = str;
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }

                });
    }

/*    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });
    }*/
/*------------------------------------------------------------------*/
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if (itemText.length() > 1) {
            itemsAdapter.add(itemText);
        } else {
            Toast.makeText(MainActivity.this,
                    "Write something !", Toast.LENGTH_LONG).show();
        }
        etNewItem.setText("");

    }


    public void editSelectedItem() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
/*                        String text = (String) adapter.getItemAtPosition(pos);
                        Bundle bundle = new Bundle();
                        bundle.putString("titleTaskText", text);

                        Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);*/

                        String text = (String) adapter.getItemAtPosition(pos);
                        Intent i = new Intent(getApplicationContext(), EditTaskActivity.class);
                        i.putExtra("titleTaskText",text);
                        startActivity(i);


                    }

                });

    }

    public void getSelectedElementToDelete(int position) {
        Object item = ((ArrayAdapter) lvItems.getAdapter()).getItem(position);
        ((ArrayAdapter) lvItems.getAdapter()).remove(item);
    }

/*------------------------------------------------------------------*/


        /*lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                       *//* if(pos == 1){
                            Intent myIntent = new Intent(item.getContext(), DisplayTaskActivity.class);
                            startActivity(myIntent);
                        }*//*
                        // final View selectedView = item;

                        String str = lvItems.getItemAtPosition(pos).toString();
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(item.getContext(), DisplayTaskActivity.class);
                        String message = str;
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);

                    }

                });*/



    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view, int position) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayTaskActivity.class);
        EditText editText = (EditText) findViewById(R.id.etNewItem);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }


}
