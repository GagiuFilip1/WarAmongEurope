package com.mygdx.ww2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.ww2.AI.PlayerAI;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.abstracts.ScreenType;
import com.mygdx.ww2.dinamicObjects.Soldier;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class GameScreen extends ScreenType
{
    private Queue<ScreenObject> thisObjects;
    private PlayerAI playerAI;
    //public LinkedList<ScreenObject> spawnedObjects;
     public Queue<ScreenObject> spawnedObjects;

    GameScreen()
    {
        thisObjects = new ConcurrentLinkedQueue<ScreenObject>();
        spawnedObjects = new ConcurrentLinkedQueue<ScreenObject>();
        //-------Test Purpose---------------------//
        Soldier soldier = new Soldier(this);
        soldier.position.x = Gdx.graphics.getWidth() - 100;
        soldier.position.y = 0;
        soldier.setType(2);
        //spawnedObjects.add(soldier);
        //--------Test Purpose----------------------//
        //the logic for player
        playerAI = new PlayerAI(this);

        //set this screen objects
        setObjects();

        // the screen will get all the objects of this instance and will draw it
        super.setAssets( this, thisObjects);
    }

    private void setObjects()
    {

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

        try {
            if (spawnedObjects.size() > Main.REFERENCE.constants.nullified)
                for (ScreenObject spawned : spawnedObjects) {
                    spawned.draw();
                }
        }catch (Exception ignore) {
            //Just ignore It
        }


        //Draw any other stuff that isn't a object already defined like a sprite or a text

    }

    private void updateThis()
    {
        //update InGameObjects, we must update them first because they contains stuff like background platform static objects
        super.update();
        playerAI.update();
        //update spawned objects like unts bullets and any other stuf like this
       try {
           if (spawnedObjects.size() > 0)
               for (ScreenObject spawned : spawnedObjects) {
                   spawned.update();
               }
       }catch (Exception ignore){
           //Just ignore it
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
