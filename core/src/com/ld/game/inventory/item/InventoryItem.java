package com.ld.game.inventory.item;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.entity.living.impl.EntityPlayer;

public abstract class InventoryItem {

    private String name;

    private String pickupMessage;

    public InventoryItem(String name) {
        this.setName(name);
    }

    public void render(SpriteBatch batch) {

    }

    public abstract void update(EntityPlayer player, OrthographicCamera camera);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickupMessage() {
        return pickupMessage;
    }

    public void setPickupMessage(String pickupMessage) {
        this.pickupMessage = pickupMessage;
    }

}