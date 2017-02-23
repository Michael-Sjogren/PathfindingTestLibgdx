package com.mygdx.game;

/**
 * Created by MichaelSjogren on 2017-02-22.
 */
public class Node{

    private float x;
    private float y;
    private Node parent;
    private int fCost;
    private int gCost;
    private int hCost;

    public Node(float x , float y){
        this.x = x - LevelManager.tilePixelWidth /2;
        this.y = y - LevelManager.tilePixelHeight /2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public void setNodeParent(Node parent){
        this.parent = parent;
    }

    public int getfCost() {
        return fCost;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }


}
