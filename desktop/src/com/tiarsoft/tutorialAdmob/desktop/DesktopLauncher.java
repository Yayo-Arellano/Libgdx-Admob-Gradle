package com.tiarsoft.tutorialAdmob.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tiarsoft.tutorialAdmob.AdHandler;
import com.tiarsoft.tutorialAdmob.MyNewGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new MyNewGame(new AdHandler() {

			@Override
			public void showInterstitial() {
				// TODO Auto-generated method stub

			}
		}), config);
	}
}
