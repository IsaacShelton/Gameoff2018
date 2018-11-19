package com.dockysoft.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dockysoft.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.width = Game.WIDTH;
		config.height = Game.HEIGHT;
		config.fullscreen = true;
		new LwjglApplication(new Game(), config);
	}
}
