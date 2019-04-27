package com.steppers.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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

        blood = 0;
        maxBlood = 10;

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), maxBlood + "/" + maxBlood);

        bounds.width = 1 + (maxBlood * bloodTexture.getWidth()) + (maxBlood*3) + glyphLayout.width + 6;
        addBlood(7);
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
            spriteBatch.draw(bloodTexture, 1 + i * bloodTexture.getWidth() + i * 3, bounds.y + 3);
        }

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), maxBlood + "/" + maxBlood);
        float textX = (1 + maxBlood * bloodTexture.getWidth() + maxBlood * 3);

        Renderer.Get().GetFont24().setColor(1, 0, 0, opacity);
        Renderer.Get().GetFont24().draw(Renderer.Get().GetSpriteBatch(), blood + "/" + maxBlood, textX + ((bounds.width - textX) - glyphLayout.width)/2, bounds.y + 4 + ((bounds.height-3) + glyphLayout.height)/2);
    }

    public int getBlood() {
        return blood;
    }

    public void useBlood(int amount) {
        if(amount > blood)
            System.err.println("Using too much blood! The blood bank can't supply this much!");
        blood -= amount;
        blood = Math.max(blood, 0);
    }

    public void addBlood(int amount) {
        blood += amount;
        blood = Math.min(blood, maxBlood);
    }
}
