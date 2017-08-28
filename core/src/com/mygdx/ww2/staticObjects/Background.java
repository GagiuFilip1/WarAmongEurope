package com.mygdx.ww2.staticObjects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.Utils.ParallaxLoop;
import com.mygdx.ww2.abstracts.ScreenObject;

/**
 * Created by Gagiu Filip on 8/28/2017.
 */
public class Background extends ScreenObject
{
    private ParallaxLoop backgroundParallax;

    @Override
    public void draw() {
        //draw the specifc loop for this object
        backgroundParallax.draw(Main.reference.camera.position , Main.reference.batch);
    }

    @Override
    public void update() {

    }

    public void setBackgroundParallax(Texture texture)
    {
        //set this background texture
        backgroundParallax = new ParallaxLoop(0,0,0.5f, texture);
    }

}
