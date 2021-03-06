package com.ld.game.entity.living.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.living.Animation;
import com.ld.game.entity.living.Direction;
import com.ld.game.entity.living.EntityLiving;
import com.ld.game.inventory.Inventory;
import com.ld.game.map.Map;

public class EntityPlayer extends EntityLiving {

    private Inventory inventory;

    public EntityPlayer(Map parentMap, Vector2 position) {
        super(parentMap, position, 10);
        this.setSpeed(0.3f);
        this.inventory = new Inventory(5);
    }

    @Override
    public void setupAnimation(Animation animation) {
        animation.setFramesForDirection(Direction.UP, "entity/player/player_up1.png", "entity/player/player_up2.png", "entity/player/player_up3.png", "entity/player/player_up4.png");
        animation.setFramesForDirection(Direction.DOWN, "entity/player/player_down1.png", "entity/player/player_down2.png", "entity/player/player_down3.png", "entity/player/player_down4.png");
        animation.setFramesForDirection(Direction.LEFT, "entity/player/player_left1.png", "entity/player/player_left2.png", "entity/player/player_left3.png", "entity/player/player_left4.png");
        animation.setFramesForDirection(Direction.RIGHT, "entity/player/player_right1.png", "entity/player/player_right2.png", "entity/player/player_right3.png", "entity/player/player_right4.png");
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        if(!this.getParentMap().getDialog().inProgress()) {
            this.pollMovementInput(camera);
        }

        camera.position.set(this.getPosition().x, this.getPosition().y, 0);
        camera.update();
    }

    private void pollMovementInput(OrthographicCamera camera) {
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
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public float getWidth() {
        return super.getWidth() - 1;
    }
}
