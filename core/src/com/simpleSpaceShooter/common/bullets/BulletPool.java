package com.simpleSpaceShooter.common.bullets;

import com.simpleSpaceShooter.common.bullets.Bullet;

public class BulletPool extends com.simpleSpaceShooter.engine.pool.SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
//        System.out.println("BulletPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
