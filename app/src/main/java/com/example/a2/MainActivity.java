package com.example.a2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskItemClickListener,CompletedTasksAdapter.OnCompletedTaskItemClickListener, taskinput.OnTaskAddedListener {

    //hooks
    ListView tasksList;
    TaskAdapter taskAdapter;
    FragmentManager fragmentManager;
    ArrayList<Task> tasks,completedTasks;
    Button history;
    CompletedTasksAdapter completedTasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        completedTasks=new ArrayList<Task>();
        fragmentManager = getSupportFragmentManager();
        tasksList = findViewById(R.id.task_list_view);
        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks);
        tasksList.setAdapter(taskAdapter);

        // Initialize your taskInput fragment
        FloatingActionButton fab = findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> {
            taskinput taskInput = new taskinput();
             fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, taskInput)
                    .addToBackStack(null)
                    .commit();
            // Hide history button and FAB when task input fragment is active
            fab.setVisibility(View.GONE);
            history.setVisibility(View.GONE);
        });

        history = findViewById(R.id.btn_view_completed_tasks);
        history.setOnClickListener(v -> showCompletedTasksDialog()); // Show dialog for completed tasks

        // Example tasks for demonstration
        tasks.add(new Task("New task Added", "Let's see what we have got here hello/n"));

        tasks.add(new Task("New task 2 Added", "Let's see what we have got here hello/n"));
        // Listen for changes in back stack
        fragmentManager.addOnBackStackChangedListener(() -> {
            // If there are no fragments in the back stack, show the buttons again
            if (fragmentManager.getBackStackEntryCount() == 0) {
                fab.setVisibility(View.VISIBLE);
                history.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onTaskAdded(String title, String description) {
        if(description.isEmpty()){
            description="no description";
        }
        Task newTask = new Task(title, description);

        tasks.add(newTask);
        taskAdapter.notifyDataSetChanged(); // Notify the adapter of data change
    }

    @Override
    public void onTaskItemClick(int position) {
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setTitle("Completed Task");
        deleteDialog.setMessage("Did you complete this task?");

        deleteDialog.setPositiveButton("Yes", (dialog, which) -> {
            Task completedTask = tasks.get(position);
            completedTasks.add(completedTask);
            // MyApplication.addCompletedTask(completedTask); // Commented out as per request
            tasks.remove(position);
            taskAdapter.notifyDataSetChanged(); // Notify the adapter of data change
        });

        deleteDialog.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = deleteDialog.create();
        dialog.show();
    }

    private void showCompletedTasksDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.completed_tasks_dialog, null);
        builder.setView(dialogView);
        TextView tvCompletedTask= dialogView.findViewById(R.id.tvCompletedTask);
        ListView completedTasksList = dialogView.findViewById(R.id.dialogcompleted_tasks_list_view);
        Button closeButton = dialogView.findViewById(R.id.btnCloseDialog);

        // ArrayList<Task> completedTasks = MyApplication.completedTasks; // Commented out as per request

        if (completedTasks == null || completedTasks.isEmpty()) {
            tvCompletedTask.setText("No Completed Tasks Yet");

        } else {
              completedTasksAdapter = new CompletedTasksAdapter(this, completedTasks);
            completedTasksList.setAdapter(completedTasksAdapter);
        }

         AlertDialog dialog = builder.create();

        closeButton.setOnClickListener(v -> dialog.dismiss()); // Dismiss dialog on button click

        dialog.show();
    }

    @Override
    public void onCompletedTaskItemClick(int position) {
        completedTasks.remove(position);
         completedTasksAdapter.notifyDataSetChanged();
    }
}
