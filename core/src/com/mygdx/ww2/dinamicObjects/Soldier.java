package com.mygdx.ww2.dinamicObjects;

import com.mygdx.ww2.abstracts.ObjectType;
import com.mygdx.ww2.abstracts.UnitType;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class Soldier extends UnitType
{
    @Override
    public void Move() {

    }

    @Override
    public void Shoot() {

    }

    @Override
    public void Die() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {
        Move();
        Shoot();
        Die();
    }
}
