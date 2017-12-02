package com.ld.game.tile.impl;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class TileShovel extends Tile {

    public TileShovel(String spritePath) {
        super(spritePath);
    }

    @Override
    public void collision(Entity collidingEntity, Vector2 position) {
        super.collision(collidingEntity, position);
        collidingEntity.getParentMap().setTile(1, (int) position.x, (int) position.y, TileType.Air);
    }
}
