package com.dockysoft.game;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by isaac on 11/18/2018.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TextureAsset {
    String value();
}
