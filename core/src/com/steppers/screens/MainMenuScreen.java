package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.state.GameState;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class MainMenuScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;

    Texture background;

    UITextButton newGameButton;
    UITextButton continueGameButton;
    UITextButton quitButton;
    UITextButton fullsceenButton;

    GameState latestGameState;

    public MainMenuScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        latestGameState = null;

        quitButton = new UITextButton(50, 32, 200, 40, "Quit");
        quitButton.setAlignment(UIElement.Alignment.ALIGN_C);
        quitButton.convertToPercentagePos();
        quitButton.setHandler(() -> {
            Gdx.app.exit();
        });
        registerElement(quitButton);

        newGameButton = new UITextButton(50, 59, 200, 40, "New Game");
        newGameButton.setAlignment(UIElement.Alignment.ALIGN_C);
        newGameButton.convertToPercentagePos();
        newGameButton.setHandler(() -> {
            latestGameState = GameState.New();
            UIManager.Get().unregisterScreen("game_screen");
            UIManager.Get().registerScreen(new GameScreen(latestGameState), "game_screen");
            UIManager.Get().transitionToScreen("game_screen", 0.3f);
        });
        registerElement(newGameButton);

        continueGameButton = new UITextButton(50, 50, 200, 40, "Continue Game");
        continueGameButton.setAlignment(UIElement.Alignment.ALIGN_C);
        continueGameButton.convertToPercentagePos();
        continueGameButton.setHandler(() -> {
            if(latestGameState != null) {
                UIManager.Get().transitionToScreen("game_screen", 0.3f);
            }
        });
        registerElement(continueGameButton);

        fullsceenButton = new UITextButton(50, 41, 200, 40, "Fullscreen");
        fullsceenButton.setAlignment(UIElement.Alignment.ALIGN_C);
        fullsceenButton.convertToPercentagePos();
        fullsceenButton.setHandler(() -> {
            if(Gdx.graphics.isFullscreen())
            {
                Gdx.graphics.setWindowedMode(910, 512);
            } else {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode(Gdx.graphics.getMonitor()));
            }
        });
        registerElement(fullsceenButton);

        background = Renderer.Get().GetBackgroundTexture();
    }

    @Override
    public void update(float dt) {
        if(latestGameState == null) {
            continueGameButton.setTextColor(0.2f, 0, 0);
        } else {
            continueGameButton.setTextColor(0.8f, 0, 0);
        }
    }

    @Override
    public void render(float opacity) {
        spriteBatch.begin();
        spriteBatch.setColor(1,1,1, opacity);
        spriteBatch.draw(background,0,0, 0, 0, (int)Renderer.Get().GetCamera().viewportWidth, (int)Renderer.Get().GetCamera().viewportHeight);
        spriteBatch.end();

        // Enable alpha blending again
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        quitButton.render(opacity);
        newGameButton.render(opacity);
        continueGameButton.render(opacity);
        fullsceenButton.render(opacity);
        shapeRenderer.end();

        spriteBatch.begin();
        quitButton.renderText(opacity);
        newGameButton.renderText(opacity);
        continueGameButton.renderText(opacity);
        fullsceenButton.renderText(opacity);

        // Title layout
        GlyphLayout glyphLayout = Renderer.Get().GetGlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont96(), "LifeBlood");

        // Shadow followed by coloured text
        Renderer.Get().GetFont96().setColor(0.1f, 0.1f, 0.1f, 0.8f*opacity);
        Renderer.Get().GetFont96().draw(Renderer.Get().GetSpriteBatch(), "LifeBlood", (Renderer.Get().GetCamera().viewportWidth - glyphLayout.width)/2 + 4, 444 - 4);
        Renderer.Get().GetFont96().setColor(0.8f, 0, 0, opacity);
        Renderer.Get().GetFont96().draw(Renderer.Get().GetSpriteBatch(), "LifeBlood", (Renderer.Get().GetCamera().viewportWidth - glyphLayout.width)/2, 444);

        spriteBatch.end();
    }

    @Override
    public void onScreenResize() {
        quitButton.onScreenResize();
        newGameButton.onScreenResize();
        continueGameButton.onScreenResize();
        fullsceenButton.onScreenResize();
    }
}
