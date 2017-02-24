package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;

/**
 * Created by MichaelSjogren on 2017-02-23.
 */
public class LevelManager {
    public static int mapWidthInTiles;
    public static int mapHeightInTiles;
    public static int mapPixelWidth;
    public static int mapPixelHeight;
    public static TiledMap tiledMap;
    public static Integer tileWidth;
    public static Integer tileHeight;
    public static TiledMapTileLayer tileLayer;
    public static Node[][] nodes;

    public static void loadLevel(String filePath) {
        tiledMap = new TmxMapLoader().load(filePath);
        MapProperties properties = tiledMap.getProperties();
        tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        mapWidthInTiles = properties.get("width", Integer.class);
        mapHeightInTiles = properties.get("height", Integer.class);
        System.out.println("Map width: " + mapWidthInTiles + " :: " + mapHeightInTiles);
        tileWidth = properties.get("tilewidth", Integer.class);
        tileHeight = properties.get("tileheight", Integer.class);
        nodes = new Node[Gdx.graphics.getWidth() / tileWidth][Gdx.graphics.getHeight() / tileHeight];
        mapPixelWidth = mapWidthInTiles * tileWidth;
        mapPixelHeight = mapHeightInTiles * tileHeight;
        System.out.println("map width in tiles:" + mapWidthInTiles + " : map height in tiles: " + mapHeightInTiles);
        createNodeList();
    }


    public static boolean checkIfWall(int x, int y) {
        boolean iswall = Boolean.valueOf(tileLayer.getCell(x, y).getTile().getProperties().get("Wall" , String.class));
        if (iswall) {
            return true;
        } else {
            return false;
        }
    }

    public static Node getNode(int x ,int y){
        return nodes[x][y];
    }

    private static void createNodeList() {
        int walls = 0;
        int floor = 0;
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[0].length; y++) {
                System.out.println("X: " + x + " :: " + "Y: " + y);
                if (checkIfWall(  x  ,  y )) {
                    walls ++;
                    nodes[x][y] = new Node(x * tileWidth, y * tileHeight, TileType.WALL);
                } else {
                    floor++;
                    nodes[x][y] = new Node(x * tileWidth, y * tileHeight, TileType.FLOOR);
                }
            }
        }
        System.out.println("Floor tiles: " + floor);
        System.out.println("Wall tiles: " + walls);
    }
}
