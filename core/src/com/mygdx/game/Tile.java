package com.mygdx.game;

/**
 * Created by MichaelSjogren on 2017-02-19.
 */
public class Tile {
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    private float y;
    private float x;
    private boolean isWall = false;
    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean isWalkable = true;

    public Tile(float x , float y){

        this.x = x;
        this.y = y;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

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

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }
}
