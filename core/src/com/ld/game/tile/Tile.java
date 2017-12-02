package com.ld.game.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.entity.Entity;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.map.Map;

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

    public void update(int x, int y, OrthographicCamera camera) {

    }

    public void collision(Entity collidingEntity, Vector2 position) {

    }

    public Sprite getSprite() {
        return this.sprite;
    }

}
