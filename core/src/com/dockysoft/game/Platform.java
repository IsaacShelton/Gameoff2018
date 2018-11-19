package com.dockysoft.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * added by coolboi G
 */


public class Platform {

    public int x, y;
    private SpriteBatch batch;
    private TextureCatalog textures;


    public Platform(SpriteBatch batch){

    }

    public Platform(int X, int Y) {
        x = X;
        y = Y;
    }

    public void drawPlatform(){
        batch.draw(textures.platform, x, y);
    }
    public void addPlatform(int X, int Y){

    }


}