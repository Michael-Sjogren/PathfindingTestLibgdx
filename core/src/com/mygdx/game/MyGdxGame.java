package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Random random;


	@Override
	public void create () {
        map = new TmxMapLoader().load(("simple-map.tmx"));
        renderer = new OrthogonalTiledMapRenderer(map, 1f);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        camera.position.set( new Vector3(Gdx.graphics.getWidth() / 2 , Gdx.graphics.getHeight() / 2 , 0));
        camera.update();

        TiledMapTileLayer layer1 = (TiledMapTileLayer) map.getLayers().get("layer1");
        TiledMapTileLayer.Cell cell = layer1.getCell(1 , 0);
      
    }



	public void setStart(){

    }


    public void setEnd(){

    }

	public void addNodesToList(){

	}

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

	@Override
	public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

	}

}
