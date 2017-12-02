package com.ld.game.entity.living;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;
import com.ld.game.map.Map;

public abstract class EntityLiving extends Entity {

    private int health;

    public EntityLiving(Map parentMap, Sprite sprite, Vector2 position, int health) {
        super(parentMap, sprite, position);
        this.setHealth(health);
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

}
