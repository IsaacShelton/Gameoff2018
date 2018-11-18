package com.dockysoft.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private SpriteBatch batch;
    private Texture img, ground, sky;

    private static final int FRAME_COLS = 6, FRAME_ROWS = 5;
    private Animation<TextureRegion> walkAnimation;
    private Texture walkSheet;

    float stateTime;

	private Person person;

	@Override
	public void create () {
	    camera = new OrthographicCamera();
	    viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

		batch = new SpriteBatch();
		img = new Texture("smile.jpg");
        ground = new Texture("ground.png");
        sky = new Texture("sky.jpg");
        walkSheet = new Texture(Gdx.files.internal("sprite-animation.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        stateTime = 0f;
        person = new Person(200.0f, 196.0f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime() * Math.abs(person.getDX() * 0.1);

        boolean sprint = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            person.move(4.0f, Gdx.graphics.getDeltaTime(), sprint);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            person.move(-4.0f, Gdx.graphics.getDeltaTime(), sprint);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            person.jump(0, (double)person.getDX(), Gdx.graphics.getFramesPerSecond());
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            person.flydown();


        person.update();
        person.clamp(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.begin();
        batch.draw(sky, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(ground, 0, 200.0f - ground.getHeight() / 2, ground.getWidth() / 2, ground.getHeight() / 2);
        batch.draw(ground, ground.getWidth() / 2, 200.0f - ground.getHeight() / 2, ground.getWidth() / 2, ground.getHeight() / 2);

        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        Vector2 position = person.getPosition();

        if(person.getDX() > 0.0f){
            batch.draw(currentFrame, position.x - currentFrame.getRegionWidth() / 2, position.y);
        } else {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth() / 2, position.y, 0, 0, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), -1, 1, 0);
        }

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
        ground.dispose();
        sky.dispose();
        walkSheet.dispose();
	}
}
