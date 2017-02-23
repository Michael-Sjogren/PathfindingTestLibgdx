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
import com.badlogic.gdx.math.Path;
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
    private Vector2 start, goal;
    private Random random;
    private ShapeRenderer shapeRenderer;
    private TiledMapTileLayer layer1;

    @Override
	public void create () {
        map = new TmxMapLoader().load(("simple-map.tmx"));
        renderer = new OrthogonalTiledMapRenderer(map, 1f);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        camera.position.set( new Vector3(Gdx.graphics.getWidth() / 2 , Gdx.graphics.getHeight() / 2 , 0));
        camera.update();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setAutoShapeType(true);
        layer1 = (TiledMapTileLayer) map.getLayers().get("layer1");
        setStart();
        setEnd();
        System.out.println(findPath( start , goal ));

    }



	public void setStart(){
        start = new Vector2(64 , 64);
    }


    public void setEnd(){
        goal = new Vector2(576 ,320);
    }

    public List<Node> findPath(Vector2 start , Vector2 goal){
        if (layer1 == null) return null;
        ArrayList<Node> openList= new ArrayList<>();
        ArrayList<Node> closedList= new ArrayList<>();
        Node current = new Node(start, null ,0 , Vector2.dst(start.x , start.y , goal.x , goal.y));
        openList.add(current);
        while(openList.size() > 0){
            current = openList.get(0);
            // sort nodes that are in open list, the node with lowest f cost should be
            Collections.sort(openList , new HCostComparator());
            if(current.getTile().equals(goal)){
                List<Node> path = new ArrayList<>();
                while (current.getParent() != null){
                    path.add(current);
                    current.setParent(current);
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9 ; i++){
                if(i == 4) continue;
                int x = (int)current.getTile().x;
                int y = (int)current.getTile().y;
                // getting the direction
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                int cellx = x + xi;
                int celly = y + yi;
                System.out.println(cellx + ":: " + celly );
                TiledMapTile tileAt = layer1.getCell(cellx ,celly ).getTile();
                if(tileAt.equals(null))return null;
                if(tileAt.getProperties().get("Wall",Boolean.class)) continue;
                Vector2 a = new Vector2(x + xi , y + yi);

                float gcost = current.getgCost() + Vector2.dst(current.getTile().x , current.getTile().y , a.x , a.y);
                float hcost = Vector2.dst(a.x , a.y , goal.x , goal.y);
                Node node = new Node(a , current , gcost , hcost);
                if(vectInList(closedList , a) && gcost <= current.getgCost()) continue;
                if(vectInList(openList , a) || gcost < current.getgCost()) openList.add(node);
            }
        }
        closedList.clear();
        return null;
    }

    private boolean vectInList(List<Node> list , Vector2 vector2){
        for (Node n : list){
            if(n.getTile().equals(vector2)) return true;
        }
        return false;
    }

	@Override
	public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        shapeRenderer.begin();
        shapeRenderer.rect(start.x , start.y , 32 ,32);
        shapeRenderer.rect(goal.x , goal.y , 32 ,32);
        shapeRenderer.end();

	}

}
