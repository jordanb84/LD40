package com.ld.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ld.game.state.StateManager;
import com.ld.game.state.editor.StateMapEditor;
import com.ld.game.state.impl.StateLevel;
import com.ld.game.state.impl.StateTestbed;

public class Game extends ApplicationAdapter {

	private OrthographicCamera camera;

	private SpriteBatch batch;

	private StateManager stateManager;

	private boolean editing = false;

	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

		this.batch = new SpriteBatch();

		this.stateManager = new StateManager();

		StateTestbed testbedState = new StateTestbed(this.stateManager);
		StateMapEditor mapEditorState = new StateMapEditor(this.stateManager);
		StateLevel levelState = new StateLevel(this.stateManager, "maps/forest.dat");

		this.stateManager.registerState("testbed", testbedState);
		this.stateManager.registerState("editor", mapEditorState);
		this.stateManager.registerState("level", levelState);

		if(this.editing) {
			this.stateManager.setActiveState("editor");

			//camera.setToOrtho(false, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
		}else {
			camera.setToOrtho(false, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);

			this.stateManager.setActiveState("level");
		}
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle("LD40 - FPS " + Gdx.graphics.getFramesPerSecond());

		this.batch.setProjectionMatrix(this.camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.stateManager.update(this.camera);

		this.batch.begin();
		this.stateManager.render(this.batch, this.camera);
		this.batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
