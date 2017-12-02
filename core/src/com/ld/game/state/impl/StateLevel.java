package com.ld.game.state.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.map.Map;
import com.ld.game.map.TileLayer;
import com.ld.game.map.file.TileMapImporter;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;
import com.ld.game.state.editor.StateMapEditor;

import java.util.List;

public class StateLevel extends State {

    private Map map;

    public StateLevel(StateManager stateManager, String mapPath) {
        super(stateManager);
        TileMapImporter mapImporter = new TileMapImporter();

        List<TileLayer> tileLayers = mapImporter.importFromJson(Gdx.files.internal(mapPath).readString());

        this.map = new Map(tileLayers);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.map.render(batch, camera);
    }

    @Override
    public void update(OrthographicCamera camera) {
        this.map.update(camera);
    }
}
