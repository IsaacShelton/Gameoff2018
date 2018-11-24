package com.dockysoft.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * added by coolboi G
 */


public class Platform {
    public int x, y, w, h;
    private SpriteBatch batch;
    private TextureCatalog textures;
    private AABB aabb;


    public Platform(SpriteBatch spriteBatch, TextureCatalog textureCatalog, int x, int y) {
        batch = spriteBatch;
        textures = textureCatalog;
        this.x = x;
        this.y = y;
        this.w = textures.platform.getWidth() / 2;
        this.h = textures.platform.getHeight() / 2;
        this.aabb = new AABB(this.x, this.y, this.w, this.h * 0.70f);
    }

    public void drawPlatform()  {
        batch.draw(textures.platform, x, y, w, h);
    }

    public AABB getPlatformBox() {
        return aabb;
    }
}

