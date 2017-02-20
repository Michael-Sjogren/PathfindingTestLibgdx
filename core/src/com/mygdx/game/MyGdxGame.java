package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer batch;
	Texture img;


	private ArrayList<Tile> openList= new ArrayList<>();
	private ArrayList<Tile> closedList= new ArrayList<>();

    public static ArrayList<Tile> allTiles = new ArrayList<>();

	@Override
	public void create () {
		batch = new ShapeRenderer();
        batch.setAutoShapeType(true);
        addNodesToList();
        createWalls();
        setStart();
        setEnd();
	}


	public void setStart(){
        Random random = new Random();
        int num = random.nextInt(allTiles.size());
        while(allTiles.get(num).isEnd() && !allTiles.get(num).isWalkable()){
            num = random.nextInt(allTiles.size());
        }
        System.out.println("START CELL:" + num );
        allTiles.get(num).setStart(true);
    }

    public void setEnd(){
	    Random random = new Random();
	    int num = random.nextInt(allTiles.size());
        while(allTiles.get(num).isStart() && !allTiles.get(num).isWalkable()){
            num = random.nextInt(allTiles.size());
        }
        System.out.println("END CELL:" + num );
        allTiles.get(num).setEnd(true);
    }

    public void createWalls(){
        for (int i = 0; i < 15; i++) {
            allTiles.get(200 + i).setWalkable(false);
        }
    }

	public void addNodesToList(){
		for (int row = 0; row < Gdx.graphics.getWidth() / 32; row++ ){
			for (int col = 0; col < Gdx.graphics.getHeight() / 32; col++) {
                allTiles.add(new Tile(row * Tile.TILE_WIDTH, col* Tile.TILE_HEIGHT ));
			}
		}
	}

	@Override
	public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.set(ShapeRenderer.ShapeType.Line);
        batch.line(0,Gdx.graphics.getHeight() , 0 ,Gdx.graphics.getWidth() , Gdx.graphics.getHeight() , 0);
		for (Tile tile : allTiles){
		    if (tile.isStart()){
                batch.set(ShapeRenderer.ShapeType.Filled);
                batch.setColor(0,1f,0,1f);
                batch.rect(tile.getX() , tile.getY() , Tile.TILE_WIDTH , Tile.TILE_HEIGHT );
            }else if(tile.isEnd()){
                batch.set(ShapeRenderer.ShapeType.Filled);
                batch.setColor(1f,0f,0,1f);
                batch.rect(tile.getX() , tile.getY() , Tile.TILE_WIDTH , Tile.TILE_HEIGHT );
            }else if( !tile.isWalkable()){
                batch.set(ShapeRenderer.ShapeType.Filled);
                batch.setColor(0f,0f,1f,1f);
                batch.rect(tile.getX() , tile.getY() , Tile.TILE_WIDTH , Tile.TILE_HEIGHT );
            }
            else {
                batch.set(ShapeRenderer.ShapeType.Line);
                batch.setColor(1f,1f,0,1f);
                batch.rect(tile.getX() , tile.getY() , Tile.TILE_HEIGHT , Tile.TILE_HEIGHT );
            }


        }
		batch.end();
	}
}
