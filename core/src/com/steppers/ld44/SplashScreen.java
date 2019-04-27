package com.steppers.ld44;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SplashScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    public SplashScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        UIManager.Get().registerScreen(new MMScreen(), "main_menu");
        UIManager.Get().transitionToScreen("main_menu");
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, opacity);
        shapeRenderer.rect( 100, 100, 100, 100);
        shapeRenderer.end();
    }
}
