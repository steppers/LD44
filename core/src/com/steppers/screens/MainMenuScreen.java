package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;

public class MainMenuScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    UIButton gameButton;
    UIButton quitButton;

    public MainMenuScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        quitButton = new UIButton(50, 97, 100, 60);
        quitButton.setAlignment(UIElement.Alignment.ALIGN_BC);
        quitButton.convertToPercentagePos();
        quitButton.setHandler(() -> {
            Gdx.app.exit();
        });
        registerElement(quitButton);

        gameButton = new UIButton(50, 50, 100, 60);
        gameButton.setAlignment(UIElement.Alignment.ALIGN_C);
        gameButton.convertToPercentagePos();
        gameButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("game_screen", 0.3f);
        });
        registerElement(gameButton);
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        quitButton.render(opacity);
        gameButton.render(opacity);
        shapeRenderer.end();
    }
}
