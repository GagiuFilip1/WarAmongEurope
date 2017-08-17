package com.mygdx.ww2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.ww2.Utils.Constants;
import com.mygdx.ww2.managers.ScreenManager;

public class Main extends ApplicationAdapter {
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public ScreenManager screenManager;
	public Constants constants;
	public static Main REFERENCE;

	@Override
	public void create () {

		REFERENCE = this;
		batch = new SpriteBatch();
		screenManager = new ScreenManager();
		shapeRenderer = new ShapeRenderer();
		constants = new Constants();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screenManager.drawScreen();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		screenManager.dispose();
	}

}
