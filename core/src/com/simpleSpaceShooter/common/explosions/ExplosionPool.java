 package com.simpleSpaceShooter.common.explosions;


 import com.badlogic.gdx.audio.Sound;
 import com.badlogic.gdx.graphics.g2d.TextureAtlas;
 import com.badlogic.gdx.graphics.g2d.TextureRegion;

 public class ExplosionPool extends com.simpleSpaceShooter.engine.pool.SpritesPool<Explosion> {

    private final TextureRegion explosionRegion;
    private Sound sndExplosion;

    public ExplosionPool(TextureAtlas atlas, Sound sndExplosion) {
        String regionName = "explosion";
        explosionRegion = atlas.findRegion(regionName);
        if(explosionRegion == null) throw new RuntimeException("Region " + regionName + " not found.");
        this.sndExplosion = sndExplosion;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionRegion, 9, 9, 74, sndExplosion);
    }

    @Override
    protected void debugLog() {
        //System.out.println("ExplosionPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }

}
