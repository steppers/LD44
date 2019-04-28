package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.steppers.ld44.Renderer;

public class UITextButton extends UIButton {

    String text;
    Color textColor;
    Vector2 textSize;

    public UITextButton(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        textColor = new Color(Renderer.Get().GetBloodColor());

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), text);
        textSize = new Vector2(glyphLayout.width, glyphLayout.height);
    }

    public UITextButton(float x, float y, float width, float height, Color color, String text) {
        super(x, y, width, height, color);
        this.text = text;
        textColor = new Color(Renderer.Get().GetBloodColor());

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Renderer.Get().GetFont24(), text);
        textSize = new Vector2(glyphLayout.width, glyphLayout.height);
    }

    public void setTextColor(float r, float g, float b)
    {
        textColor.set(r, g, b, 1.0f);
    }

    public void renderText(float opacity) {
        Renderer.Get().GetFont24().setColor(textColor.r, textColor.g, textColor.b, opacity);
        Renderer.Get().GetFont24().draw(Renderer.Get().GetSpriteBatch(), text, bounds.x + (bounds.width - textSize.x)/2, bounds.y + (bounds.height + textSize.y)/2);
    }
}
