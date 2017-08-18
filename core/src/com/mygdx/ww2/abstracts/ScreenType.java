package com.mygdx.ww2.abstracts;


import com.mygdx.ww2.Main;

import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public abstract class ScreenType
{

    private LinkedList<ScreenObject> objects = new LinkedList<ScreenObject>();
    private LinkedList<UnitObject> units = new LinkedList<UnitObject>();
    protected void setAssets(ScreenType sc , LinkedList<ScreenObject> assets)
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
