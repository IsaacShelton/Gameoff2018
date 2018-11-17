package com.dockysoft.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by isaac on 11/17/2018.
 */
public class Person {
    private Vector2 position;
    private float dx;

    private static final boolean GO_BANANAS = true;

    public Person(float x, float y){
        position = new Vector2(x, y);
        dx = 0;
    }

    public void move(float vx, float dt, boolean sprint){
        if(sprint){
            dx += ((vx < 0 && dx > 0) || (vx > 0 && dx < 0)) ? vx * 0.2 : vx * 0.1;
        } else {
            dx += ((vx < 0 && dx > 0) || (vx > 0 && dx < 0)) ? vx * 0.1 : vx * 0.05;
        }
        dx = Utils.clamp(dx, -24.0f, 24.0f);
    }

    public void update(){position.x += dx;
        dx -= (1 / 60.0f) * dx;
    }

    public void clamp(float mx, float my){
        position.x = Utils.clamp(position.x, 0.0f, mx);
        position.y = Utils.clamp(position.y, 0.0f, my);
    }

    public Vector2 getPosition(){
        return position;
    }

    public float getDX(){
        return dx;
    }
}