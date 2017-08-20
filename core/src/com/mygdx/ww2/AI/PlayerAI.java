package com.mygdx.ww2.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.dinamicObjects.Soldier;
import com.mygdx.ww2.screens.GameScreen;

/**
 * Created by Gagiu Filip on 8/18/2017.
 */
public class PlayerAI extends ScreenObject {
    private final GameScreen instance;

    public PlayerAI(GameScreen getInstance) {
        //get the instance of the played screen game
        instance = getInstance;
    }

    @Override
    public void draw() {
        /*
        TO DO : it would be a nice and clean idea to put here stuff as player name , level and other shit like this
         */
    }

    @Override
    public void update() {
        setControls();
    }

    private void setControls() {
        /*
         TO DO: this controls must be changed with buttons for android
         Hint to me to don't forget : try to make a function that gets a functions as a parameter
         */

        //Add a new unit to the instance to be drawed and updated
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Soldier newSoldier = new Soldier(instance);
            newSoldier.setType(1);
            newSoldier.position.set(0, 0);
            instance.spawnedObjects.add(newSoldier);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Soldier newSoldier = new Soldier(instance);
            newSoldier.setType(2);
            newSoldier.position.set(900, 0);
            instance.spawnedObjects.add(newSoldier);
        }
        setCamera();
    }

    private void setCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if( Main.REFERENCE.camera.position.x  >= 0)
            Main.REFERENCE.camera.translate(-3, 0, 0);
            //If The left input is pressed move to left
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if( Main.REFERENCE.camera.position.x  <= 5000)
            Main.REFERENCE.camera.translate(3, 0, 0);
            //If The Right input is pressed move to Right
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) &&   Main.REFERENCE.camera.zoom < 1) {
            Main.REFERENCE.camera.zoom += 0.02;
            Main.REFERENCE.camera.translate(0, +10, 0);
            //If the A Key is pressed, add 0.02 to the Camera's Zoom
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) &&   Main.REFERENCE.camera.zoom > 0.5f) {
            Main.REFERENCE.camera.zoom -= 0.02;
            Main.REFERENCE.camera.translate(0, -10, 0);

            //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
        }
        resize(1366,768);
    }

    private void resize(int width, int height) {
        Main.REFERENCE.camera.viewportWidth = width;
        Main.REFERENCE.camera.viewportHeight = height;
    }
}
