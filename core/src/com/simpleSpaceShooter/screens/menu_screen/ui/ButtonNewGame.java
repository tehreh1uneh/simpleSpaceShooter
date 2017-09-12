package com.simpleSpaceShooter.screens.menu_screen.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.simpleSpaceShooter.engine.ui.ActionListener;
import com.simpleSpaceShooter.engine.ui.ScaledTouchUpButton;
import com.simpleSpaceShooter.engine.math.Rect;

public class ButtonNewGame extends ScaledTouchUpButton {

    public ButtonNewGame(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btPlay"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
