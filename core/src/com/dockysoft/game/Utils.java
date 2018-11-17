package com.dockysoft.game;

/**
 * Created by isaac on 11/17/2018.
 */
public class Utils {
    public static float clamp(float value, float u, float m){
        return value < u ? u : (value > m ? m : value);
    }
}
