package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by MichaelSjogren on 2017-02-26.
 */
public class Enemy extends Entity {
    private SpriteBatch batch;
    private Player player;
    private ArrayList<Node> path = null;
    private ShapeRenderer shapeRenderer;

    public Enemy(Vector2 position , Sprite sprite , Player player){
        super(position , sprite);
        this.player = player;
        this.position = position;
        this.sprite = sprite;
        init();
    }

    @Override
    void update(float delta) {
        move(delta);
        position.set(x ,y);
    }

    @Override
    void render() {
        batch.begin();
        sprite.draw(batch);
        batch.end();
        if(path !=  null){
            for (Node node: path
                    ) {
                shapeRenderer.begin();
                shapeRenderer.set(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(.5f ,0 ,0 ,1f);
                Vector2 childNode = new Vector2(node.getCordinates().x * 32 + 16 , node.getCordinates().y * 32 + 16);
                Vector2 parentNode = new Vector2(node.getParent().getCordinates().x * 32 + 16 , node.getParent().getCordinates().y * 32 +16);
                shapeRenderer.line(childNode , parentNode);
                shapeRenderer.end();
            }
        }
    }

    @Override
    void init() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
    }

    @Override
    void dispose() {

    }

    @Override
    void move(float delta) {
        float pTilePosX = (int)player.getPosition().x >> 5;
        float pTilePosY = (int)player.getPosition().y >> 5;
        path = PathFinder.findPath(new Vector2(((int)x >> 5 ), (int)y >> 5), new Vector2(pTilePosX , pTilePosY),true);
    }
}
