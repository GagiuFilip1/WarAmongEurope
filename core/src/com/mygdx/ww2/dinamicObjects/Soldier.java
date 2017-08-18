package com.mygdx.ww2.dinamicObjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.Utils.SimpleTimer;
import com.mygdx.ww2.abstracts.UnitObject;
import com.mygdx.ww2.kinematicObjects.Bullet;
import com.mygdx.ww2.screens.GameScreen;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class Soldier extends UnitObject
{

    private SimpleTimer cooldown;
    private float HP,SPEED, DAMAGE;
    private final GameScreen instance;
    private boolean DIE = false;
    private Vector2 position;

    public Soldier(GameScreen setInstance)
    {
        //set a new timer for attack
        cooldown = new SimpleTimer();
        cooldown.setNewTime(Main.REFERENCE.constants.soldierAttackTime);

        //set the instance where this object is added
        instance = setInstance;

        //set the basic for this unit
        HP = Main.REFERENCE.constants.soldierHP;
        SPEED = Main.REFERENCE.constants.soldierSpeed;
        DAMAGE = Main.REFERENCE.constants.soldierDamange;
    }

    @Override
    public void Move() {
        // TO DO: move to a certain point and maybe just maybe try to not overlap too much with other soldiers;
    }

    @Override
    public void Shoot() {
        //when cooldown turns down add a bullet to current instance, shooted by this player
        if(cooldown.isTimeElapsed())
            instance.spawnedObjects.add(new Bullet(this));

    }

    @Override
    public void Die() {
        DIE = (HP <= 0);
    }

    @Override
    public void draw() {
        // TO DO...ADD FUCKING TEXTURES
    }

    @Override
    public void update() {
        if(!DIE) {
            Move();
            Shoot();
            Die();
        }
    }
}
