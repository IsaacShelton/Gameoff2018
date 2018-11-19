package com.dockysoft.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by isaac on 11/17/2018.
 */
public class Person {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 size;

    private static final int GRAVITY = 8;
    private static final boolean MOON_WALK = false;

    public Person(float x, float y, float w, float h) {
        position = new Vector2(x, y);
        velocity = new Vector2(0.0f, 0.0f);
        size = new Vector2(w, h);
    }

    public void walk(float vx, boolean sprint) {
        if (sprint) {
            velocity.x += ((vx < 0 && velocity.x > 0) || (vx > 0 && velocity.x < 0)) ? vx * 0.2 : vx * 0.1;
        } else {
            velocity.x += ((vx < 0 && velocity.x > 0) || (vx > 0 && velocity.x < 0)) ? vx * 0.1 : vx * 0.05;
        }
        velocity.x = Utils.clamp(velocity.x, -24.0f, 24.0f);
    }

    public void jump(float amount) {
        velocity.y = amount;
    }

    public void update() {
        position.add(velocity);

        // Reduce x velocity proportionally
        velocity.x -= (1 / 60.0f) * velocity.x;

        // If on ground stand on ground, else compound y velocity
        if (velocity.y < 0.0f && position.y < 196.0f) {
            position.y = 196.0f;
            velocity.y = 0.0f;
        } else {
            velocity.y -= GRAVITY * 0.1;
        }
    }

    public void clamp(float mx, float my) {
        position.x = Utils.clamp(position.x, 0.0f, mx);
        position.y = Utils.clamp(position.y, 0.0f, my);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public boolean isActuallyFacingRight() {
        return velocity.x >= 0;
    }

    public boolean isFacingRight() {
        return MOON_WALK ^ isActuallyFacingRight();
    }
}
