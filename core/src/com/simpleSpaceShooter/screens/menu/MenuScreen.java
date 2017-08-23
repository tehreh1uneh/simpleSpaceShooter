package com.simpleSpaceShooter.screens.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.simpleSpaceShooter.engine.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("galaxy.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();

        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("key down " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyup " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keytyped " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchdown x " + screenX + " y " + screenY + " pointer " + pointer + " button " + button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchup x " + screenX + " y " + screenY + " pointer " + pointer + " button " + button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchdragged x " + screenX + " y " + screenY + " pointer " + pointer);
        return false;
    }
}
