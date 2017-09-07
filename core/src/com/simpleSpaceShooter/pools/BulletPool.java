package com.simpleSpaceShooter.pools;

import com.simpleSpaceShooter.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
//        System.out.println("BulletPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
