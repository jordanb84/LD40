package com.ld.game.tile;

import com.ld.game.tile.impl.TilePathBlockingRock;
import com.ld.game.tile.impl.TileShovel;

public enum TileType {

    Air("tile/air2.png", false), Grass_Top("tile/grass2.png", false), Sand("tile/grass2.png", false), Tree("tile/tree2_1.png", true),
    Tree1("tile/tree2_1.png", true), Tree2("tile/tree2_1.png", true), Water("tile/water2.png", true),
    Water_Edge("tile/water_edge.png", true), Rock(new TilePathBlockingRock("tile/rock2.png"), true),
    Lava_Lamp_blue("tile/lamp3.png", true), Shovel(new TileShovel("tile/shovel2.png"), true);

    TileType(String spritePath, boolean solid) {
        this.SOLID = solid;
        this.TILE = new Tile(spritePath);
    }

    TileType(Tile tile, boolean solid) {
        this.SOLID = solid;
        this.TILE = tile;
    }

    public boolean SOLID;

    public Tile TILE;
}
