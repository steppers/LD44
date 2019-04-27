package com.steppers.ld44;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.steppers.screens.SplashScreen;
import com.steppers.ui.UIManager;

public class LD44Game extends ApplicationAdapter {

	private UIManager uiManager;
	
	@Override
	public void create () {
		// Enable alpha blending
		Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
		Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

		// Setup the camera and inputs
		uiManager = UIManager.Get();
		Renderer.Get().SetupCamera();
		Gdx.input.setInputProcessor(uiManager);

		// Start the splash screen
		uiManager.registerScreen(new SplashScreen(), "splash");
		uiManager.setActiveScreen("splash");
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		uiManager.update(deltaTime);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		uiManager.render();
	}
	
	@Override
	public void dispose () {
		Renderer.Get().dispose();
	}
}
