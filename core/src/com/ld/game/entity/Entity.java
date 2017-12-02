package com.ld.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.map.Map;

public abstract class Entity {

    private Sprite sprite;

    private Vector2 position;

    private Map parentMap;

    public Entity(Map parentMap, Sprite sprite, Vector2 position) {
        this.setSprite(sprite);
        this.setPosition(position);
        this.setParentMap(parentMap);
    }

    public void render(SpriteBatch batch) {
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getSprite().draw(batch);
    }

    public abstract void update(OrthographicCamera camera);

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setParentMap(Map parentMap) {
        this.parentMap = parentMap;
    }

    public Map getParentMap() {
        return this.parentMap;
    }
}
