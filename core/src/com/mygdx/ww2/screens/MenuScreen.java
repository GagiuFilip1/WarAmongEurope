package com.mygdx.ww2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ObjectType;
import com.mygdx.ww2.abstracts.ScreenType;

import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class MenuScreen extends ScreenType
{
    private LinkedList<ObjectType> thisObjects;
    private BitmapFont fontDrawer = new BitmapFont();

    public MenuScreen()
    {
        thisObjects = new LinkedList<ObjectType>();

        //set this screen objects
        setObjects();

        // the screen will get all the objects of this instance and will draw it
        super.setAssets( this, thisObjects);
    }

    public void setObjects()
    {
        super.setAssets(this, thisObjects);
    }

    public void drawThis()
    {
        //Draw InGameObjects
        super.draw();

        //Draw any other stuff that isn't a object already defined like a sprite or a text

        Main.REFERENCE.batch.begin();
        fontDrawer.draw(Main.REFERENCE.batch ,Main.REFERENCE.constants.MENUDATA, Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2);
        Main.REFERENCE.batch.end();
    }

    public void updateThis()
    {
        //update InGameObjects
        super.update();

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
