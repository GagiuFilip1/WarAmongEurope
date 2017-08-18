package com.mygdx.ww2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.abstracts.ScreenType;

import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class GameScreen extends ScreenType
{
    private LinkedList<ScreenObject> thisObjects;

    public LinkedList<ScreenObject> spawnedObjects;

    GameScreen()
    {
        thisObjects = new LinkedList<ScreenObject>();

        //set this screen objects
        setObjects();

        // the screen will get all the objects of this instance and will draw it
        super.setAssets( this, thisObjects);
    }

    private void setObjects()
    {
        thisObjects.add(null);
        thisObjects.removeLast();
        /*
       TO DO:
       1)ADD PLATOFRM
       2)ADD BACKGROUND
       3)THINK OF A WAY TO SET ENEMYS
       4)ADD AI MODULE
       5)ADD PLAYER MODULE
        */
    }

    private void drawThis()
    {
        //Draw InGameObjects , we must draw first the static objects
        super.draw();

        // draw spawned objects like units or bulets;
        if(spawnedObjects.size() > Main.REFERENCE.constants.nullified)
        for(ScreenObject spawned : spawnedObjects)
        {
            spawned.draw();
        }


        //Draw any other stuff that isn't a object already defined like a sprite or a text
        Main.REFERENCE.batch.begin();
        Main.REFERENCE.fontDrawer.draw(Main.REFERENCE.batch ,Main.REFERENCE.constants.MENUDATA, Gdx.graphics.getWidth() /2.4f, Gdx.graphics.getHeight()/2);
        Main.REFERENCE.batch.end();
    }

    private void updateThis()
    {
        //update InGameObjects, we must update them first because they contains stuff like background platform static objects
        super.update();

        //update spawned objects like unts bullets and any other stuf like this
        if(spawnedObjects.size() > 0)
            for(ScreenObject spawned : spawnedObjects)
            {
                spawned.update();
            }


        //any other actions that happens in this screen
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            Main.REFERENCE.screenManager.setScreen(new GameScreen());
    }

    @Override
    public void finalize() {

        //update and draw InGameObjects and the other stuff u wanted to add

        drawThis();
        updateThis();
    }
}
