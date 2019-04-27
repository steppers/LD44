package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class MainMenuScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;

    UIButton gameButton;
    UIButton quitButton;

    public MainMenuScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        quitButton = new UITextButton(50, 97, 100, 60, "Quit");
        quitButton.setAlignment(UIElement.Alignment.ALIGN_BC);
        quitButton.convertToPercentagePos();
        quitButton.setHandler(() -> {
            Gdx.app.exit();
        });
        registerElement(quitButton);

        gameButton = new UITextButton(50, 50, 100, 60, "New Game");
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


        Renderer.Get().GetSpriteBatch().begin();
        quitButton.renderText(opacity);
        gameButton.renderText(opacity);
        Renderer.Get().GetSpriteBatch().end();
    }
}
