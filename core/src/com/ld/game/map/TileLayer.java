package com.ld.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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

    public void update(Map map, OrthographicCamera camera) {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int x = (tile * Map.TILE_WIDTH);
                int y = (tileRow * Map.TILE_HEIGHT);

                this.layerTiles[tile][tileRow].TILE.update(x, y, camera);

                if(map.getPlayer().getRectangle().overlaps(this.layerTiles[tile][tileRow].TILE.getRectangle()));
            }
        }
    }

    public TileType tileAt(Rectangle newPosition, boolean collide) {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int x = (tile * Map.TILE_WIDTH);
                int y = (tileRow * Map.TILE_HEIGHT);

                TileType type = this.layerTiles[tile][tileRow];

                if(newPosition.overlaps(new Rectangle(x, y, Map.TILE_WIDTH, Map.TILE_HEIGHT)) && type.SOLID) {
                    return type;
                }

                if(collide) {
                    type.TILE.collision(type.TILE.getMap().getPlayer(), new Vector2(x, y));
                }

                if(newPosition.overlaps(new Rectangle(x, y, Map.TILE_WIDTH, Map.TILE_HEIGHT))) {
                    if(!(type == TileType.Air)) {
                        System.out.println("On tile " + type.name() + " at " + x + "/" + y + "/" + type.TILE.toString());
                    }
                }
            }
        }

        return TileType.Air;
    }

    public void setTile(int x, int y, TileType type) {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int tileX = (tile * Map.TILE_WIDTH);
                int tileY = (tileRow * Map.TILE_HEIGHT);

                if(x == tileX && y == tileY) {
                    this.layerTiles[tile][tileRow] = type;
                }
            }
        }
    }

    public Vector2 toTilePosition(Vector2 position) {
        for(int tileRow = 0; tileRow < this.layerTiles.length; tileRow++) {
            for(int tile = 0; tile < this.layerTiles[tileRow].length; tile++) {
                int tileX = (tile * Map.TILE_WIDTH);
                int tileY = (tileRow * Map.TILE_HEIGHT);

                if(new Rectangle(position.x, position.y, 0, 0).overlaps(new Rectangle(tileX, tileY, Map.TILE_WIDTH, Map.TILE_HEIGHT))) {
                    return new Vector2(tileX, tileY);
                }
                //this.layerTiles[tile][tileRow] = type;
            }
        }

        return null;
    }

    public TileType[][] getLayerTiles() {
        return this.layerTiles;
    }

    public void setLayerTiles(TileType[][] layerTiles) {
        this.layerTiles = (layerTiles);
    }
}
