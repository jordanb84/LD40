package com.ld.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class StateManager {

    private HashMap<String, State> states = new HashMap<String, State>();

    private String activeStateKey;

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.getActiveState().render(batch, camera);
    }

    public void update(OrthographicCamera camera) {
        this.getActiveState().update(camera);
    }

    public void registerState(String key, State state) {
        this.states.put(key, state);
    }

    public void setActiveState(String stateKey) {
        this.activeStateKey = (stateKey);
    }

    private State getActiveState() {
        return this.states.get(this.activeStateKey);
    }

}
