package com.dockysoft.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Game extends ApplicationAdapter {
    OrthographicCamera camera;
    ExtendViewport viewport;

	SpriteBatch batch;
	Texture img;

    private static final int FRAME_COLS = 6, FRAME_ROWS = 5;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;

    float stateTime;

	@Override
	public void create () {
	    camera = new OrthographicCamera();
	    viewport = new ExtendViewport(1920.0f, 1280.0f, camera);

		batch = new SpriteBatch();
		img = new Texture("smile.jpg");

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("sprite-animation.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        stateTime = 0f;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		batch.begin();
		batch.draw(img, 10, 0);

        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, 800, 800); // Draw current frame at (50, 50)
        batch.draw(currentFrame, 1000, 800); // Draw current frame at (50, 50)
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
        walkSheet.dispose();
	}
}
