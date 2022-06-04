package sg.edu.rp.c346.id21021785.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner selectItem;
    EditText editText;
    Button btnAdd;
    Button btnRemove;
    ListView tasks;
    Button btnClear;

    ArrayList<String> taskItems;
    ArrayAdapter aaTaskItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectItem = findViewById(R.id.spinnerForItems);
        editText = findViewById(R.id.editTextInput);
        btnAdd = findViewById(R.id.buttonAdd);
        btnRemove = findViewById(R.id.buttonRemove);
        tasks = findViewById(R.id.tasks);
        btnClear = findViewById(R.id.buttonClear);

        taskItems = new ArrayList<String>();

        aaTaskItem = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskItems);

        tasks.setAdapter(aaTaskItem);

        selectItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        btnAdd.setTextColor(Color.parseColor("#000000"));
                        btnRemove.setTextColor(Color.parseColor("#606060"));
                        editText.setHint("Enter a new task");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        btnAdd.setTextColor(Color.parseColor("#505050"));
                        btnRemove.setTextColor(Color.parseColor("#000000"));
                        editText.setHint("Enter the position of the task to delete it");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = editText.getText().toString();
                if (!newTask.equals("")) {
                    taskItems.add(taskItems.size(), (taskItems.size() + 1) + ": " + newTask);
                    aaTaskItem.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = editText.getText().toString();
                int position = 0;
                if (!newTask.equals("") && isNumeric(newTask)) {
                    position = Integer.parseInt(newTask);
                    if (position > 0 && position < taskItems.size() + 1) {
                        taskItems.remove(position - 1);
                        aaTaskItem.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Task removed", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid task position", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task's position", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskItems.isEmpty()) {
                    taskItems.clear();
                    aaTaskItem.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "All tasks cleared", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "There are no tasks to clear", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    public static boolean isNumeric(String taskPos) {
        return taskPos.matches("[-+]?\\d*\\.?\\d+");
    }
}