package com.polezin85.tomatomanager;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.polezin85.Timer.*;
import static com.polezin85.Timer.Constants.*;

public class Start extends AppCompatActivity {


    //Views
    private TextView mainTimer;
    private TextView titleText;
    private TextView pomodoroCountText; //Count pomodoro circles
    private Button startTimerBtn;

    private int minutes; //Calculated minutes
    private int seconds; //Calculated seconds
    private String strSeconds; //Seconds for output
    private String strMinutes; //Minutes for output
    private boolean isWork; //If work interval
    private int pomodoroBigCircle = 0; //3 or 4 pomodoro circles
    private int pomodoroCount = 0; //Count pomodoro circles (after the last rest)

    //Timers
    Timer t;
    WorkTimer workTimer;
    RestTimer restTimer;
    BigRestTimer bigRestTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**
         * Init views
         */
        titleText = (TextView) findViewById(R.id.textViewTitle);
        titleText.setText(TEXT_TITLE_WORK);

        mainTimer = (TextView) findViewById(R.id.textViewMainTimer);
        mainTimer.setText(START_WORK_TIME);

        startTimerBtn = (Button) findViewById(R.id.button);

        pomodoroCountText = (TextView) findViewById(R.id.textViewPomodoroCount);
        pomodoroCountText.setText(pomodoroCount + POMODORO_COUNT_END_TEXT);
        /**
         * End of init views
         */

        isWork = true; //First is work

        /**
         * Init timers
         */
        workTimer = new WorkTimer((int)(MILLISEC_IN_MINUTE * POMODORO_WORK_INTERVAL), MILLISEC_IN_SEC);
        workTimer.defineTitle(titleText);
        workTimer.defineTimer(mainTimer);
        workTimer.defineStartButton(startTimerBtn);
        workTimer.definePomodoroCounter(pomodoroCountText);
        workTimer.defineVibrator(this);

        restTimer = new RestTimer((int)(MILLISEC_IN_MINUTE * POMODORO_REST_INTERVAL), MILLISEC_IN_SEC);
        restTimer.defineTitle(titleText);
        restTimer.defineTimer(mainTimer);
        restTimer.defineStartButton(startTimerBtn);
        restTimer.definePomodoroCounter(pomodoroCountText);
        restTimer.defineVibrator(this);

        bigRestTimer = new BigRestTimer((int)(MILLISEC_IN_MINUTE * POMODORO_BIG_REST_INTERVAL), MILLISEC_IN_SEC);
        bigRestTimer.defineTitle(titleText);
        bigRestTimer.defineTimer(mainTimer);
        bigRestTimer.defineStartButton(startTimerBtn);
        bigRestTimer.definePomodoroCounter(pomodoroCountText);
        bigRestTimer.defineVibrator(this);
        /**
         * End of init timers
         */
    }

    /**
     * Start timer
     * @param view
     */
    public void timerStart(View view) {
        if(isWork) {                        //If work interval then
            pomodoroBigCircle++;            //count pomodoro big circle (3 or 4 work intervals)
            t = workTimer;                  //and choose work timer
            isWork = false;                 //switch flag to the rest
            if(pomodoroBigCircle == POMODORO_BIG_CIRCLE)      //If pomodoro big circle is now then
                t.isBigCircle = true;       //Up the flag of big circle
        }
        else {                              //If rest interval then
            if(pomodoroBigCircle == POMODORO_BIG_CIRCLE) {    //If pomodoro big circle is now then
                pomodoroBigCircle = 0;      //reinitialize pomodoro big circle
                t = bigRestTimer;           //and choose big rest timer
                isWork = true;              //switch flag to work
            }
            else {                          //If pomodoro big circle not is now then
                t = restTimer;              //choose rest timer
                isWork = true;              //switch flag to the work
            }
            //t.isBigCircle = false;      //and reset the flag of big circle
            t.pomodoroCounter = ++pomodoroCount; //Increments pomodoro counter
        }
        t.start();
        startTimerBtn.setClickable(false); //Lock button while timer is working
        startTimerBtn.setTextColor(Color.rgb(124,124,124)); //Change colour of button text
    }

}
