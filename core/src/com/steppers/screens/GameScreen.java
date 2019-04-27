package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    UIButton backButton;

    Texture background;

    SpriteBatch batch;

    public GameScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        backButton = new UIButton(5, 457, 50, 50);
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);

        Pixmap px=new Pixmap(Gdx.files.internal("StoneWallTileableSmall.png"));
        background=new Texture(px);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        batch = new SpriteBatch();
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
        batch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        backButton.render(opacity);
        batch.end();
        shapeRenderer.end();
    }
}