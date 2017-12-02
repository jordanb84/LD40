package com.ld.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    private Sprite sprite;

    private Vector2 position;

    public Entity(Sprite sprite, Vector2 position) {
        this.setSprite(sprite);
        this.setPosition(position);
    }

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
}
