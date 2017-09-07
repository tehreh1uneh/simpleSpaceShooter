package com.simpleSpaceShooter.screens.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.simpleSpaceShooter.Background;
import com.simpleSpaceShooter.Explosion;
import com.simpleSpaceShooter.engine.Base2DScreen;
import com.simpleSpaceShooter.engine.Sprite2DTexture;
import com.simpleSpaceShooter.math.Rect;
import com.simpleSpaceShooter.math.Rnd;
import com.simpleSpaceShooter.pools.BulletPool;
import com.simpleSpaceShooter.pools.ExplosionPool;
import com.simpleSpaceShooter.stars.TrackingStar;

public class GameScreen extends Base2DScreen {

    private static final int STARS_AMOUNT = 250;
    private static final float STAR_HEIGHT = 0.01f;

    private final BulletPool bulletPool = new BulletPool();
    private ExplosionPool explosionPool;

    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;
    private MainShip mainShip;
    private TrackingStar[] stars = new TrackingStar[STARS_AMOUNT];
    private Sound sndExplosion;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        explosionPool = new ExplosionPool(atlas, sndExplosion);

        background = new Background(new TextureRegion(textureBackground));
        mainShip = new MainShip(atlas, bulletPool);

        TextureRegion regionStar = atlas.findRegion("star");
        for (int i = 0; i < STARS_AMOUNT; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new TrackingStar(regionStar, vx, vy, starHeight, mainShip.getV());
        }

    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].resize(worldBounds);
        mainShip.resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        Explosion explosion = explosionPool.obtain();
        explosion.set(0.1f, touch);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void update(float deltaTime) {
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].update(deltaTime);
        bulletPool.updateActiveSprites(deltaTime);
        explosionPool.updateActiveSprites(deltaTime);
        mainShip.update(deltaTime);
    }

    private void checkCollisions() {

    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].draw(batch);
        bulletPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        mainShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        explosionPool.dispose();
        textureBackground.dispose();
        atlas.dispose();
        bulletPool.dispose();
        sndExplosion.dispose();
        super.dispose();
    }
}
