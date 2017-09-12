package com.simpleSpaceShooter.screens.menu_screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.simpleSpaceShooter.common.Background;
import com.simpleSpaceShooter.common.stars.Star;
import com.simpleSpaceShooter.engine.Base2DScreen;
import com.simpleSpaceShooter.engine.Sprite2DTexture;
import com.simpleSpaceShooter.engine.math.Rect;
import com.simpleSpaceShooter.engine.math.Rnd;
import com.simpleSpaceShooter.engine.ui.ActionListener;
import com.simpleSpaceShooter.screens.game_screen.GameScreen;
import com.simpleSpaceShooter.screens.menu_screen.ui.ButtonExit;
import com.simpleSpaceShooter.screens.menu_screen.ui.ButtonNewGame;

public class MenuScreen extends Base2DScreen implements ActionListener {

    private static final int STARS_AMOUNT = 250;
    private static final float BUTTON_HEIGHT = 0.15f;
    private static final float BUTTON_PRESS_SCALE = 0.9f;


    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;
    private Star[] stars = new Star[STARS_AMOUNT];
    private static final float STAR_HEIGHT = 0.01f;
    private ButtonExit buttonExit;
    private ButtonNewGame buttonNewGame;

    private Music music;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        background = new Background(new TextureRegion(textureBackground));
        TextureRegion regionStar = atlas.findRegion("star");
        music = Gdx.audio.newMusic(Gdx.files.internal("tracks/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        for (int i = 0; i < STARS_AMOUNT; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new Star(regionStar, vx, vy, starHeight);
        }

        buttonNewGame = new ButtonNewGame(atlas, this, BUTTON_PRESS_SCALE);
        buttonNewGame.setHeightProportion(BUTTON_HEIGHT);
        buttonExit = new ButtonExit(atlas, this, BUTTON_PRESS_SCALE);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer);
        buttonNewGame.touchDown(touch, pointer);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonNewGame.touchUp(touch, pointer);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonExit) {
            Gdx.app.exit();
        } else if (src == buttonNewGame) {
            game.setScreen(new GameScreen(game));
        } else {
            throw new RuntimeException("Unknown src = " + src);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float deltaTime) {
        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].update(deltaTime);
    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < STARS_AMOUNT; i++) stars[i].draw(batch);
        buttonNewGame.draw(batch);
        buttonExit.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
        atlas.dispose();
        music.dispose();
        super.dispose();
    }


}
