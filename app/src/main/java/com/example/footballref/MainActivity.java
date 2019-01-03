package com.example.footballref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseGoal(View view) {
        TextView counter = (TextView) findViewById(view.getId());
        int score = Integer.parseInt(counter.getText().toString());
        score++;
        String counterValue = String.valueOf(score);
        counter.setText(counterValue);
    }

    public void resetGoal(View view) {
        TextView counterA = (TextView) findViewById(R.id.counterA);
        TextView counterB = (TextView) findViewById(R.id.counterB);
        String counterValue = "0";
        counterA.setText(counterValue);
        counterB.setText(counterValue);
    }
}
