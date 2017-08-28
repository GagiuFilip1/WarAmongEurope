package com.mygdx.ww2.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.ww2.Main;

/**
 * Created by Gagiu Filip on 8/28/2017.
 */
public class ParallaxLoop {
    // this is a parralax object
    // parallax object have different speed upon camera movement
    // to create a parallax object we need a parallax loop

    private int x, y, width, items;
    private float factor;
    public Texture texture;

    public ParallaxLoop(int _x, int _y, float _factor, Texture _texture) {
        //set the position
        x = _x;
        y = _y;

        //how fast should this item move
        factor = _factor;
        texture = _texture;
        width = _texture.getWidth();

        //modify upon resize
        items = 1366 / width + 1;
        if (1366 % width > 0)
            items++;
    }

    public void draw(Vector3 camerapos, SpriteBatch batch) {
        //draw algorithm

        if ((x + (int) (camerapos.x * factor) + width) < (camerapos.x - Gdx.graphics.getWidth() / 2))
            x += width;
        if ((x + (int) (camerapos.x * factor)) > (camerapos.x - Gdx.graphics.getWidth() / 2))
            x -= width;

        for (int i = 0; i < items; i++) {
            Main.reference.batch.begin();
            batch.draw(texture, x + (int) (camerapos.x * factor) + (width * i), y);
            Main.reference.batch.end();
        }
    }

}