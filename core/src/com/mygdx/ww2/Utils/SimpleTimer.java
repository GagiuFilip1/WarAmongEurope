package com.mygdx.ww2.Utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by Gagiu Filip on 8/18/2017.
 */
public class SimpleTimer {

    private float counter = 0.0f , total;

    public boolean isTimeElapsed()
    {
        counter+= Gdx.graphics.getDeltaTime();
        if(counter > total)
        {
            //this line reset the timer
            counter  = 0.0f;
            return  true;
        }
        return false;
    }

    public void setNewTime(float newTime)
    {
        total = newTime;
    }
}
