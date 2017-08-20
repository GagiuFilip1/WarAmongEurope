package com.mygdx.ww2.dinamicObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private float HP, SPEED, DAMAGE;
    public final GameScreen instance;
    private float range;
    private boolean DIE = false;
    public Vector2 position;
    public int getType;
    private Sprite soldierSprite;


    public Soldier(GameScreen setInstance) {
        soldierSprite = new Sprite(Main.REFERENCE.registry.retrieveTextures(0));
        // set the soldier type to neutral
        range = setNewRange();
        getType = Main.REFERENCE.constants.nullified;
        //set a new timer for attack
        cooldown = new SimpleTimer();
        cooldown.setNewTime(Main.REFERENCE.constants.soldierAttackTime);
        position = new Vector2();
        //set the instance where this object is added
        instance = setInstance;

        //set the basic for this unit
        HP = Main.REFERENCE.constants.soldierHP;
        SPEED = Main.REFERENCE.constants.soldierSpeed;
        DAMAGE = Main.REFERENCE.constants.soldierDamange;

    }

    @Override
    public void Move() {
        // Move to the range of the attack with the nearest enemy;
        // TO DO : make soldiers to not overlap
        if (isLeft && lowestDistance >= range && lowestDistance != Integer.MAX_VALUE) {
            position.x -= Gdx.graphics.getDeltaTime() * Main.REFERENCE.constants.soldierSpeed;
        }
        if (!isLeft && lowestDistance >= range && lowestDistance != Integer.MAX_VALUE) {
            position.x += Gdx.graphics.getDeltaTime() * Main.REFERENCE.constants.soldierSpeed;
        }
    }

    @Override
    public void Shoot() {
        //when cooldown turns down add a bullet to current instance, shooted by this player
        if (cooldown.isTimeElapsed()) {
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
        if (DIE) instance.spawnedObjects.remove(this);
    }

    @Override
    public void draw() {
      /*
        Main.REFERENCE.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.REFERENCE.shapeRenderer.setColor(Color.BLUE);
        Main.REFERENCE.shapeRenderer.rect(position.x, position.y,12 , 100);
        Main.REFERENCE.shapeRenderer.end();
        */

        // TO DO...ADD FUCKING TEXTURES
        Main.REFERENCE.batch.begin();
        Main.REFERENCE.batch.draw(soldierSprite, position.x,position.y,40,100);
        Main.REFERENCE.batch.end();
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
        Shoot();

        // die if hp < 0
        Die();
    }

    private boolean isLeft = false;
    private float lowestDistance = Integer.MAX_VALUE;
    private Soldier target;
    private void getLowestDistanceToEnemy(Soldier thisNpc, Soldier enemyNpc)
    {
        //get the nearsset enemy to this soldier
        float newDistance = Math.abs(thisNpc.position.x - enemyNpc.position.x);
        if(newDistance < lowestDistance && enemyNpc.getType != getType)
        {
            lowestDistance = newDistance;
            boolean isLeftSeter = thisNpc.position.x - enemyNpc.position.x > Main.REFERENCE.constants.nullified;
            target = enemyNpc;
            isLeft = isLeftSeter;
            if(isLeft)
                soldierSprite.flip(true,false);
        }
    }

    private boolean isInRange(Soldier thisNpc, Soldier enemyNpc)
    {
        return Math.abs(thisNpc.position.x - enemyNpc.position.x) < range;
    }

    private float setNewRange()
    {
        return ThreadLocalRandom.current().nextInt((int)(Main.REFERENCE.constants.soldierRange - Main.REFERENCE.constants.distanceToGun), (int)(Main.REFERENCE.constants.soldierRange + Main.REFERENCE.constants.distanceToGun) + 1);
    }
}

