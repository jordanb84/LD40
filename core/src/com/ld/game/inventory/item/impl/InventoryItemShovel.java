package com.ld.game.inventory.item.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.inventory.item.InventoryItem;

public class InventoryItemShovel extends InventoryItem {

    public InventoryItemShovel() {
        super("shovel");
    }

    @Override
    public void update(EntityPlayer player, OrthographicCamera camera) {

    }
}
