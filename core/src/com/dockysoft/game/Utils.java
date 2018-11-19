package com.dockysoft.game;

/**
 * Created by isaac (Why you no capitalize your own name? -Gage) on 11/17/2018.
 */
public class Utils {
    public static float clamp(float value, float u, float m){
        return value < u ? u : (value > m ? m : value);
    }
}
