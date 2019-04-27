package com.steppers.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    UIButton backButton;

    public GameScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        backButton = new UIButton(5, 457, 50, 50);
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backButton.render(opacity);
        shapeRenderer.end();
    }
}