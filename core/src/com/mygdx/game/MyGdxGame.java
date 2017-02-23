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
        System.out.println(LevelManager.checkIfWall(64 , 64));
    }

    public void findPath(Node startNode , Node endNode){
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        openList.add(startNode);
        while(openList.size() > 0){
           Node current = sortByLowestFcost(openList);
           openList.remove(current);
           closedList.add(current);
           if(current.equals(endNode)){
            return;
           }
        }
    }

    public Node sortByLowestFcost(ArrayList<Node> openList){
        Node minNode = new Node(0 , 0);
        int min = 0;
        for (Node node: openList) {
            if(min > node.getfCost()) {
                min = node.getfCost();
                minNode = node;
            }
        }
        return minNode;
    }







	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        for (int y = 0; y < LevelManager.lvlTileHeight; y++) {
            for (int x = 0; x < LevelManager.lvlTileWidth; x++){
                Node node = LevelManager.nodes[x][y];
                shapeRenderer.begin();
                shapeRenderer.point(node.getX() , node.getY(), 0f);
                shapeRenderer.end();
            }
        }
    }

}
