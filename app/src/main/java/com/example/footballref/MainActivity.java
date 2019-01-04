package com.example.footballref;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreA;
    private int scoreB;
    private long time;
    private boolean isTimerRunning;

    private TextView counterA;
    private TextView counterB;
    private TextView timeView;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = 0;
        scoreB = 0;
        time = 120;
        isTimerRunning = false;

        counterA = (TextView) findViewById(R.id.counterA);
        counterB = (TextView) findViewById(R.id.counterB);
        timeView = (TextView) findViewById(R.id.time);

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

        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRunning){
                    timer.cancel();
                    isTimerRunning = false;
                    String timeValue = timeView.getText().toString();
                    int dividerIndex = timeValue.indexOf(':');
                    int minute = Integer.parseInt(timeValue.substring(0, dividerIndex));
                    int second = Integer.parseInt(timeValue.substring(dividerIndex+1, timeValue.length()));
                    time = (long)((minute * 60) + second);
                } else {
                    timer = getTimer(time);
                    timer.start();
                }
            }
        });
    }

    CountDownTimer getTimer(long timePeriod) {
        return new CountDownTimer(timePeriod * 1000L, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                long timeValue = millisUntilFinished / 1000L;
                String timeString = String.valueOf(timeValue / 60L) + ":" + String.valueOf(timeValue % 60L);
                timeView.setText(timeString);
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
            }
        };
    }

    public void resetGoal(View view) {
        if(isTimerRunning){
            timer.cancel();
            isTimerRunning = false;
        }
        time = 120;
        scoreA = 0;
        scoreB = 0;
        String counterValue = "0";
        counterA.setText(counterValue);
        counterB.setText(counterValue);
    }
}
