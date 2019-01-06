package com.example.footballref;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.footballref.data.MatchContract;

public class MainActivity extends AppCompatActivity {

    private int scoreA;
    private int scoreB;
    private long time = 120000L;
    private long timeLeft;
    private boolean isTimerRunning;

    private TextView counterA;
    private TextView counterB;
    private TextView teamNameA;
    private TextView teamNameB;
    private TextView timeView;
    private ProgressBar timeProgress;

    private CountDownTimer timer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                resetGoal();
                return true;
            case R.id.save:
                saveData();
                return true;
            case R.id.history:
                resetGoal();
                Intent history = new Intent(this, HistoryActivity.class);
                startActivity(history);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = 0;
        scoreB = 0;
        timeLeft = time;
        isTimerRunning = false;

        counterA = findViewById(R.id.counterA);
        counterB = findViewById(R.id.counterB);
        teamNameA = findViewById(R.id.teamA);
        teamNameB = findViewById(R.id.teamB);
        timeView = findViewById(R.id.time);
        timeProgress = findViewById(R.id.time_progress);

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

        timeProgress.setOnClickListener(new View.OnClickListener() {
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

        timeProgress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getInput(R.id.time);
                return true;
            }
        });

        teamNameA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getInput(R.id.teamA);
                return true;
            }
        });

        teamNameB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getInput(R.id.teamB);
                return true;
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
                deactivateCounter();
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
        timeProgress.setProgress((int)((time == 0) ? 0 : (timePeriod * 10000L / time)));
    }

    public void resetGoal() {

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

    public void displayCard(View view) {
        int id = view.getId();
        Intent cardIntent = new Intent(this, CardActivity.class);
        switch (id) {
            case R.id.yellowA:
            case R.id.yellowB:
                cardIntent.putExtra("color", R.color.cardYellow);
                break;
            case R.id.redA:
            case R.id.redB:
                cardIntent.putExtra("color", R.color.cardRed);
                break;
        }
        startActivity(cardIntent);
    }

    private void getInput(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup parent = findViewById(R.id.parent);
        View dialog = getLayoutInflater().inflate(R.layout.input_dialog, parent, false);
        final EditText input = dialog.findViewById(R.id.input);
        if(id == R.id.time){
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
            input.setHint(R.string.input_time);
            builder.setTitle(R.string.title_time);
        } else {
            builder.setTitle(R.string.title_team);
            input.setHint(R.string.input_team);
        }
        builder.setView(dialog);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString();
                if(id == R.id.time) {
                    time = Long.valueOf(text) * 60000L;
                    resetGoal();
                } else {
                    TextView name = findViewById(id);
                    name.setText(text);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveData(){
        String teamA = teamNameA.getText().toString();
        String teamB = teamNameB.getText().toString();
        int scoreA = Integer.parseInt(counterA.getText().toString());
        int scoreB = Integer.parseInt(counterB.getText().toString());
        ContentValues values = new ContentValues();
        values.put(MatchContract.MatchEntry.COLUMN_TEAM_A, teamA);
        values.put(MatchContract.MatchEntry.COLUMN_TEAM_B, teamB);
        values.put(MatchContract.MatchEntry.COLUMN_SCORE_A, scoreA);
        values.put(MatchContract.MatchEntry.COLUMN_SCORE_B, scoreB);
        getContentResolver().insert(MatchContract.MatchEntry.CONTENT_URI, values);
        resetGoal();
    }
}
