package com.mygdx.ww2.dinamicObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.abstracts.UnitObject;
import com.mygdx.ww2.screens.GameScreen;
import com.mygdx.ww2.staticObjects.House;

/**
 * Created by Gagiu Filip on 8/21/2017.
 */
public class Worker extends UnitObject {

    private final GameScreen instace;
    private Sprite workerSprite;
    private Vector2 position;
    public Worker(GameScreen getInstace) {
        instace = getInstace;
        //vector of this character position
        position = new Vector2();
        //get the texture of this unit
        workerSprite = new Sprite(Main.reference.registry.retrieveTextures(2));
    }

    //to check if this unit is binded to a house or not
    public boolean binded = false;

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }

    @Override
    public void Move() {
        if (isLeft && lowestDistance != Integer.MAX_VALUE) {
            position.x -= Gdx.graphics.getDeltaTime() * Main.reference.constants.soldierSpeed;
        }
        if (!isLeft && lowestDistance != Integer.MAX_VALUE) {
            position.x += Gdx.graphics.getDeltaTime() * Main.reference.constants.soldierSpeed;
        }
    }

    @Override
    public void Action() {
        getNearestHouse();
    }

    @Override
    public void Die() {

    }


    public void farmMoney(House house) {
        //farm money from a house
        house.money -= Main.reference.constants.workCapacity;
        house.instance.playerAI.money += Main.reference.constants.workCapacity;
    }

    //bind and unbind from a house
    public void bind() {
        binded = true;
    }

    public void unbind() {
        binded = false;
    }

    private void getNearestHouse() {
        for (ScreenObject house : instace.thisObjects) {
            try {
                House thisHouse = (House) (house);
                getLowestDistanceToHouse(this,thisHouse);
            } catch (Exception ignore) {
                // not all screenobjects are houses
            }
        }
    }

    private boolean isLeft = false;
    private float lowestDistance = Integer.MAX_VALUE;
    private House target;
    private void getLowestDistanceToHouse(Worker thisNpc, House house)
    {
        //get the nearsset enemy to a house
        float newDistance = Math.abs(thisNpc.position.x - house.position.x);
        if(newDistance < lowestDistance)
        {
            lowestDistance = newDistance;
            boolean isLeftSeter = thisNpc.position.x - house.position.x > Main.reference.constants.nullified;
            target = house;
            isLeft = isLeftSeter;
            if(isLeft)
                workerSprite.setFlip(true,false);
            else
                workerSprite.setFlip(false,false);
        }
        if(target.wastedMoney)lowestDistance = Integer.MAX_VALUE;
    }
}
