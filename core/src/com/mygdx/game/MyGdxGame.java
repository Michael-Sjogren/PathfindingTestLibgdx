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
	private static int NODE_WIDTH = 32;
	private static int NODE_HEIGHT = 32;

	private ArrayList<Tile> openList= new ArrayList<>();
	private ArrayList<Tile> closedList= new ArrayList<>();

	private ArrayList<Tile> allNodes = new ArrayList<>();

	
	@Override
	public void create () {
		batch = new ShapeRenderer();
        batch.setAutoShapeType(true);
        addNodesToList();
        setStart();
        setEnd();
	}

	public void setStart(){
        Random random = new Random();
        int num = random.nextInt(allNodes.size());
        if (allNodes.get(num).isEnd()){
            num = random.nextInt(allNodes.size());
        }
        System.out.println("START CELL:" + num );
        allNodes.get(num).setStart(true);
    }

    public void setEnd(){
	    Random random = new Random();
	    int num = random.nextInt(allNodes.size());
        if (allNodes.get(num).isStart()){
            num = random.nextInt(allNodes.size());
        }
        System.out.println("END CELL:" + num );
        allNodes.get(num).setEnd(true);
    }

	public void addNodesToList(){
		for (int row = 0; row < Gdx.graphics.getWidth() / 32; row++ ){
			for (int col = 0; col < Gdx.graphics.getHeight() / 32; col++) {
                allNodes.add(new Tile(row *32, col*32  , 32 ,32));
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
		for (Tile tile : allNodes){
		    if (tile.isStart()){
                batch.set(ShapeRenderer.ShapeType.Filled);
                batch.setColor(0,1f,0,1f);
                batch.rect(tile.getX() , tile.getY() , tile.getTileWidth() , tile.getTileHeight() );
            }else if(tile.isEnd()){
                batch.set(ShapeRenderer.ShapeType.Filled);
                batch.setColor(1f,0f,0,1f);
                batch.rect(tile.getX() , tile.getY() , tile.getTileWidth() , tile.getTileHeight() );
            }else {
                batch.set(ShapeRenderer.ShapeType.Line);
                batch.setColor(1f,1f,0,1f);
                batch.rect(tile.getX() , tile.getY() , tile.getTileWidth() , tile.getTileHeight() );
            }


        }
		batch.end();
	}
}
