package com.ld.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.entity.living.impl.EntityPlayer;
import com.ld.game.inventory.item.InventoryItem;

public class Inventory {

    private List<InventorySlot> slots = new ArrayList<InventorySlot>();

    public Inventory(int slots) {

        Sprite slotSprite = new Sprite(new Texture(Gdx.files.internal("tile/air.png")));

        for(int slot = 0; slot < slots; slot++) {
            this.slots.add(new InventorySlot(new Vector2(10 + slot * slotSprite.getWidth(), slotSprite.getHeight()), slotSprite, this));
        }
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(InventorySlot slot : this.slots) {
            slot.render(batch, camera);
        }
    }

    public void update(EntityPlayer player, OrthographicCamera camera) {
        for(InventorySlot slot : this.slots) {
            slot.update(player, camera);
        }
    }

    public void addItem(InventoryItem item) {
        for(InventorySlot slot : this.slots) {
            if(slot.getItem() == null) {
                slot.setItem(item);
                break;
            }
        }
    }

    public void removeItem(InventoryItem item) {
        for(InventorySlot slot : this.slots) {
            if(slot.getItem().getName().equals(item.getName())) {
                slot.setItem(null);
                break;
            }
        }
    }

    public void removeItem(String name) {
        for(InventorySlot slot : this.slots) {
            if(slot.getItem().getName().equals(name)) {
                slot.setItem(null);
                break;
            }
        }
    }

    public boolean hasItem(String name) {
        for(InventorySlot slot : this.slots) {
            if(slot.getItem() != null) {
                if(slot.getItem().getName().equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

}