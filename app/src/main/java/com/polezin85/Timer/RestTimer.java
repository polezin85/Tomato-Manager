package com.polezin85.Timer;

import static com.polezin85.Timer.Constants.*;

/**
 * Created by User on 07.09.2017.
 */

public class RestTimer extends Timer {

    public RestTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish(){
        setTitle(TEXT_TITLE_WORK);
        setTimer(START_WORK_TIME);
        resetStartButton();
        setPomodoroCounter(pomodoroCounter + POMODORO_COUNT_END_TEXT);
        vibrate();
    }
}
