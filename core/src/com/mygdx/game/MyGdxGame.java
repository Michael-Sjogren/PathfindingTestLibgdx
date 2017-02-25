package com.mygdx.game;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Tile startTile1;
    private Tile startTile2;
    private Tile endTile;
    private List<Node> path1;
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
        startTile1 = getStart();
        endTile = setGoal();
        path1 = PathFinder.findPath(startTile1.getTileCenter() , endTile.getTileCenter() , true);
    }

    public Tile getStart(){
        Tile tile;
        do{
            tile = LevelManager.getTileByXY( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (tile.getType() == TileType.WALL || tile == endTile);
        return tile;
    }

    public Tile setGoal(){
        Tile tile;
        do{
            tile = LevelManager.getTileByXY( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (tile.getType() == TileType.WALL || tile == startTile1 || tile == startTile2);
        return tile;
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
        shapeRenderer.rect(startTile1.getCords().x , startTile1.getCords().y, startTile1.getTileWidth() ,startTile1.getTileHeight());
        shapeRenderer.setColor(0,1,1,1f);
        shapeRenderer.rect(endTile.getCords().x , endTile.getCords().y , endTile.getTileWidth() ,endTile.getTileHeight());
        shapeRenderer.end();

        if(path1 !=  null){	
            for (Node node: path1
                    ) {
                shapeRenderer.begin();
                shapeRenderer.set(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(.5f ,0 ,0 ,1f);
                shapeRenderer.line(node.getCordinates() , node.getParent().getCordinates());
                shapeRenderer.end();
            }
        }
    }

}
