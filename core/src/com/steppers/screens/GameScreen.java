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

        mapDisplay = new UIMapDisplay(50, 50, 500, 40);
        mapDisplay.convertToPercentagePos();
        /*gameButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("game_screen", 0.3f);
        });*/
        for(UIButton button : mapDisplay.getButtons()){
            registerElement(button);
        }

        Pixmap px=new Pixmap(Gdx.files.internal("StoneWallTileableSmall.png"));
        background=new Texture(px);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bloodBank = new UIBloodBank(0, 512, 255, 36);
        bloodBank.setAlignment(UIElement.Alignment.ALIGN_TL);
    }

    @Override
    public void render(float opacity) {
        batch.begin();
        batch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

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