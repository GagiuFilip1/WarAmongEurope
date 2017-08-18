package com.mygdx.ww2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.ww2.Utils.Constants;
import com.mygdx.ww2.managers.ScreenManager;

import java.security.Key;

public class Main extends ApplicationAdapter {
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
    public BitmapFont fontDrawer;
	public ScreenManager screenManager;
	public Constants constants;
	public static Main REFERENCE;


	@Override
	public void create () {

		REFERENCE = this;
		batch = new SpriteBatch();
		screenManager = new ScreenManager();
		shapeRenderer = new ShapeRenderer();
		fontDrawer = new BitmapFont();
		constants = new Constants();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screenManager.drawScreen();

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		screenManager.dispose();
		fontDrawer.dispose();
	}

}
