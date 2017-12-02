package com.ld.game.entity.living.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.living.Direction;
import com.ld.game.entity.living.EntityLiving;
import com.ld.game.map.Map;

public class EntityPlayer extends EntityLiving {

    public EntityPlayer(Map parentMap, Vector2 position) {
        super(parentMap, new Sprite(new Texture(Gdx.files.internal("entity/player_up.png"))), position, 10);
        this.setSpeed(0.3f);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        boolean pressingMovementKey = false;

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(!pressingMovementKey) {
                this.move(Direction.UP, camera);
                pressingMovementKey = true;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(!pressingMovementKey) {
                this.move(Direction.DOWN, camera);
                pressingMovementKey = true;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(!pressingMovementKey) {
                this.move(Direction.LEFT, camera);
                pressingMovementKey = true;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(!pressingMovementKey) {
                this.move(Direction.RIGHT, camera);
                pressingMovementKey = true;
            }
        }

        camera.position.set(this.getPosition().x, this.getPosition().y, 0);
        camera.update();
    }
}
