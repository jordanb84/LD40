package com.ld.game.map.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ld.game.map.TileLayer;
import com.ld.game.tile.TileType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TileMapExporter {

    public void exportToFile(List<TileLayer> tileLayers, String path) throws JSONException {
        Json exporterJson = new Json();

        String mapJsonData = (exporterJson.toJson(tileLayers));

        Gdx.files.external(path).writeString(mapJsonData, false);
    }

}
