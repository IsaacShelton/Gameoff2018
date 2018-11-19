package com.dockysoft.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * added by coolboi G
 */


public class Platform {

    public int x, y;
    private SpriteBatch batch;
    private TextureCatalog textures;

    public Platform(SpriteBatch spriteBatch, TextureCatalog textureCatalog, int X, int Y) {
        batch = spriteBatch;
        textures = textureCatalog;
        x = X;
        y = Y;
    }

    public void drawPlatform()  {
        batch.draw(textures.platform, x, y, textures.platform.getWidth() / 2, textures.platform.getHeight() / 2);
    }
    public void addPlatform(int X, int Y) {
        batch.draw(textures.platform, X, Y);
    }


}