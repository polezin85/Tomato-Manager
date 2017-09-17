package com.polezin85.Timer;

import static com.polezin85.Timer.Constants.*;

/**
 * Created by User on 07.09.2017.
 */

public class WorkTimer extends Timer {

    public WorkTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish(){
        setTitle(TEXT_TITLE_REST);
        if(isBigCircle) {
            setTimer(START_BIG_REST_TIME);
            isBigCircle = false;
        }else
            setTimer(START_REST_TIME);
        resetStartButton();
        vibrate();
    }

}
