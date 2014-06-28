package com.tiarsoft.tutorialAdmob;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyNewGame extends ApplicationAdapter {

	AdHandler adHandler;

	public MyNewGame(AdHandler adHandler) {
		this.adHandler = adHandler;
	}

	Stage stage;
	ImageButton mostrarAnuncio;

	@Override
	public void create() {
		stage = new Stage(new StretchViewport(480, 800));
		Gdx.input.setInputProcessor(stage);
		mostrarAnuncio = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("badlogic.jpg"))));

		mostrarAnuncio.setSize(100, 100);
		mostrarAnuncio.setPosition(190, 350);
		mostrarAnuncio.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				mostrarAnuncio.setY(mostrarAnuncio.getY() - 5);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				mostrarAnuncio.setY(mostrarAnuncio.getY() + 5);
				adHandler.showInterstitial();

			}
		});

		stage.addActor(mostrarAnuncio);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
}
