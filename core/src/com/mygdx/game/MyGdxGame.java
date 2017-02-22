package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer batch;
	Texture img;
    private BitmapFont font;
    private int[] tiles;

    SpriteBatch sprBatch;
    private Random random;


	public List<Node> findPath(Vector2 start , Vector2 goal){

        ArrayList<Node> openList= new ArrayList<>();
        ArrayList<Node> closedList= new ArrayList<>();
        Node current = new Node(start, null ,0 , start.dst(goal));
        openList.add(current);

        while(openList.size() < 0){
            current = openList.get(0);
            // sort nodes that are in open list, the node with lowest f cost should be
            Collections.sort(openList , new HCostComparator());
            if(current.getTile().equals(goal)){
                //return
            }

            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9 ; i++){
                if(i == 4) continue;
                float x = current.getTile().x;
                float y = current.getTile().y;
                // getting the
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                //Tile currentTile =
            }
        }
        return null;

    }

    public static Tile[][] allTiles;

	@Override
	public void create () {
        tiles = new int[Gdx.graphics.getWidth() / 32 * Gdx.graphics.getHeight() / 32];
        System.out.println(tiles.length);
        batch = new ShapeRenderer();
        batch.setAutoShapeType(true);
        allTiles = new Tile[Gdx.graphics.getWidth() / Tile.TILE_WIDTH][Gdx.graphics.getHeight() / Tile.TILE_HEIGHT];
        font = new BitmapFont();
        sprBatch = new SpriteBatch();
        addNodesToList();
        createWalls();
        setStart();
        setEnd();
	}


	public void setStart(){
        Random random = new Random();
        int randRow = random.nextInt(Gdx.graphics.getWidth() / Tile.TILE_WIDTH);
        int randCol = random.nextInt(Gdx.graphics.getHeight() / Tile.TILE_HEIGHT);
        Tile tile = allTiles[randRow][randCol];
        while(tile.isEnd() && !tile.isWalkable()){
            randRow = random.nextInt(Gdx.graphics.getWidth() / Tile.TILE_WIDTH);
            randCol = random.nextInt(Gdx.graphics.getHeight() / Tile.TILE_HEIGHT);
        }
        tile = allTiles[randRow][randCol];
        tile.setStart(true);
    }

    public void setEnd(){
	    Random random = new Random();
        int randRow = random.nextInt(Gdx.graphics.getWidth() / Tile.TILE_WIDTH);
        int randCol = random.nextInt(Gdx.graphics.getHeight() / Tile.TILE_HEIGHT);
        Tile tile = allTiles[randRow][randCol];
        while(tile.isStart() && !tile.isWalkable()){
            randRow = random.nextInt(Gdx.graphics.getWidth() / Tile.TILE_WIDTH);
            randCol = random.nextInt(Gdx.graphics.getHeight() / Tile.TILE_HEIGHT);
        }
        tile = allTiles[randRow][randCol];
        tile.setEnd(true);
    }

    public void createWalls(){
        for (int i = 0; i < 15; i++) {
           allTiles[5 + i][5].setWalkable(false);
        }
    }

	public void addNodesToList(){
		for (int row = 0; row < Gdx.graphics.getWidth() / 32; row++ ){
			for (int col = 0; col < Gdx.graphics.getHeight() / 32; col++) {
                allTiles[row][col] = new Tile(row * Tile.TILE_WIDTH, col* Tile.TILE_HEIGHT , 0 , 0 ,0);
			}
		}
	}

	public Tile getTile(float x , float y){
     if(x < 0 || y < 0 || x >= Gdx.graphics.getWidth() || y >= Gdx.graphics.getHeight()) return null;

    return null;
    }

	@Override
	public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.set(ShapeRenderer.ShapeType.Line);
        batch.line(0,Gdx.graphics.getHeight() , 0 ,Gdx.graphics.getWidth() , Gdx.graphics.getHeight() , 0);
		for (int row = 0; row < Gdx.graphics.getWidth() >> 5; row++){
		    for (int col = 0; col < Gdx.graphics.getHeight() >> 5; col++){
		        Tile tile;
                tile = allTiles[row][col];
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
                    font.setColor(1f,1f,1f,1f);
                }
                else {
                    batch.set(ShapeRenderer.ShapeType.Line);
                    batch.setColor(1f,1f,0,1f);
                    batch.rect(tile.getX() , tile.getY() , Tile.TILE_HEIGHT , Tile.TILE_HEIGHT );
                }
            }


        }

        batch.end();
	}
}
