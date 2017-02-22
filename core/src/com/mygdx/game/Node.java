package com.mygdx.game;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MichaelSjogren on 2017-02-22.
 */
public class Node {

    private Node parent;
    private final float fCost;
    private float gCost;
    private float hCost;

    public Node (Vector2 tile , Node parent , float gCost , float hCost){
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }

    private Vector2 tile;

    public Vector2 getTile() {
        return tile;
    }

    public void setTile(Vector2 tile) {
        this.tile = tile;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public float getfCost() {
        return fCost;
    }

    public float getgCost() {
        return gCost;
    }

    public void setgCost(float gCost) {
        this.gCost = gCost;
    }

    public float gethCost() {
        return hCost;
    }
    public void sethCost(float hCost) {
        this.hCost = hCost;
    }
}
