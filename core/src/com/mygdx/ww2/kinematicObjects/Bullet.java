package com.mygdx.ww2.kinematicObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.dinamicObjects.Soldier;

/**
 * Created by Gagiu Filip on 8/17/2017.
 */
public class Bullet extends ScreenObject
{
    private Vector2 position;
    private float traveled;
    private final Soldier shooter;
    public int moveIndex = 0;

    //----Test Timy------//
    public Bullet(Soldier getshooter)
    {
        shooter = getshooter;
        // get the shooter position
        position = new Vector2();
        position.set(shooter.position.x,shooter.position.y + Main.reference.constants.distanceToGun);
        System.out.println(position.x + " " + position.y);
    }

    @Override
    public void draw() {
        //draw smth for testing
        Main.reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.reference.shapeRenderer.setColor(Color.GOLD);
        Main.reference.shapeRenderer.rect(position.x,position.y,6,2);
        Main.reference.shapeRenderer.end();
    }

    @Override
    public void update() {
        //basic runtime for the bullet
        Move(moveIndex);
        destroy();
    }

    private void Move(int type)
    {
        //move left or right depends og the type
        //the type must be chosen by the way the soldier is facing left or right
        switch (type)
        {
            case -1:
                position.x += Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                traveled += Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                break;
            case 1 :
                position.x -= Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                traveled += Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                break;
            default:  position.x += Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                traveled += Gdx.graphics.getDeltaTime()* Main.reference.constants.bulletSpeed;
                break;
        }
    }

    private void destroy()
    {
        // if the bullet traveed more than the screen width destroy in the current instance
        if(traveled >= Gdx.graphics.getWidth())
            shooter.instance.spawnedObjects.remove(this);
        checkColisionwithOther();
    }

    private void checkColisionwithOther()
    {
        for(ScreenObject enemy : shooter.instance.spawnedObjects)
        {
            //type cate the atribute of enemy for every single spawned object
            //if the object is a enemy and the bullet is in his colision box destroy it
            try {
                if (enemy != shooter) {
                    Soldier setEnemy = ((Soldier) (enemy));
                    if (shooter.getType != setEnemy.getType)
                    {
                        if(setEnemy.position.x - Main.reference.constants.soldierBodyBorder < position.x)
                            if(setEnemy.position.x + Main.reference.constants.soldierBodyBorder > position.x) {
                                shooter.instance.spawnedObjects.remove(this);
                                shooter.target.HP -= Main.reference.constants.soldierDamange;
                            }
                    }
                }
            }
            catch (Exception ignore) {
                // not all objects are soldiers
            }
        }
    }
}
