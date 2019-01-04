package com.example.footballref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreA;
    private int scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = 0;
        scoreB = 0;

        final TextView counterA = (TextView) findViewById(R.id.counterA);
        final TextView counterB = (TextView) findViewById(R.id.counterB);

        counterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreA++;
                String counterValue = String.valueOf(scoreA);
                counterA.setText(counterValue);
            }
        });

        counterA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (scoreA > 0) scoreA--;
                String counterValue = String.valueOf(scoreA);
                counterA.setText(counterValue);
                return true;
            }
        });

        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreB++;
                String counterValue = String.valueOf(scoreB);
                counterB.setText(counterValue);
            }
        });

        counterB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (scoreB > 0) scoreB--;
                String counterValue = String.valueOf(scoreB);
                counterB.setText(counterValue);
                return true;
            }
        });
    }

    public void resetGoal(View view) {
        TextView counterA = (TextView) findViewById(R.id.counterA);
        TextView counterB = (TextView) findViewById(R.id.counterB);
        scoreA = 0;
        scoreB = 0;
        String counterValue = "0";
        counterA.setText(counterValue);
        counterB.setText(counterValue);
    }
}
