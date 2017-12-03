package com.ld.game.tile.impl;

import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.Entity;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.inventory.item.impl.InventoryItemShovel;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class TileShovel extends Tile {

    public TileShovel(String spritePath) {
        super(spritePath);
    }

    @Override
    public void collision(Entity collidingEntity, Vector2 position) {
        super.collision(collidingEntity, position);
        collidingEntity.getParentMap().getDialog().startWithDialog("test", "hey");

        Vector2 pos = collidingEntity.getParentMap().toTilePosition(position);
        collidingEntity.getParentMap().setTile(1, 160, 152, TileType.Air);

        collidingEntity.getParentMap().getPlayer().getInventory().addItem(new InventoryItemShovel());
    }
}
