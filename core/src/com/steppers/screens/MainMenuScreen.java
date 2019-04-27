package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIMapDisplay;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class MainMenuScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;

    Texture background;

    UIButton gameButton;
    UIButton quitButton;

    public MainMenuScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        quitButton = new UITextButton(50, 3, 150, 40, "Quit");
        quitButton.setAlignment(UIElement.Alignment.ALIGN_BC);
        quitButton.convertToPercentagePos();
        quitButton.setHandler(() -> {
            Gdx.app.exit();
        });
        registerElement(quitButton);

        gameButton = new UITextButton(50, 50, 150, 40, "New Game");
        gameButton.setAlignment(UIElement.Alignment.ALIGN_C);
        gameButton.convertToPercentagePos();
        gameButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("game_screen", 0.3f);
        });
        registerElement(gameButton);

        background = Renderer.Get().GetBackgroundTexture();
    }

    @Override
    public void render(float opacity) {
        spriteBatch.begin();
        spriteBatch.setColor(1,1,1, opacity);
        spriteBatch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.end();

        // Enable alpha blending again
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        quitButton.render(opacity);
        gameButton.render(opacity);
        shapeRenderer.end();

        spriteBatch.begin();
        quitButton.renderText(opacity);
        gameButton.renderText(opacity);
        spriteBatch.end();
    }
}
