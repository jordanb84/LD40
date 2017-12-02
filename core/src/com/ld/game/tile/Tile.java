package com.ld.game.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;

public class Tile {

    private Sprite sprite;

    public Tile(String spritePath) {
        System.out.println(spritePath);
        this.sprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
    }

    public void render(SpriteBatch batch, int x, int y) {
        this.sprite.setPosition(x, y);
        this.sprite.draw(batch);
    }

    public void update(int x, int y) {

    }

}
