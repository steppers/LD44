package com.steppers.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

public class UIBloodBank extends UIElement {

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;

    Texture bloodTexture;
    int maxBlood, blood;

    public UIBloodBank(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        bloodTexture = new Texture("blood_droplet.png");

        blood = 7;
        maxBlood = (int)width / bloodTexture.getWidth();
    }

    public void update(float dt) {}

    public void render(float opacity) {
        shapeRenderer.setColor(0.10f, 0.1f, 0.1f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y + 2, bounds.width-2, bounds.height-2);
    }

    public void renderText(float opacity) {
        for(int i = 0; i < blood; ++i) {
            spriteBatch.draw(bloodTexture, i * bloodTexture.getWidth() + i * 3, bounds.y + 3);
        }
    }
}
