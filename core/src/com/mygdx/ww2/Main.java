package com.mygdx.ww2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.ww2.Utils.Constants;
import com.mygdx.ww2.Utils.GameRegistry;
import com.mygdx.ww2.managers.ScreenManager;

public class Main extends ApplicationAdapter {
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
    public BitmapFont fontDrawer;
	public ScreenManager screenManager;
	public Constants constants;
	public GameRegistry registry;
	public static Main reference;
    public OrthographicCamera camera;

	@Override
	public void create () {

		reference = this;
		batch = new SpriteBatch();
		screenManager = new ScreenManager();
		shapeRenderer = new ShapeRenderer();
		fontDrawer = new BitmapFont();
		constants = new Constants();
		registry = new GameRegistry();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screenManager.drawScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		screenManager.dispose();
		fontDrawer.dispose();
		registry.dispose();
	}

}
