package com.polezin85.Timer;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import static com.polezin85.Timer.Constants.*;

/**
 * Created by User on 07.09.2017.
 */

public class Timer extends CountDownTimer {

    //Views
    protected TextView title; //Link to the title
    protected TextView timer; //Link to the timer
    protected Button button; //Link to the start button
    protected TextView pomodoroCounterText; //Link to the pomodoro counter text

    //System
    protected Vibrator vibrator;

    private int minutes; //Calculated minutes
    private int seconds; //Calculated seconds
    private String strSeconds; //Seconds for output
    private String strMinutes; //Minutes for output
    public boolean isBigCircle; //If 3 or 4 circles end
    public int pomodoroCounter; //Counts the pomodoro circles


    public Timer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        isBigCircle = false;
    }

    public void defineTitle(TextView title){
        this.title = title;
    }

    public void defineTimer(TextView timer){
        this.timer = timer;
    }

    public void defineStartButton(Button button){
        this.button = button;
    }

    public void definePomodoroCounter(TextView pomodoroCounterText){
        this.pomodoroCounterText = pomodoroCounterText;
    }

    public void defineVibrator(Context context){
        vibrator = context.getSystemService(Vibrator.class);
    }

    protected void setTitle(String title){
        this.title.setText(title);
    }

    public void setTimer(String timer){
        this.timer.setText(timer);
    }

    public void setPomodoroCounter(String pomodoroCounterText){
        this.pomodoroCounterText.setText(pomodoroCounterText);
    }

    protected void resetStartButton(){
        this.button.setClickable(true); //Unlock button
        this.button.setTextColor(Color.rgb(0,0,0)); //Change colour of button text
    }

    protected void vibrate(){
        long vibratePattern[] = {10, 200, 50, 200, 50, 1000};
        vibrator.vibrate(vibratePattern, -1);
    }

    @Override
    public void onTick(long l) {
        calculateTimer(l);
        drawTimer();
    }

    @Override
    public void onFinish() {

    }

    private void calculateTimer(long lTime){
        minutes = (int)lTime/MILLISEC_IN_MINUTE; //Convert minutes
        seconds = (int)lTime%MILLISEC_IN_MINUTE/MILLISEC_IN_SEC; //Convert seconds
        strSeconds = (seconds < 10) ? "0" + seconds : "" + seconds; //Get string of seconds
        strMinutes = (minutes < 10) ? "0" + minutes : "" + minutes; //Get string of minutes
    }

    private void drawTimer(){
        timer.setText("" + strMinutes + ":" + strSeconds); //Timer output
    }
}
