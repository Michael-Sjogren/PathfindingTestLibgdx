package com.mygdx.game;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
        Player player = new Player(startTile1.getCords(),new Sprite(new Texture("player.png")));
        Gdx.input.setInputProcessor(player);
        endTile = setGoal();
        Enemy enemy = new Enemy(endTile.getCords(),new Sprite(new Texture("enemy.png")), player);
    }

    public Tile getStart(){
        Tile tile;
        do{
            tile = LevelManager.getTile( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (tile.getType() == TileType.WALL || tile == endTile);
        return tile;
    }

    public Tile setGoal(){
        Tile tile;
        do{
            tile = LevelManager.getTile( new Random().nextInt(LevelManager.mapPixelWidth) / 32 , new Random().nextInt(LevelManager.mapPixelHeight / 32));
        }
        while (tile.getType() == TileType.WALL || tile == startTile1 || tile == startTile2);
        return tile;
    }


	@Override
	public void render () {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        for (Entity entity : Entity.entities){
            entity.render();
        }
    }

    private void update(float delta) {
        for (Entity entity : Entity.entities){
            entity.update(delta);
        }
    }

}
