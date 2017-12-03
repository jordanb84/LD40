package com.ld.game.inventory;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.inventory.item.InventoryItem;

public class InventorySlot {

    private Sprite sprite;

    private InventoryItem item;

    private Vector2 position;

    private Inventory parentInventory;

    public InventorySlot(Vector2 position, Sprite sprite, Inventory parentInventory) {
        this.sprite = sprite;
        this.setPosition(position);
        this.setParentInventory(parentInventory);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.sprite.setPosition(this.getPosition().x, this.getPosition().y);
        this.sprite.draw(batch);

        if(this.item != null) {
            item.render(batch);
        }
    }

    public void update(EntityPlayer player, OrthographicCamera camera) {
        if(this.getItem() != null) {
            this.getItem().update(player, camera);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public Inventory getParentInventory() {
        return parentInventory;
    }

    public void setParentInventory(Inventory parentInventory) {
        this.parentInventory = parentInventory;
    }


}