package com.ld.game.tile.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.dialog.Dialog;
import com.ld.game.entity.Entity;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.tile.Tile;
import com.ld.game.tile.TileType;

public class TilePathBlockingRock extends Tile {

    public TilePathBlockingRock(String spritePath) {
        super(spritePath);
    }

    @Override
    public void collision(Entity collidingEntity, Vector2 position) {
        super.collision(collidingEntity, position);
        EntityPlayer player = (EntityPlayer) collidingEntity;

        if(player.getInventory().hasItem("shovel")) {
            collidingEntity.getParentMap().setTile(1, 80, 152, TileType.Grass_Top);
        }else {
            Dialog dialog = (collidingEntity.getParentMap().getDialog());

            dialog.startWithDialog("Nick", "Looks like there's one over there behind this rock...", "Seems pretty heavy. Maybe I can  find something to move it with.");

            collidingEntity.getParentMap().setTile(1, 160, 152, TileType.Shovel);
        }
    }
}
