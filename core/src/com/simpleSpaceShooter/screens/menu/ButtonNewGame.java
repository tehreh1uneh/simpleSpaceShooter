package com.simpleSpaceShooter.screens.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.simpleSpaceShooter.gui.ActionListener;
import com.simpleSpaceShooter.gui.ScaledTouchUpButton;
import com.simpleSpaceShooter.math.Rect;

class ButtonNewGame extends ScaledTouchUpButton {

    ButtonNewGame(TextureAtlas atlas, ActionListener listener, float pressScale) {
        super(atlas.findRegion("btPlay"), listener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
