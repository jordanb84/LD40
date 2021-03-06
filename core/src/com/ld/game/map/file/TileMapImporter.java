package com.ld.game.map.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ld.game.map.TileLayer;

import java.util.ArrayList;
import java.util.List;

public class TileMapImporter {

    public List<TileLayer> importFromJsonFile(String path) {
        Json importerJson = new Json();

        String mapJsonData = (Gdx.files.external(path).readString());

        ArrayList<TileLayer> layers = importerJson.fromJson(ArrayList.class, mapJsonData);

        return layers;
    }

    public List<TileLayer> importFromJson(String json) {
        Json importerJson = new Json();

        ArrayList<TileLayer> layers = importerJson.fromJson(ArrayList.class, json);

        return layers;
    }

}
