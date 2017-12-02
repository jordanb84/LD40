package com.ld.game.tile;

public enum TileType {

    Air("tile/air.png", false), Grass_Top("tile/grass_top.png", false), Sand("tile/sand.png", false), Tree("tile/tree.png", true),
    Tree1("tile/tree1.png", true), Tree2("tile/tree2.png", true), Water("tile/water.png", true),
    Water_Edge("tile/water_edge.png", true);

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
