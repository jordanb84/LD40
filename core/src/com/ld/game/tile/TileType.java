package com.ld.game.tile;

public enum TileType {

    Air("tile/air.png", false), Floor_Wood("tile/floor_wood.png", false), Wall_Stone("tile/wall_stone.png", true);

    TileType(String spritePath, boolean solid) {
        this.SOLID = solid;
        this.TILE = new Tile(spritePath);
    }

    public boolean SOLID;

    public Tile TILE;
}
