package com.simpleSpaceShooter.screens.game_screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.simpleSpaceShooter.common.Background;
import com.simpleSpaceShooter.common.enemies.EnemiesEmitter;
import com.simpleSpaceShooter.common.enemies.EnemyPool;
import com.simpleSpaceShooter.engine.Base2DScreen;
import com.simpleSpaceShooter.engine.Sprite2DTexture;
import com.simpleSpaceShooter.engine.math.Rect;
import com.simpleSpaceShooter.engine.math.Rnd;
import com.simpleSpaceShooter.common.bullets.BulletPool;
import com.simpleSpaceShooter.common.explosions.ExplosionPool;
import com.simpleSpaceShooter.common.stars.TrackingStar;

public class GameScreen extends Base2DScreen {

    //region Fields
    private static final int STARS_AMOUNT = 250;
    private static final float STAR_HEIGHT = 0.01f;

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private EnemyPool enemyPool;

    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;
    private TrackingStar[] stars = new TrackingStar[STARS_AMOUNT];
    private MainShip mainShip;
    EnemiesEmitter enemiesEmitter;

    private Music music;
    private Sound sndLaser;
    private Sound sndBullet;
    private Sound sndExplosion;

    //endregion

    //region Constructors
    public GameScreen(Game game) {
        super(game);
    }
    //endregion

    //region UserActionEvents
    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
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
    //endregion

    //region ScreenLifeEvents
    @Override
    public void show() {
        super.show();

        music = Gdx.audio.newMusic(Gdx.files.internal("tracks/music.mp3"));
        sndBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        sndLaser = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, sndExplosion);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, worldBounds, sndLaser);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, mainShip);

        enemiesEmitter = new EnemiesEmitter(enemyPool, worldBounds, atlas, sndBullet);

        background = new Background(new TextureRegion(textureBackground));
        mainShip = new MainShip(atlas, bulletPool, explosionPool, worldBounds, sndLaser);

        TextureRegion regionStar = atlas.findRegion("star");
        for (int i = 0; i < STARS_AMOUNT; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new TrackingStar(regionStar, vx, vy, starHeight, mainShip.getV());
        }
        music.setLooping(true);
        music.play();
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].resize(worldBounds);
        mainShip.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void update(float deltaTime) {
        enemiesEmitter.generateEnemies(deltaTime);
        enemyPool.updateActiveSprites(deltaTime);
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].update(deltaTime);
        bulletPool.updateActiveSprites(deltaTime);
        explosionPool.updateActiveSprites(deltaTime);
        mainShip.update(deltaTime);
    }

    private void checkCollisions() {

    }

    private void deleteAllDestroyed() {
        enemyPool.freeAllDestroyedActiveObjects();
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
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        mainShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        music.dispose();
        sndBullet.dispose();
        sndLaser.dispose();
        sndExplosion.dispose();
        sndExplosion.dispose();

        enemyPool.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        textureBackground.dispose();
        atlas.dispose();
        super.dispose();
    }
    //endregion
}
