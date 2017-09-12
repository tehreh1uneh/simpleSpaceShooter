package com.simpleSpaceShooter.common.enemies;


import com.simpleSpaceShooter.common.bullets.BulletPool;
import com.simpleSpaceShooter.common.explosions.ExplosionPool;
import com.simpleSpaceShooter.engine.math.Rect;
import com.simpleSpaceShooter.engine.pool.SpritesPool;
import com.simpleSpaceShooter.screens.game_screen.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;
    private final Rect worldBounds;
    private final MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldBounds, mainShip);
    }

    @Override
    protected void debugLog() {
//        System.out.println("EnemyPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
