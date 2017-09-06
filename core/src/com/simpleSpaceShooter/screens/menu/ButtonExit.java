package com.simpleSpaceShooter.screens.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.simpleSpaceShooter.gui.ActionListener;
import com.simpleSpaceShooter.gui.ScaledTouchUpButton;
import com.simpleSpaceShooter.math.Rect;

class ButtonExit extends ScaledTouchUpButton {

    ButtonExit(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btExit"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
