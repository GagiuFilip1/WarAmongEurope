package com.mygdx.ww2.abstracts;


import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public abstract class ScreenType
{

    private LinkedList<ObjectType> objects = new LinkedList<ObjectType>();
    private LinkedList<UnitType> units = new LinkedList<UnitType>();
    protected void setAssets(ScreenType sc , LinkedList<ObjectType> assets)
    {
        sc.objects = assets;
    }

    public void draw()
    {
        if(objects.size() > 0) {
            for (ObjectType obj : objects) {
                obj.draw();
            }
            for (UnitType unit : units) {
                unit.draw();
            }
        }
    }

    public void update()
    {
        if(objects.size() > 0) {
            for (ObjectType obj : objects) {
                obj.update();
            }
            for (UnitType unit : units) {
                unit.draw();
                unit.update();
            }
        }
    }

    public abstract void finalize();
}
