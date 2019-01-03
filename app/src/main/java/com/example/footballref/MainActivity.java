package com.example.footballref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
    }

    public void increaseGoal(View view) {
        TextView counter = (TextView) findViewById(R.id.counter);
        score++;
        String counterValue = String.valueOf(score);
        counter.setText(counterValue);
    }
}
