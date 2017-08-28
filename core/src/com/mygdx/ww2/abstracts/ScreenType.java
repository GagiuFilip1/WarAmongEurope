package com.mygdx.ww2.abstracts;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ww2.Main;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public abstract class ScreenType
{

    private Queue<ScreenObject> objects = new ConcurrentLinkedQueue<ScreenObject>();
    private Queue<ScreenObject> screenTextures = new ConcurrentLinkedQueue<ScreenObject>();
    protected void setAssets(ScreenType sc , Queue<ScreenObject> assets)
    {
        sc.objects = assets;
    }

    protected void setAssets(Queue<Texture> assets)
    {

    }

    protected void draw()
    {
        if(objects.size() > Main.reference.constants.nullified) {
            for (ScreenObject obj : objects) {
                obj.draw();
            }
        }
    }

    protected void update()
    {
        if(objects.size() > Main.reference.constants.nullified) {
            for (ScreenObject obj : objects) {
                obj.update();
            }
        }
    }

    public abstract void finalize();
}
