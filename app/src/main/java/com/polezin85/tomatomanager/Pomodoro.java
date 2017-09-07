package com.polezin85.tomatomanager;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Andrew Polezin on 06.09.2017.
 */

public class Pomodoro {

    private static final int POMODORO_WORK_INTERVAL = 25; //Minutes of work interval
    private static final int POMODORO_REST_INTERVAL = 5; //Minutes of rest
    private static final int POMODORO_BIG_REST_INTERVAL = 15; //Minutes of big rest interval after 3 or 4 circles
    private static final int MILLISEC_IN_SEC = 1000;
    private static final int MILLISEC_IN_MINUTE = 60000;
    private static final boolean ACTIVE_BUTTON = true; //If button START TIMER is active
    private static final boolean NOT_ACTIVE_BUTTON = false; //If button START TIMER is not active
    private static final String POMODORO_COUNT_END_TEXT = " 'pomodoro'"; //The end of count pomodoro string ("count" + 'pomodoros')
    private static final String START_WORK_TIME = "25:00"; //Start timer value when work interval
    private static final String START_REST_TIME = "05:00"; //Start timer value when rest interval
    private static final String START_BIG_REST_TIME = "15:00"; //Start timer value when big rest interval
    private static final String END_OF_TIME = "00:00";

    //System services
    Vibrator vib;

    private int minutes; //Calculated minutes
    private int seconds; //Calculated seconds
    private String strSeconds; //Seconds for output
    private String strMinutes; //Minutes for output
    private int pomodoroBigCircle = 0; //3 or 4 pomodoro circles
    private int pomodoroCount = 0; //Count pomodoro circles (after the last rest)

    //Constructor
    public Pomodoro(){

    }


}
