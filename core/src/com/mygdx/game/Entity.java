package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by MichaelSjogren on 2017-02-26.
 */
public abstract class Entity {
    public static ArrayList<Entity> entities = new ArrayList<>();
    protected Sprite sprite;
    protected Vector2 position;
    protected float x , y;

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

    public Entity(Vector2 position , Sprite sprite){
        this.position = position;
        this.sprite = sprite;
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
        this.x = position.x;
        this.y = position.y;
        entities.add(this);
    }

    abstract void update(float delta);

    abstract void render();

    abstract void init();

    abstract void dispose();

    abstract void move(float delta);

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

}
