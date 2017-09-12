package com.simpleSpaceShooter.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.simpleSpaceShooter.engine.math.Rect;
import com.simpleSpaceShooter.engine.sprites.Sprite;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {

        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
