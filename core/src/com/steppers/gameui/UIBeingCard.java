package com.steppers.gameui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gamestate.Being;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

public class UIBeingCard extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private Being being;

    public UIBeingCard(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();
    }

    public void setBeing(Being being) {
        this.being = being;
    }

    public Being getBeing() {
        return being;
    }

    public void render(float opacity) {
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(0.15f, 0.15f, 0.15f, opacity);
        shapeRenderer.rect( bounds.x+2, bounds.y+2, bounds.width-4, bounds.height-4);
    }

    public void renderText(float opacity) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), Integer.toString(being.getLifeBlood()));

        Renderer.Get().GetFont24().setColor(1, 0, 0, opacity);
        Renderer.Get().GetFont24().draw(Renderer.Get().GetSpriteBatch(), Integer.toString(being.getLifeBlood()), bounds.x + (bounds.width - glyphLayout.width)/2, bounds.y + (bounds.height + glyphLayout.height)/2);
    }

}
