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
    public static int lvlPixelWidth;
    public static int lvlPixelHeight;
    public static TiledMap tiledMap;
    public static Integer tilePixelWidth;
    public static Integer tilePixelHeight;
    public static TiledMapTileLayer tileLayer;
    public static Node[][] nodes;

    public static void loadLevel(String filePath){
        tiledMap = new TmxMapLoader().load(filePath);
        MapProperties properties = tiledMap.getProperties();
        tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        mapWidthInTiles = properties.get("width", Integer.class);
        mapHeightInTiles = properties.get("height", Integer.class);
        nodes = new Node[mapWidthInTiles +1][mapHeightInTiles +1];
        tilePixelWidth = properties.get("tilewidth" , Integer.class);
        tilePixelHeight = properties.get("tileheight" , Integer.class);
        lvlPixelWidth = mapWidthInTiles * tilePixelWidth;
        lvlPixelHeight = mapHeightInTiles * tilePixelHeight;
        System.out.println("map width in tiles:" + mapWidthInTiles + " : map height in tiles: " + mapHeightInTiles );
        createNodeList();
    }

    public static boolean checkIfWall(int x ,int y){
        int modY = y / tilePixelWidth;
        int modX = x / tilePixelHeight;
        boolean iswall = Boolean.valueOf(tileLayer.getCell(modX , modY).getTile().getProperties().get("Wall",String.class));
        if(!iswall){
            return false;
        }else{
            return true;
        }
    }

    private static void createNodeList(){
        for (int y = 0; y < mapHeightInTiles +1; y++) {
            for (int x = 0; x < mapWidthInTiles +1; x++){
               // if(checkIfWall(x  , y )) ;
                nodes[x][y] = new Node(x * 32 , y * 32);
            }
        }
    }
}
