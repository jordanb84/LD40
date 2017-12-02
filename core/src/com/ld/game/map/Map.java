package com.ld.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.tile.TileType;

import java.util.List;

public class Map {

    /** Constants for accessibility across map related actors **/
    public static final int MAP_WIDTH = (10);
    public static final int MAP_HEIGHT = (10);

    public static final int TILE_WIDTH = (32);
    public static final int TILE_HEIGHT = (32);

    /** For abstraction, tile layers are each their own actor **/
    private List<TileLayer> tileLayers;

    public Map(List<TileLayer> tileLayers) {
        this.tileLayers = (tileLayers);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.render(batch);
        }
    }

    public void update(OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.update();
        }
    }
}
