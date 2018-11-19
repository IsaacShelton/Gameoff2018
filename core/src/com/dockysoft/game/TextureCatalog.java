package com.dockysoft.game;

import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Field;

/**
 * Created by isaac on 11/18/2018.
 */
public class TextureCatalog {
    @TextureAsset("badlogic.jpg")
    public Texture badlogic;

    @TextureAsset("ground.png")
    public Texture ground;

    @TextureAsset("sky.jpg")
    public Texture sky;

    @TextureAsset("smile.jpg")
    public Texture smile;

    @TextureAsset("sprite-animation.png")
    public Texture walkSheet;

    public void loadTextures(){
        for(Field field : this.getClass().getDeclaredFields()){
            TextureAsset location = field.getAnnotation(TextureAsset.class);

            if(location != null) try {
                field.set(this, new Texture(location.value()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispose(){
        for(Field field : this.getClass().getDeclaredFields()){
            TextureAsset location = field.getAnnotation(TextureAsset.class);

            if(location != null) try {
                Object maybeTexture = field.get(this);

                if(maybeTexture.getClass().equals(Texture.class)){
                    ((Texture) maybeTexture).dispose();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
