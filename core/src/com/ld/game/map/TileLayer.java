package com.ld.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.tile.TileType;

/**
 * Represents a layer of tiles in a map, including functions to access and manipulate the contained tiles
 */
public class TileLayer {

    private TileType[][] layerTiles;

    public void render(SpriteBatch batch) {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int x = (tile * Map.TILE_WIDTH);
                int y = (tileRow * Map.TILE_HEIGHT);

                this.layerTiles[tile][tileRow].TILE.render(batch, x, y);
            }
        }

    }

    public void update() {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int x = (tile * Map.TILE_WIDTH);
                int y = (tileRow * Map.TILE_HEIGHT);

                this.layerTiles[tile][tileRow].TILE.update(x, y);
            }
        }
    }

    public TileType[][] getLayerTiles() {
        return this.layerTiles;
    }

    public void setLayerTiles(TileType[][] layerTiles) {
        this.layerTiles = (layerTiles);
    }
}
