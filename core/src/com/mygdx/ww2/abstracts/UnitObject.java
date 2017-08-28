package com.mygdx.ww2.abstracts;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public abstract class UnitObject extends ScreenObject
{
    public abstract void Move();
    public abstract void Action();
    public abstract void Die();

}
