package com.mygdx.ww2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ww2.AI.PlayerAI;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.abstracts.ScreenType;
import com.mygdx.ww2.staticObjects.Background;
import com.mygdx.ww2.staticObjects.Platform;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class GameScreen extends ScreenType
{
    public Queue<ScreenObject> thisObjects;
    public PlayerAI playerAI;
    //public LinkedList<ScreenObject> spawnedObjects;
     public Queue<ScreenObject> spawnedObjects;

    GameScreen()
    {
        thisObjects = new ConcurrentLinkedQueue<ScreenObject>();
        spawnedObjects = new ConcurrentLinkedQueue<ScreenObject>();

        //the logic for player
        playerAI = new PlayerAI(this);

        //set this screen objects
        setObjects();

        // the screen will get all the objects of this instance and will draw it
        super.setAssets( this, thisObjects);
    }

    public Platform platform;
    private void setObjects()
    {
        Background background = new Background();
        background.setBackgroundParallax(new Texture(Gdx.files.internal("backgrounds/world.jpg")));
        platform = new Platform();
        platform.setBackgroundParallax(new Texture(Gdx.files.internal("platforms/testPlatform.png")));
        thisObjects.add(background);
        thisObjects.add(platform);
    }

    private void drawThis()
    {
        //Draw InGameObjects , we must draw first the static objects
        super.draw();

        // draw spawned objects like units or bulets;

        try {
            if (spawnedObjects.size() > Main.reference.constants.nullified)
                for (ScreenObject spawned : spawnedObjects) {
                    spawned.draw();
                }
        }catch (Exception ignore) {
            //Just ignore It
        }


        //Draw any other stuff that isn't a object already defined like a sprite or a text
        Main.reference.batch.begin();
        Main.reference.fontDrawer.setColor(Color.GOLD);
        Main.reference.fontDrawer.draw(Main.reference.batch,"Money : " + String.valueOf(playerAI.money),Main.reference.camera.position.x, (float) (0.98 * Gdx.graphics.getHeight()));
        Main.reference.batch.end();

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
            Main.reference.screenManager.setScreen(new GameScreen());
    }

    @Override
    public void finalize() {

        //update and draw InGameObjects and the other stuff u wanted to add

        drawThis();
        updateThis();
    }
}
