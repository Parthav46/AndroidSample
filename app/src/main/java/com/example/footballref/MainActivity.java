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

        final TextView counterA = (TextView) findViewById(R.id.counterA);
        final TextView counterB = (TextView) findViewById(R.id.counterB);

        counterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = Integer.parseInt(counterA.getText().toString());
                score++;
                String counterValue = String.valueOf(score);
                counterA.setText(counterValue);
            }
        });

        counterA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int score = Integer.parseInt(counterA.getText().toString());
                if(score > 0) score--;
                String counterValue = String.valueOf(score);
                counterA.setText(counterValue);
                return true;
            }
        });

        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = Integer.parseInt(counterB.getText().toString());
                score++;
                String counterValue = String.valueOf(score);
                counterB.setText(counterValue);
            }
        });

        counterB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int score = Integer.parseInt(counterB.getText().toString());
                if(score > 0) score--;
                String counterValue = String.valueOf(score);
                counterB.setText(counterValue);
                return true;
            }
        });
    }

    public void resetGoal(View view) {
        TextView counterA = (TextView) findViewById(R.id.counterA);
        TextView counterB = (TextView) findViewById(R.id.counterB);
        String counterValue = "0";
        counterA.setText(counterValue);
        counterB.setText(counterValue);
    }
}
