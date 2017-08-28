package com.mygdx.ww2.staticObjects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.Utils.ParallaxLoop;
import com.mygdx.ww2.abstracts.ScreenObject;

/**
 * Created by Gagiu Filip on 8/28/2017.
 */
public class Platform extends ScreenObject
{
    public ParallaxLoop platformParallax;

    @Override
    public void draw() {
        //draw the specifc loop for this object
        platformParallax.draw(Main.reference.camera.position , Main.reference.batch);
    }

    @Override
    public void update() {
        // nothing to update
    }

    public void setBackgroundParallax(Texture texture)
    {
        //set this paltform texture
        platformParallax = new ParallaxLoop(0,0,0f, texture);
    }
}
