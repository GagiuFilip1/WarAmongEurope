package com.mygdx.ww2.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.dinamicObjects.Soldier;
import com.mygdx.ww2.screens.GameScreen;

/**
 * Created by Gagiu Filip on 8/18/2017.
 */
public class PlayerAI extends ScreenObject {
    private final GameScreen instance;
    public float money = 500;

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
            newSoldier.position.set(Main.reference.constants.nullified, instance.platform.platformParallax.texture.getHeight());
            instance.spawnedObjects.add(newSoldier);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Soldier newSoldier = new Soldier(instance);
            newSoldier.setType(2);
            newSoldier.position.set(990, instance.platform.platformParallax.texture.getHeight());
            instance.spawnedObjects.add(newSoldier);
        }
        setCamera();
    }

    private void setCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if( Main.reference.camera.position.x  >= 0)
            Main.reference.camera.translate(-3, 0, 0);
            //If The left input is pressed move to left
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if( Main.reference.camera.position.x  <= 5000)
            Main.reference.camera.translate(3, 0, 0);
            //If The Right input is pressed move to Right
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) &&   Main.reference.camera.zoom < 1) {
            Main.reference.camera.zoom += 0.02;
            Main.reference.camera.translate(0, +5, 0);
            //If the A Key is pressed, add 0.02 to the Camera's Zoom
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) &&   Main.reference.camera.zoom > 0.5f) {
            Main.reference.camera.zoom -= 0.02;
            Main.reference.camera.translate(0, -5, 0);

            //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
        }
        resize(1366,768);
    }

    private void resize(int width, int height) {
        Main.reference.camera.viewportWidth = width;
        Main.reference.camera.viewportHeight = height;
    }
}
