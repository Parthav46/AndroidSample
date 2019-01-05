package com.example.footballref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent intent = getIntent();
        int color = intent.getIntExtra("color", 0);

        View parent = findViewById(R.id.parent);
        parent.setBackgroundColor(getResources().getColor(color));
    }
}
