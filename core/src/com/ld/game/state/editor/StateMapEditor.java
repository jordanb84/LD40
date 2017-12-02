package com.ld.game.state.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.ld.game.map.Map;
import com.ld.game.map.TileLayer;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;
import com.ld.game.tile.TileType;

import java.util.ArrayList;
import java.util.List;

/** Excuse this mess, it's heckin' late and I'm in a rush to get stuff done **/
public class StateMapEditor extends State {

    private int focusedLayerIndex = 0;

    private List<TileLayer> tileLayers = new ArrayList<TileLayer>();

    private TileType selectedType = (TileType.Wall_Stone);

    public StateMapEditor(StateManager stateManager) {
        super(stateManager);
        this.generateBlankMap(TileType.Floor_Wood, 2);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.render(batch);
        }
    }

    @Override
    public void update(OrthographicCamera camera) {
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePosition);

        Rectangle mouseRectangle = new Rectangle(mousePosition.x, mousePosition.y, 0, 0);

        TileType[][] focusedLayerTiles = (this.getFocusedLayer().getLayerTiles());

        for(int tileRow = 0; tileRow < focusedLayerTiles.length; tileRow++) {
            for(int tile = 0; tile < focusedLayerTiles[tileRow].length; tile++) {
                int x = (tile * Map.TILE_WIDTH);
                int y = (tileRow * Map.TILE_HEIGHT);

                Rectangle tileRectangle = new Rectangle(x, y, Map.TILE_WIDTH, Map.TILE_HEIGHT);

                if(mouseRectangle.overlaps(tileRectangle)) {
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                        this.getFocusedLayer().getLayerTiles()[tile][tileRow] = (this.selectedType);
                    }
                }
            }
        }
    }

    private void generateBlankMap(TileType floorTile, int totalLayers) {
        for(int layerGenerated = 0; layerGenerated < totalLayers; layerGenerated++) {
            TileType[][] tileTypes = new TileType[Map.MAP_WIDTH][Map.MAP_HEIGHT];

            for(int tileRow = 0; tileRow < Map.MAP_HEIGHT; tileRow++) {
                for(int tile = 0; tile < Map.MAP_WIDTH; tile++) {
                    if(layerGenerated == 0) {
                        tileTypes[tile][tileRow] = (floorTile);
                    }else {
                        tileTypes[tile][tileRow] = (TileType.Air);
                    }

                    System.out.println("Set tile " + tile + " in row " + tileRow);
                }
            }

            this.tileLayers.add(new TileLayer(tileTypes));
        }
    }

    private TileLayer getFocusedLayer() {
        return this.tileLayers.get(this.focusedLayerIndex);
    }
}
