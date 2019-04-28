package com.steppers.gameui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.steppers.gamestate.Being;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

public class UIBeingCard extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private Being being;
    private float expandProgress;

    public UIBeingCard(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        expandProgress = 0.0f;
    }

    public void setBeing(Being being) {
        this.being = being;
    }

    public Being getBeing() {
        return being;
    }

    public void setExpandProgress(float progress)
    {
        expandProgress = progress;
    }

    public void render(float opacity) {
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(0.15f, 0.15f, 0.15f, opacity);
        shapeRenderer.rect( bounds.x+2, bounds.y+2, bounds.width-4, bounds.height-4);
    }

    public void renderText(float opacity) {
        if(being.getIcon() != null)
            Renderer.Get().GetSpriteBatch().draw(being.getIcon(), bounds.x+5, bounds.y + bounds.height - 5 - (bounds.width-10), bounds.width-10, bounds.width-10);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), Integer.toString(being.getLifeBlood()));

        float bloodTexWidth = Renderer.Get().GetBloodTexture().getWidth()/2;
        float totalBloodWidth = bloodTexWidth + 1.5f + glyphLayout.width;

        Renderer.Get().GetFont24().setColor(0.8f, 0, 0, opacity);
        Renderer.Get().GetFont24().draw(Renderer.Get().GetSpriteBatch(), Integer.toString(being.getLifeBlood()), bounds.x + (bounds.width - totalBloodWidth)/2 + bloodTexWidth + 1.5f, bounds.y + glyphLayout.height + 7);
        Renderer.Get().GetSpriteBatch().draw(Renderer.Get().GetBloodTexture(), bounds.x + (bounds.width - totalBloodWidth)/2, bounds.y + 4, Renderer.Get().GetBloodTexture().getWidth()/2, Renderer.Get().GetBloodTexture().getHeight()/2);

        if(expandProgress > 0.5f && being.getName() != null) {
            glyphLayout.setText(Renderer.Get().GetFont24(), being.getName(), Renderer.Get().GetBloodColor(), bounds.width - 6, Align.center, true);
            Renderer.Get().GetFont24().setColor(0.8f, 0, 0, opacity * ((expandProgress - 0.5f)*2));
            Renderer.Get().GetFont24().draw(Renderer.Get().GetSpriteBatch(), being.getName(), bounds.x + 3, bounds.y + bounds.height - 4, bounds.width - 6, Align.center, true);
        }
    }

}
