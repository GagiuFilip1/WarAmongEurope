package com.mygdx.ww2.abstracts;


import com.mygdx.ww2.Main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public abstract class ScreenType
{

    private Queue<ScreenObject> objects = new ConcurrentLinkedQueue<ScreenObject>();
    private Queue<UnitObject> units = new ConcurrentLinkedQueue<UnitObject>();
    protected void setAssets(ScreenType sc , Queue<ScreenObject> assets)
    {
        sc.objects = assets;
    }

    public void draw()
    {
        if(objects.size() > Main.REFERENCE.constants.nullified) {
            for (ScreenObject obj : objects) {
                obj.draw();
            }
            for (UnitObject unit : units) {
                unit.draw();
            }
        }
    }

    public void update()
    {
        if(objects.size() > Main.REFERENCE.constants.nullified) {
            for (ScreenObject obj : objects) {
                obj.update();
            }
            for (UnitObject unit : units) {
                unit.draw();
                unit.update();
            }
        }
    }

    public abstract void finalize();
}
