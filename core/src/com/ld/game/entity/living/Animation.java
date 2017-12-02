package com.ld.game.entity.living;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Animation {

    private HashMap<Direction, Sprite[]> sprites = new HashMap<Direction, Sprite[]>();

    private float elapsed;

    private Direction currentDirection;

    private int frame;

    private Sprite sprite;

    private Direction lastDirection;

    public Animation() {
        this.currentDirection = (Direction.RIGHT);
        this.lastDirection = (Direction.RIGHT);
    }

    public void render(SpriteBatch batch, Vector2 position) {
        if(this.sprite == null) {
            this.sprite = (this.getCurrentSprite()[this.frame]);
            System.out.println("Set to " + this.sprite);
        }

        this.sprite.setPosition(position.x, position.y);
        this.sprite.draw(batch);
    }

    public void update(OrthographicCamera camera, Direction direction) {
        this.elapsed += (1 * Gdx.graphics.getDeltaTime());

        this.currentDirection = (direction);

        if(this.currentDirection != this.lastDirection) {
            this.elapsed = 0.4f;
            this.frame = 0;
        }

        if(this.elapsed >= 0.4f) {
            this.elapsed = 0;

            this.frame++;
            try {
                this.sprite = (this.getCurrentSprite()[this.frame]);
            }catch(Exception e) {
                this.frame = 0;
                this.sprite = (this.getCurrentSprite()[this.frame]);
            }
        }

        this.lastDirection = (this.currentDirection);
    }

    public void setFramesForDirection(Direction direction, String ... spritePaths) {
        Sprite[] sprites = new Sprite[spritePaths.length];

        for(int spritePath = 0; spritePath < spritePaths.length; spritePath++) {
            sprites[spritePath] = new Sprite(new Texture(Gdx.files.internal(spritePaths[spritePath])));
        }

        this.sprites.put(direction, sprites);
    }

    public Sprite[] getCurrentSprite() {
        return this.sprites.get(this.currentDirection);
    }

    public float getWidth() {
        return this.sprite.getWidth();
    }

    public float getHeight() {
        return this.sprite.getHeight();
    }
}