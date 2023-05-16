package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edittext ;
    private Button add;
    private ListView tasksList;
    private ArrayList<String> tasks = new ArrayList<String>();

    private ArrayAdapter<String> adapter ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.edittext);
        add = findViewById(R.id.add);
        tasksList = findViewById(R.id.task);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                tasks
        );

        tasksList.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = edittext.getText().toString();
                if(!task.isEmpty())
                {
                    tasks.add(task);
                    adapter.notifyDataSetChanged();
                    edittext.setText("");
                }
            }
        });


       tasksList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(MainActivity.this, "deleted "+tasks.get(position), Toast.LENGTH_SHORT).show();
               tasks.remove(position);
               adapter.notifyDataSetChanged();
               return false;
           }
       });




    }
}