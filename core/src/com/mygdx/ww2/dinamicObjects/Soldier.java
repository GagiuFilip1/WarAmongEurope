package com.mygdx.ww2.dinamicObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.Utils.SimpleTimer;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.abstracts.UnitObject;
import com.mygdx.ww2.kinematicObjects.Bullet;
import com.mygdx.ww2.screens.GameScreen;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class Soldier extends UnitObject {

    private SimpleTimer cooldown;
    public float HP;
    public final GameScreen instance;
    private float range;
    public boolean DIE = false;
    public Vector2 position;
    public int getType;
    private Sprite soldierSprite;

    public Soldier(GameScreen setInstance) {
        soldierSprite = new Sprite(Main.reference.registry.retrieveTextures(0));

        // set the soldier type to neutral
        range = setNewRange();
        getType = Main.reference.constants.nullified;

        //set a new timer for attack
        cooldown = new SimpleTimer();
        cooldown.setNewTime(Main.reference.constants.soldierAttackTime);

        //set the instance where this object is added
        instance = setInstance;

        //set the basic for this unit
        HP = Main.reference.constants.soldierHP;

        position = new Vector2();
        //set the postion of this unit above the platform

    }

    @Override
    public void Move() {
        // Move to the range of the attack with the nearest enemy;
        if (isLeft && lowestDistance >= range && lowestDistance != Integer.MAX_VALUE) {
            position.x -= Gdx.graphics.getDeltaTime() * Main.reference.constants.soldierSpeed;
        }
        if (!isLeft && lowestDistance >= range && lowestDistance != Integer.MAX_VALUE) {
            position.x += Gdx.graphics.getDeltaTime() * Main.reference.constants.soldierSpeed;
        }
    }

    @Override
    public void Action() {
        //when cooldown turns down add a bullet to current instance, shooted by this player
        if (cooldown.isTimeElapsed() && !target.DIE) {
            Bullet bullet = new Bullet(this);
            // if moveindex == - 1 it will move to left else to right
            if(isLeft)
                bullet.moveIndex = 1;
            else bullet.moveIndex = -1;
            instance.spawnedObjects.add(bullet);
        }
    }

    @Override
    public void Die() {
        DIE = (HP <= 0);
        if (DIE)
            instance.spawnedObjects.remove(this);
    }

    @Override
    public void draw() {
        //draw this unit texture
        Main.reference.batch.begin();
        Main.reference.batch.draw(soldierSprite, position.x,position.y,40,100);
        Main.reference.batch.end();
    }

    @Override
    public void update() {
        //Basic Runtime for this unit
        if (!DIE) {
            loadLogic();
        }
    }

    public void setType(int setType) {
        //set the team where this soldier belongs to
        getType = setType;
    }

    private void loadLogic() {
        // set the logi of this npc(where to shoot where to move)
        for (ScreenObject soldier : instance.spawnedObjects) {
            try {
                Soldier otherSoldier = ((Soldier) (soldier));
                if (otherSoldier != this) {
                    getLowestDistanceToEnemy(this, otherSoldier);
                }
            } catch (Exception ignore) {
                // not all spawned objects are soldiers
            }
        }
        //move this npc
        Move();

        //shot if is in range with its target
        if(isInRange(this, target))
        Action();

        // die if hp < 0
       // Die();
    }

    private boolean isLeft = false;
    private float lowestDistance = Integer.MAX_VALUE;
    public Soldier target;
    private void getLowestDistanceToEnemy(Soldier thisNpc, Soldier enemyNpc)
    {
        //get the nearsset enemy to this soldier
        float newDistance = Math.abs(thisNpc.position.x - enemyNpc.position.x);
        if(newDistance < lowestDistance && enemyNpc.getType != getType)
        {
            lowestDistance = newDistance;
            boolean isLeftSeter = thisNpc.position.x - enemyNpc.position.x > Main.reference.constants.nullified;
            target = enemyNpc;
            isLeft = isLeftSeter;
            if(isLeft)
                soldierSprite.setFlip(true,false);
            else
                soldierSprite.setFlip(false,false);
        }
        if(target.DIE)lowestDistance = Integer.MAX_VALUE;
    }

    private boolean isInRange(Soldier thisNpc, Soldier enemyNpc)
    {
        return Math.abs(thisNpc.position.x - enemyNpc.position.x) < range;
    }

    private float setNewRange()
    {
        return ThreadLocalRandom.current().nextInt((int)(Main.reference.constants.soldierRange - Main.reference.constants.distanceToGun), (int)(Main.reference.constants.soldierRange + Main.reference.constants.distanceToGun) + 1);
    }
}

