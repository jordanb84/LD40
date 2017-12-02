package com.ld.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ld.game.dialog.Dialog;
import com.ld.game.entity.Entity;
import com.ld.game.tile.TileType;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Entity> entities = new ArrayList<Entity>();

    /** Queues so entities can be spawned/despawned properly during an entity loop without ConcurrentModificationException **/

    private List<Entity> entitySpawnQueue = new ArrayList<Entity>();
    private List<Entity> entityDespawnQueue = new ArrayList<Entity>();

    /** Constants for accessibility across map related actors **/
    public static final int MAP_WIDTH = (50);
    public static final int MAP_HEIGHT = (50);

    public static final int TILE_WIDTH = (8);
    public static final int TILE_HEIGHT = (8);

    /** For abstraction, tile layers are each their own actor **/
    private List<TileLayer> tileLayers;

    private Dialog dialog;

    public Map(List<TileLayer> tileLayers) {
        this.tileLayers = (tileLayers);
        this.dialog = new Dialog();

        this.dialog.startWithDialog("Nick", "I've discovered the sacred forest of lava lamps!");
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.render(batch);
        }

        for(Entity entity : this.entities) {
            entity.render(batch);
        }

        batch.end();
        this.dialog.render();
        batch.begin();
    }

    public void update(OrthographicCamera camera) {

        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.update(camera);
        }

        this.entities.addAll(this.entitySpawnQueue);
        this.entitySpawnQueue.clear();

        this.entities.removeAll(this.entityDespawnQueue);
        this.entityDespawnQueue.clear();

        for(Entity entity : this.entities) {
            entity.update(camera);
        }

        this.dialog.update();
    }

    public void spawnEntity(Entity entity) {
        this.entitySpawnQueue.add(entity);
    }

    public void despawnEntity(Entity entity) {
        this.entityDespawnQueue.add(entity);
    }

    public boolean willCollideAt(Rectangle newPosition, OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            if(tileLayer.tileAt(newPosition).SOLID) {
                System.out.println(tileLayer.tileAt(newPosition).name());
                return true;
            }
        }

        return false;
    }

    public Vector2 toTilePosition(Vector2 position) {
        return this.tileLayers.get(0).toTilePosition(position);
    }

    public TileType tileAt(Rectangle position) {
        for(TileLayer tileLayer : this.tileLayers) {
            return tileLayer.tileAt(position);
        }

        return null;
    }

    public void setTile(int layer, int x, int y, TileType type) {
        this.tileLayers.get(layer).setTile(x, y, type);
    }

    public TileType solidTileAt(Rectangle position) {
        for(TileLayer tileLayer : this.tileLayers) {
            TileType type = tileLayer.tileAt(position);
            if(type.SOLID) {
                return type;
            }
        }

        return null;
    }

    public Dialog getDialog() {
        return this.dialog;
    }
}
