package com.steppers.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;

public class SplashScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    public SplashScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        UIManager.Get().registerScreen(new MainMenuScreen(), "main_menu");
        UIManager.Get().transitionToScreen("main_menu", 1f);
    }

    @Override
    public void onTransitionedTo() {
        UIManager.Get().transitionToScreen("main_menu", 1f);
    }
}
