package com.polezin85.Timer;

/**
 * Created by User on 07.09.2017.
 */

public final class Constants {

    public static final float POMODORO_WORK_INTERVAL = 25; //Minutes of work interval
    public static final float POMODORO_REST_INTERVAL = 5; //Minutes of rest
    public static final float POMODORO_BIG_REST_INTERVAL = 15; //Minutes of big rest interval after 3 or 4 circles
    public static final String START_WORK_TIME = "25:00"; //Start timer value when work interval
    public static final String START_REST_TIME = "05:00"; //Start timer value when rest interval
    public static final String START_BIG_REST_TIME = "15:00"; //Start timer value when big rest interval
    public static final String TEXT_TITLE_WORK = "Time to work";
    public static final String TEXT_TITLE_REST = "Time for the rest";
    public static final String POMODORO_COUNT_END_TEXT = " 'pomodoro'"; //The end of count pomodoro string ("count" + 'pomodoros')

    public static final int MILLISEC_IN_SEC = 1000;
    public static final int MILLISEC_IN_MINUTE = 60000;

    public static final int POMODORO_BIG_CIRCLE = 3;

}
