package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gameui.UIBloodBank;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIMapDisplay;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    UIButton backButton;
    UIBloodBank bloodBank;

    Texture background;

    UIMapDisplay mapDisplay;

    public GameScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        batch = Renderer.Get().GetSpriteBatch();

        backButton = new UITextButton(5, 5, 60, 50, "Back");
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);

        mapDisplay = new UIMapDisplay(50, 300, 200, 40);
        mapDisplay.convertToPercentagePos();

        for(UIButton button : mapDisplay.getButtons()){
            registerElement(button);
        }

        background = Renderer.Get().GetBackgroundTexture();

        bloodBank = new UIBloodBank(0, 512, 0, 36);
        bloodBank.setAlignment(UIElement.Alignment.ALIGN_TL);
    }

    @Override
    public void render(float opacity) {
        batch.begin();
        batch.setColor(1,1,1, opacity);
        batch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        // Enable alpha blending again
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backButton.render(opacity);
        for(UIButton button : mapDisplay.getButtons()){
            button.render(opacity);
        }
        bloodBank.render(opacity);
        shapeRenderer.end();

        batch.begin();
        backButton.renderText(opacity);
        bloodBank.renderText(opacity);
        batch.end();
    }
}