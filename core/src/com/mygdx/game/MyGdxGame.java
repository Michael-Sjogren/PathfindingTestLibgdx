package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Node startNode;
    private Node endNode;



    @Override
	public void create () {
        LevelManager.loadLevel("simple-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(LevelManager.tiledMap, 1f);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        camera.position.set( new Vector3(Gdx.graphics.getWidth() / 2 , Gdx.graphics.getHeight() / 2 , 0));
        camera.update();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setAutoShapeType(true);
        setStart();
        setGoal();
    }

    public void findPath(Node startNode , Node endNode){

    }


    public void setStart(){
        do{
            startNode = LevelManager.getNode( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (startNode.getType() == TileType.WALL || startNode == endNode);
    }

    public void setGoal(){
        do{
            endNode = LevelManager.getNode( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (endNode.getType() == TileType.WALL || endNode == startNode);
    }






	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,0,1f);
        shapeRenderer.rect(startNode.getX() - 16 , startNode.getY() - 16 , 32 ,32);
        shapeRenderer.setColor(0,1,1,1f);
        shapeRenderer.rect(endNode.getX() - 16 , endNode.getY() - 16 , 32 ,32);
        shapeRenderer.end();
        for (int x = 0; x < LevelManager.nodes.length; x++) {
            for (int y = 0; y < LevelManager.nodes[0].length; y++){
                shapeRenderer.begin();
                Node node = LevelManager.nodes[x][y];
                if(node.getType() == TileType.FLOOR){
                    shapeRenderer.setColor(1f , 1f , 1f , 1);
                    shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.circle(node.getX() , node.getY(), 1f);
                }else if(node.getType() == TileType.WALL){
                    shapeRenderer.setColor(1f , 1f , 0 , 1);
                    shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.circle(node.getX() , node.getY(), 2f);
                }
                shapeRenderer.end();
            }
        }
    }

}
