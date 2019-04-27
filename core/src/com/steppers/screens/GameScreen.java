package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gameui.UIBloodBank;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    UIButton backButton;
    UIBloodBank bloodBank;

    Texture background;

    public GameScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        batch = Renderer.Get().GetSpriteBatch();

        backButton = new UITextButton(5, 457, 60, 50, "Back");
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);

        Pixmap px=new Pixmap(Gdx.files.internal("StoneWallTileableSmall.png"));
        background=new Texture(px);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bloodBank = new UIBloodBank(0, 0, 255, 36);
    }

    @Override
    public void render(float opacity) {
        batch.begin();
        batch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backButton.render(opacity);
        bloodBank.render(opacity);
        shapeRenderer.end();

        batch.begin();
        backButton.renderText(opacity);
        batch.end();
    }
}