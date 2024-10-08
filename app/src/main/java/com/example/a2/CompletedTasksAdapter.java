
package com.example.a2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CompletedTasksAdapter extends ArrayAdapter<Task> {
    ArrayList<Task> tasks;
    Context context;
    TextView tvTitle,tvDescription;


    OnCompletedTaskItemClickListener onTaskItemClick;
    public interface OnCompletedTaskItemClickListener{
        void onCompletedTaskItemClick(int position);
    }
    public CompletedTasksAdapter(@NonNull Context context, ArrayList<Task> tasks) {
        super(context,0,tasks);
        // constructor is of Array Adapter that has id of android.R.layout.simple_list_item_1
        // we are making custom adapter so passed 0
        //context is parent activity
        this.context=context;
        this.tasks=tasks;
        this.onTaskItemClick=(OnCompletedTaskItemClickListener)context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task=getItem(position);
        if(convertView==null){
            // inflate with xml
            convertView= LayoutInflater.from(context).inflate(R.layout.singleitem_design,parent,false);

        }
        // init hooks
        tvTitle=convertView.findViewById(R.id.tvTitle);
        tvDescription=convertView.findViewById(R.id.tvDescription);

        String title,description;
        title=task.getTitle().trim();
        description=task.getDescription().trim();
        if(!title.isEmpty() && !description.isEmpty() ){
            tvTitle.setText(task.getTitle().trim());
            tvDescription.setText(task.getDescription().trim());

        }
        else{
            Toast.makeText(context,"Title is :"+ title + "Description is :"+ description,Toast.LENGTH_SHORT).show();
        }



        convertView.setOnClickListener(v->{
            if(onTaskItemClick!=null){
                onTaskItemClick.onCompletedTaskItemClick(position);
            }
        });

        // convertview will have text description boxes add them
        // click position give positon
        return convertView;

    }

}
