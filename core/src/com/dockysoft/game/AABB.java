package com.dockysoft.game;

public class AABB {
    public float x, y, w, h;

    public AABB(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void set(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersecting(AABB other){
        return this.x < other.x + other.w && this.x + this.w > other.x && this.y < other.y + other.h && this.h + this.y > other.y;
    }

    public boolean equals(AABB other){
        return this.x == other.x && this.y == other.y && this.w == other.w && this.h == other.h;
    }
}
