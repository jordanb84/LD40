package com.ld.game.entity.living;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;
import com.ld.game.map.Map;

public abstract class EntityLiving extends Entity {

    private int health;

    private float speed;

    private Direction direction = (Direction.UP);

    private Animation animation;

    public EntityLiving(Map parentMap, Vector2 position, int health) {
        super(parentMap, null, position);
        this.setHealth(health);

        this.animation = new Animation();
        this.setupAnimation(this.animation);
    }

    /**
     * Initiate entity animation
     * @return Default animation frame
     */
    public abstract void setupAnimation(Animation animation);

    @Override
    public void render(SpriteBatch batch) {
        this.animation.render(batch, this.getPosition());
    }

    @Override
    public void update(OrthographicCamera camera) {
        if(this.getHealth() <= 0) {
            this.die();
        }
    }

    public void die() {
        this.getParentMap().despawnEntity(this);
    }

    public void setHealth(int health) {
        this.health = (health);
    }

    public int getHealth() {
        return this.health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setDirection(Direction direction) {
        this.direction = (direction);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Vector2 move(Direction direction, OrthographicCamera camera) {
        Vector2 force = new Vector2();

        Direction newDirection = null;

        switch(direction) {
            case UP:
                force.add(0, this.getSpeed() + 1 * Gdx.graphics.getDeltaTime());
                newDirection = (Direction.UP);
                break;
            case DOWN:
                force.add(0, -this.getSpeed() + 1 * Gdx.graphics.getDeltaTime());
                newDirection = (Direction.DOWN);
                break;
            case RIGHT:
                force.add(this.getSpeed() + 1 * Gdx.graphics.getDeltaTime(), 0);
                newDirection = (Direction.RIGHT);
                break;
            case LEFT:
                force.add(-this.getSpeed() + 1 * Gdx.graphics.getDeltaTime(), 0);
                newDirection = (Direction.LEFT);
                break;
        }

        Rectangle newPosition = new Rectangle(this.getPosition().x + force.x, this.getPosition().y + force.y, this.getWidth(), this.getHeight());

        if(!this.getParentMap().willCollideAt(newPosition, camera)) {
            this.getPosition().add(force);
            this.setDirection(newDirection);

            this.getAnimation().update(camera, this.getDirection());
        }else{
            force.set(0, 0);
        }
        return force;
    }

    public Animation getAnimation() {
        return animation;
    }

    public float getWidth() {
        return this.getAnimation().getWidth();
    }

    public float getHeight() {
        return this.getAnimation().getHeight();
    }
}
