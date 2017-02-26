package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.input.KeyCode;


/**
 * Created by MichaelSjogren on 2017-02-26.
 */
public class Player extends Entity implements InputProcessor{

    private SpriteBatch batch;
    private boolean UP;
    private boolean RIGHT;
    private boolean DOWN;
    private boolean LEFT;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Player(Vector2 position , Sprite sprite){
        super(position , sprite);
        this.position = position;
        this.sprite = sprite;
        init();
    }

    @Override
    public void update(float delta) {
        move(delta);
        position.set(x ,y);
    }

    @Override
    public void render() {
        batch.begin();
        sprite.setX(getX());
        sprite.setY(getY());
        sprite.draw(batch);
        batch.end();
    }

    @Override
    void init() {
        batch = new SpriteBatch();
    }

    @Override
    void dispose() {
        batch.dispose();
    }

    @Override
    public void move(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            setY(getY() + 128 * delta);
            UP = true;
        }else {
            UP = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            setY(getY() - 128 * delta);
            DOWN = true;
        }else {
            DOWN = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            setX(getX() - 128 * delta);
            LEFT = true;
        }else{
            LEFT = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            setX(getX() + 128 * delta);
            RIGHT = true;
        }else{
            RIGHT = false;
        }
    }


    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
