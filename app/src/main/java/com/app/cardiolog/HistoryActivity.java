package com.app.cardiolog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.cardiolog.Models.Cardiolog;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
  RecyclerView mRecyclerView;
  ArrayList<Cardiolog>loglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = findViewById(R.id.recyclerView);
        loglist = new ArrayList<>();


    }
}