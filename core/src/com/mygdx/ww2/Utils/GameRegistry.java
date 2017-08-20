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
              texture.dispose();
    }

    public Texture retrieveTextures(int textureID) {
        //retrieve a texture by a id
        return gameTextures[textureID];
    }

    private void setGameTextures() {
        //here we add textures to our array
        gameTextures[0] = new Texture(Gdx.files.internal("units/soldier.png"));
    }
}
