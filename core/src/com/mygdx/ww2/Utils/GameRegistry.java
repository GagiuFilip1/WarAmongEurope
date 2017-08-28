package com.mygdx.ww2.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gagiu Filip on 8/20/2017.
 */
public class GameRegistry {
    public Texture[] gameTextures;

    public GameRegistry() {
        //this class is for storing textures and dispose them
        gameTextures = new Texture[20];
        setGameTextures();
    }

    public void dispose() {
        //dispose each texture
        for (Texture texture : gameTextures)
            try {
                texture.dispose();
            }catch (Exception ex){}
    }

    public Texture retrieveTextures(int textureID) {
        //retrieve a texture by a id
        return gameTextures[textureID];
    }

    private void setGameTextures() {
        //here we add textures to our array

        //the texture for the soldier
        gameTextures[0] = new Texture(Gdx.files.internal("units/soldier.png"));
        //the texture for the house
        gameTextures[1] = new Texture(Gdx.files.internal("buildings/house.png"));
        //the texture for the worker
        gameTextures[2] = new Texture(Gdx.files.internal("units/worker.png"));
    }
}
