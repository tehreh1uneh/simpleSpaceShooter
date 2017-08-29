package com.simpleSpaceShooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.simpleSpaceShooter.StarGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        float aspectRatio = 3f / 4f;

        config.width = 450;
        config.height = (int) (config.width / aspectRatio);

        new LwjglApplication(new StarGame(), config);
    }
}
