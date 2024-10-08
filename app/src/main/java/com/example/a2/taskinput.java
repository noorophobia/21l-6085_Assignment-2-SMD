package com.example.a2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class taskinput extends Fragment {
    EditText etTitle, etDescription;
    Button btnSave;

    private static final String TAG = "taskinputFragment";

    // Define the interface
    public interface OnTaskAddedListener {
        void onTaskAdded(String title, String description);
    }

    private OnTaskAddedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach called");
        try {
            listener = (OnTaskAddedListener) context; // Ensure the activity implements the interface
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTaskAddedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        View v = inflater.inflate(R.layout.fragment_taskinput, container, false);
        etTitle = v.findViewById(R.id.etTitle);
        etDescription = v.findViewById(R.id.etDescription);
        btnSave = v.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            if(!title.isEmpty()){
            // Notify the listener (MainActivity) with the task data
            listener.onTaskAdded(title, description);}

            // Optionally pop the fragment from the back stack
            getFragmentManager().popBackStack();
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach called");
    }
}
