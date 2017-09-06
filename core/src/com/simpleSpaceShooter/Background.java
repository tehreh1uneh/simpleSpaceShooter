package com.simpleSpaceShooter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.simpleSpaceShooter.math.Rect;
import com.simpleSpaceShooter.sprites.Sprite;

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
