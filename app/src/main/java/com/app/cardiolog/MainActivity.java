package com.app.cardiolog;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.app.cardiolog.Models.DialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
 FloatingActionButton mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= findViewById(R.id.add_record_btn);
        mButton.setOnClickListener(view -> {
            DialogFragment dialogFragment =new DialogFragment();
            dialogFragment.show(getFragmentManager(),"MyFragment");

        });
    }
}