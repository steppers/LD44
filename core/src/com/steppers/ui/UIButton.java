package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;

public class UIButton extends UIElement {

    public interface ButtonHandler {
        void onActivate();
    }

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;
    Color color;
    Color baseColor;
    ButtonHandler handler;
    boolean active;

    boolean mouseDown;
    Texture icon;

    public UIButton(float x, float y, float width, float height, Texture icon) {
        this(x, y, width, height, new Color(0.2f, 0.2f, 0.2f, 1.0f), icon);
    }

    public UIButton(float x, float y, float width, float height) {
        this(x, y, width, height, new Color(0.2f, 0.2f, 0.2f, 1.0f), null);
    }

    public UIButton(float x, float y, float width, float height, Color color) {
        this(x, y, width, height, color, null);
    }

    public UIButton(float x, float y, float width, float height, Color color, Texture icon) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        baseColor = color;
        this.color = new Color(baseColor);
        mouseDown = false;
        handler = null;
        this.icon = icon;
        active = true;
    }

    public void setActive(boolean active) {
        if(this.active && active == false)
            baseColor.set(baseColor.r * 0.5f, baseColor.g * 0.5f, baseColor.b * 0.5f, 1.0f);
        else if (this.active == false && active)
            baseColor.set(baseColor.r * 2, baseColor.g * 2, baseColor.b * 2, 1.0f);
    }

    public void setHandler(ButtonHandler handler) {
        this.handler = handler;
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        boolean handled = false;
        if(isMouseOver(x, y)) {
            if(!active) {
                color.set(baseColor);
                mouseDown = false;
                handled = true;
            } else {
                switch (event) {
                    case MOUSE_DOWN:
                        color.set(baseColor.r * 2, baseColor.g * 2, baseColor.b * 2, 1.0f);
                        mouseDown = true;
                        handled = true;
                        break;
                    case MOUSE_UP:
                        color.set(baseColor.r * 1.5f, baseColor.g * 1.5f, baseColor.b * 1.5f, 1.0f);
                        if (mouseDown && handler != null) {
                            handler.onActivate();
                        }
                        mouseDown = false;
                        handled = true;
                        break;
                    case MOUSE_DRAGGED:
                        handled = true;
                    case MOUSE_MOVED:
                        if(!mouseDown)
                            color.set(baseColor.r * 1.5f, baseColor.g * 1.5f, baseColor.b * 1.5f, 1.0f);
                        break;
                }
            }
        } else {
            color.set(baseColor);
            mouseDown = false;
            handled = false;
        }

        return handled;
    }

    public void update(float dt) {}

    public void render(float opacity) {
        shapeRenderer.setColor(color.r, color.g, color.b, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(color.r*0.5f, color.g*0.5f, color.b*0.5f, opacity);
        shapeRenderer.rect( bounds.x+2, bounds.y+2, bounds.width-4, bounds.height-4);
    }

    public void renderText(float opacity) {
        if(icon != null) {
            float iconSize = Math.min(bounds.width, bounds.height) * 0.8f;
            float iconOffsetX = (bounds.width - iconSize)/2;
            float iconOffsetY = (bounds.height - iconSize)/2;
            float colorFactor = active ? 1 : 0.5f;
            spriteBatch.setColor(colorFactor, colorFactor, colorFactor, opacity);
            spriteBatch.draw(icon, bounds.x + iconOffsetX, bounds.y + iconOffsetY, iconSize, iconSize);
        }
    }
}
