package com.polezin85.tomatomanager;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Start extends AppCompatActivity {

    private static final int POMODORO_WORK_INTERVAL = 25; //Minutes of work interval
    private static final int POMODORO_REST_INTERVAL = 5; //Minutes of rest
    private static final int POMODORO_BIG_REST_INTERVAL = 15; //Minutes of big rest interval after 3 or 4 circles
    private static final int MILLISEC_IN_SEC = 1000;
    private static final int MILLISEC_IN_MINUTE = 60000;
    private static final boolean ACTIVE_BUTTON = true; //If button START TIMER is active
    private static final boolean NOT_ACTIVE_BUTTON = false; //If button START TIMER is not active
    private static final String TEXT_TITLE_WORK = "Time to work";
    private static final String TEXT_TITLE_REST = "Time for the rest";
    private static final String POMODORO_COUNT_END_TEXT = " 'pomodoro'"; //The end of count pomodoro string ("count" + 'pomodoros')
    private static final String START_WORK_TIME = "25:00"; //Start timer value when work interval
    private static final String START_REST_TIME = "05:00"; //Start timer value when rest interval
    private static final String START_BIG_REST_TIME = "15:00"; //Start timer value when big rest interval
    private static final String END_OF_TIME = "00:00";

    //Views
    private TextView mainTimer;
    private TextView titleText;
    private TextView pomodoroCountText; //Count pomodoro circles
    private Button startTimerBtn;

    //System services
    Vibrator vib;

    private int minutes; //Calculated minutes
    private int seconds; //Calculated seconds
    private String strSeconds; //Seconds for output
    private String strMinutes; //Minutes for output
    private boolean isWork; //If work interval
    private boolean isRest; //If rest interval
    private int pomodoroBigCircle = 0; //3 or 4 pomodoro circles
    private int pomodoroCount = 0; //Count pomodoro circles (after the last rest)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Pomodoro pomodoro = new Pomodoro();

        //Init booleans (first interval is work)
        isWork = true;
        isRest = false;

        /**
         * Init views
         */
        titleText = (TextView) findViewById(R.id.textViewTitle);
        pomodoro.setTitle(titleText, TEXT_TITLE_WORK);

        mainTimer = (TextView) findViewById(R.id.textViewMainTimer);
        pomodoro.setTimer(mainTimer, START_WORK_TIME);

        startTimerBtn = (Button) findViewById(R.id.button);

        pomodoroCountText = (TextView) findViewById(R.id.textViewPomodoroCount);
        pomodoroCountText.setText(pomodoroCount + POMODORO_COUNT_END_TEXT);
        /**
         * End of init views
         */

        //Init system services
        vib = (Vibrator) getSystemService(Vibrator.class); //Vibration
    }

    /**
     * Count down timer for work interval
     */
    public CountDownTimer cdWorkTimer = new CountDownTimer(MILLISEC_IN_MINUTE * POMODORO_WORK_INTERVAL, MILLISEC_IN_SEC) {
        @Override
        public void onTick(long l) {
            calculateTimer(l);
        }

        @Override
        public void onFinish() {
            onFinishWorkTimer();
        }
    };

    /**
     * Count down timer for rest interval
     */
    public CountDownTimer cdRestTimer = new CountDownTimer(MILLISEC_IN_MINUTE * POMODORO_REST_INTERVAL, MILLISEC_IN_SEC) {
        @Override
        public void onTick(long l) {
            calculateTimer(l);
        }

        @Override
        public void onFinish() {
            onFinishRestTimer();
        }
    };

    /**
     * Count down timer for big rest interval
     */

    public CountDownTimer cdBigRestTimer = new CountDownTimer(MILLISEC_IN_MINUTE * POMODORO_BIG_REST_INTERVAL, MILLISEC_IN_SEC) {
        @Override
        public void onTick(long l) {
            calculateTimer(l);
        }

        @Override
        public void onFinish() {
            onFinishRestTimer();
        }
    };

    /**
     * Start timer
     * @param view
     */
    public void timerStart(View view) {
        if(isWork && !isRest) {  //If work interval then
            pomodoroBigCircle++; //count pomodoro big circle (3 or 4 work intervals)
            cdWorkTimer.start(); //and start work timer
        }
        else {                              //If rest interval then
            if(pomodoroBigCircle == 3) {    //If pomodoro big circle is now then
                pomodoroBigCircle = 0;      //reinitialize pomodoro big circle
                cdBigRestTimer.start();     //and start big rest timer
            }
            else                            //If pomodoro big circle not is now then
                cdRestTimer.start();        //start rest timer
        }
        startTimerBtn.setClickable(NOT_ACTIVE_BUTTON); //Lock button while timer is working
        startTimerBtn.setTextColor(Color.rgb(124,124,124)); //Change colour of button text
    }

    public void onFinishWorkTimer(){
        vibrate();
        //Reinitialize timer
        isWork = false;
        isRest = true;
        if(pomodoroBigCircle == 3)                  //If pomodoro big circle is now then
            mainTimer.setText(START_BIG_REST_TIME); //set timer time on big rest time
        else                                        //If pomodoro big circle not is now then
            mainTimer.setText(START_REST_TIME);     //set timer time on rest time
        titleText.setText(TEXT_TITLE_REST); //Set title
        startTimerBtn.setClickable(ACTIVE_BUTTON); //Unlock button
        startTimerBtn.setTextColor(Color.rgb(0,0,0)); //Change colour of button text
    }

    public void onFinishRestTimer(){
        vibrate();
        //Reinitialize timer
        isWork = true;
        isRest = false;
        mainTimer.setText(START_WORK_TIME); //Set timer on work time
        titleText.setText(TEXT_TITLE_WORK); //Set title
        startTimerBtn.setClickable(ACTIVE_BUTTON); //Unlock button
        startTimerBtn.setTextColor(Color.rgb(0,0,0)); //Change colour of button text

        pomodoroCount++; //Count the pomodoro

        pomodoroCountText.setText(pomodoroCount + POMODORO_COUNT_END_TEXT); //Set pomodoro count text

    }

    public void calculateTimer(long lTime){
        minutes = (int)lTime/MILLISEC_IN_MINUTE; //Convert minutes
        seconds = (int)lTime%MILLISEC_IN_MINUTE/MILLISEC_IN_SEC; //Convert seconds
        strSeconds = (seconds < 10) ? "0" + seconds : "" + seconds; //Get string of seconds
        strMinutes = (minutes < 10) ? "0" + minutes : "" + minutes; //Get string of minutes
        mainTimer.setText("" + strMinutes + ":" + strSeconds); //Timer output
    }

    public void vibrate(){
        long vibratePattern[] = {10, 200, 50, 200, 50, 1000};
        vib.vibrate(vibratePattern, -1);
    }

}
