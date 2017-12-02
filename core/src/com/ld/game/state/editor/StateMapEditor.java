package com.ld.game.state.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.ld.game.map.Map;
import com.ld.game.map.TileLayer;
import com.ld.game.map.file.TileMapExporter;
import com.ld.game.map.file.TileMapImporter;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;
import com.ld.game.tile.TileType;
import org.json.JSONException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/** Excuse this mess, it's heckin' late and I'm in a rush to get stuff done **/
public class StateMapEditor extends State {

    private int focusedLayerIndex = 0;

    private List<TileLayer> tileLayers = new ArrayList<TileLayer>();

    private TileType selectedType = (TileType.Wall_Stone);

    private TileMapExporter mapExporter = new TileMapExporter();

    private TileMapImporter mapImporter = new TileMapImporter();

    private Vector3 mousePosition = new Vector3();

    public StateMapEditor(StateManager stateManager) {
        super(stateManager);
        this.generateBlankMap(TileType.Floor_Wood, 2);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(TileLayer tileLayer : this.tileLayers) {
            tileLayer.render(batch);
        }

        Sprite selectedTileSprite = (this.selectedType.TILE.getSprite());

        selectedTileSprite.setAlpha(0.5f);
        selectedTileSprite.setPosition(this.mousePosition.x, this.mousePosition.y);
        selectedTileSprite.draw(batch);
        selectedTileSprite.setAlpha(1);
    }

    @Override
    public void update(OrthographicCamera camera) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            try {
                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    this.mapExporter.exportToFile(this.tileLayers, fileChooser.getSelectedFile().getAbsolutePath());
                }
            }catch(JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.tileLayers = this.mapImporter.importFromJson(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }

        try {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                this.selectedType = (TileType.values()[this.selectedType.ordinal() + 1]);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                this.selectedType = (TileType.values()[this.selectedType.ordinal() - 1]);
            }
        }catch(ArrayIndexOutOfBoundsException noNextTile) {
            System.out.println("Invalid tile");
        }

         this.mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(this.mousePosition);

        Rectangle mouseRectangle = new Rectangle(this.mousePosition.x, this.mousePosition.y, 0, 0);

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

        Gdx.graphics.setTitle("LD40 - Selected: " + this.selectedType.name());
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

            TileLayer layer = new TileLayer();
            layer.setLayerTiles(tileTypes);

            this.tileLayers.add(layer);
        }
    }

    private TileLayer getFocusedLayer() {
        return this.tileLayers.get(this.focusedLayerIndex);
    }
}
