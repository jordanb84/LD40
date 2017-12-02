package com.ld.game.state.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.state.State;
import com.ld.game.state.StateManager;

public class StateTestbed extends State {

    public StateTestbed(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {

    }

    @Override
    public void update(OrthographicCamera camera) {

    }
}
