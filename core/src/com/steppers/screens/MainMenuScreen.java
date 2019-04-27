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

    UIButton quitButton;

    UIButton button;

    public MainMenuScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        quitButton = new UIButton(50, 97, 100, 60);
        quitButton.setAlignment(UIElement.Alignment.ALIGN_BC);
        quitButton.convertToPercentagePos();
        quitButton.setHandler(() -> {
            Gdx.app.exit();
        });
        registerElement(quitButton);

        button = new UIButton(50, 50, 100, 60);
        button.setAlignment(UIElement.Alignment.ALIGN_C);
        button.convertToPercentagePos();
        button.setHandler(() -> {
            UIManager.Get().transitionToScreen("splash");
        });
        registerElement(button);
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        quitButton.render(opacity);
        button.render(opacity);
        shapeRenderer.end();
    }
}
