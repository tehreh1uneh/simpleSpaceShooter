package com.simpleSpaceShooter.screens.menu_screen.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.simpleSpaceShooter.engine.ui.ActionListener;
import com.simpleSpaceShooter.engine.ui.ScaledTouchUpButton;
import com.simpleSpaceShooter.engine.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btExit"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
