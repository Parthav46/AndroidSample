package com.example.footballref;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreA;
    private int scoreB;
    private long time = 120000L;
    private long timeLeft;
    private boolean isTimerRunning;

    private TextView counterA;
    private TextView counterB;
    private TextView timeView;
    private ProgressBar timeProgress;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = 0;
        scoreB = 0;
        timeLeft = time;
        isTimerRunning = false;

        counterA = (TextView) findViewById(R.id.counterA);
        counterB = (TextView) findViewById(R.id.counterB);
        timeView = (TextView) findViewById(R.id.time);
        timeProgress = (ProgressBar) findViewById(R.id.time_progress);

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
                    deactivateCounter();
                    isTimerRunning = false;
                    String timeValue = timeView.getText().toString();
                    int dividerIndex = timeValue.indexOf(':');
                    int minute = Integer.parseInt(timeValue.substring(0, dividerIndex));
                    int second = Integer.parseInt(timeValue.substring(dividerIndex+1, timeValue.length()));
                    timeLeft = (long)((minute * 60) + second) * 1000;
                } else {
                    timer = getTimer(timeLeft);
                    activateCounter();
                    timer.start();
                }
            }
        });

        setTime(timeLeft);
        deactivateCounter();
    }

    private CountDownTimer getTimer(long timePeriod) {
        return new CountDownTimer(timePeriod, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                setTime(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                timeLeft = 0;
                setTime(timeLeft);
            }
        };
    }

    private void activateCounter() {
        counterA.setEnabled(true);
        counterB.setEnabled(true);
    }

    private void deactivateCounter() {
        counterA.setEnabled(false);
        counterB.setEnabled(false);
    }

    private void setTime(long timePeriod) {
        String seconds = String.valueOf((timePeriod / 1000L) % 60L);
        String timeString = String.valueOf(timePeriod / 60000L) + ":" + ((seconds.length() == 1) ? "0" : "") + seconds;
        timeView.setText(timeString);
        timeProgress.setProgress((int)(timePeriod * 10000L / time) );
    }

    public void resetGoal(View view) {
        if(isTimerRunning){
            timer.cancel();
            isTimerRunning = false;
        }
        deactivateCounter();
        timeLeft = time;
        setTime(timeLeft);
        scoreA = 0;
        scoreB = 0;
        String counterValue = "0";
        counterA.setText(counterValue);
        counterB.setText(counterValue);
    }
}
