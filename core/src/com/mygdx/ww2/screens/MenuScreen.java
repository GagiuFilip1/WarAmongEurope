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
public class MenuScreen extends ScreenType
{
    private LinkedList<ScreenObject> thisObjects;

    public MenuScreen()
    {
        thisObjects = new LinkedList<ScreenObject>();

        //set this screen objects
        setObjects();

        // the screen will get all the objects of this instance and will draw it(only for static and start objects)
        super.setAssets(this, thisObjects);
    }

    public void setObjects()
    {

    }

    public void drawThis()
    {
        //Draw InGameObjects
        super.draw();

        //Draw any other stuff that isn't a object already defined like a sprite or a text

        Main.reference.batch.begin();
        Main.reference.fontDrawer.draw(Main.reference.batch ,Main.reference.constants.MENUDATA, Gdx.graphics.getWidth() /2.4f, Gdx.graphics.getHeight()/2);
        Main.reference.batch.end();
    }

    public void updateThis()
    {
        //update InGameObjects
        super.update();

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
