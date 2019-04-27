package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.steppers.ld44.Renderer;

public class UITextButton extends UIButton {

    String text;
    Color textColor;

    public UITextButton(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        textColor = new Color(0,0,0,1);
    }

    public UITextButton(float x, float y, float width, float height, Color color, String text) {
        super(x, y, width, height, color);
        this.text = text;
        textColor = new Color(0,0,0,1);
    }

    public void setTextColor(float r, float g, float b)
    {
        textColor.set(r, g, b, 1.0f);
    }

    public void renderText(float opacity) {
        Renderer.Get().GetFont().setColor(textColor.r, textColor.g, textColor.b, opacity);
        Renderer.Get().GetFont().draw(Renderer.Get().GetSpriteBatch(), text, bounds.x + bounds.width/2, bounds.y + bounds.height/2 - Renderer.Get().GetFont().getXHeight()/2, bounds.width, Align.center, true);
    }
}
