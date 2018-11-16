package com.dockysoft.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Game extends ApplicationAdapter {
    OrthographicCamera camera;
    ExtendViewport viewport;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
	    camera = new OrthographicCamera();
	    viewport = new ExtendViewport(1920.0f, 1280.0f, camera);

		batch = new SpriteBatch();
		img = new Texture("smile.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 10, 0);
		batch.end();
	}

	@Override
    public void resize(int width, int height){
	    viewport.update(width, height, true);
	    batch.setProjectionMatrix(camera.combined);
    }
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
