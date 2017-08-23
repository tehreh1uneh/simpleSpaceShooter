package com.simpleSpaceShooter;

import com.badlogic.gdx.Game;
import com.simpleSpaceShooter.screens.menu.MenuScreen;

public class StarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
