package com.aulaudemy.tasklist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aulaudemy.tasklist.R;
import com.aulaudemy.tasklist.databinding.ActivityInputBinding;
import com.aulaudemy.tasklist.helper.TaskDAO;
import com.aulaudemy.tasklist.model.Task;

public class InputActivity extends AppCompatActivity {
    private ActivityInputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task:
                String task_description = binding.inputTask.getText().toString();
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                if(task_description.isEmpty()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Task description is empty",
                            Toast.LENGTH_LONG
                    ).show();
                } else {
                    Task task = new Task();
                    Toast.makeText(
                            getApplicationContext(),
                            "Saving...",
                            Toast.LENGTH_LONG
                    ).show();
                    task.setDescription(task_description);
                    if (taskDAO.create(task)){
                        finish();
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "An error has occurred",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}