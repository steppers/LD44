package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;

public class UIButton extends UIElement {

    public interface ButtonHandler {
        void onActivate();
    }

    ShapeRenderer shapeRenderer;
    Color color;
    Color baseColor;
    ButtonHandler handler;

    boolean mouseDown;

    public UIButton(float x, float y, float width, float height) {
        this(x, y, width, height, new Color(0.6f, 0.6f, 0.6f, 1.0f));
    }

    public UIButton(float x, float y, float width, float height, Color color) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        baseColor = color;
        this.color = new Color(baseColor);
        mouseDown = false;
        handler = null;
    }

    public void setHandler(ButtonHandler handler) {
        this.handler = handler;
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        if(isMouseOver(x, y)) {
            switch (event) {
                case MOUSE_DOWN:
                    color.set(baseColor.r*1.5f, baseColor.g*1.5f, baseColor.b*1.5f, 1.0f);
                    mouseDown = true;
                    break;
                case MOUSE_UP:
                    color.set(baseColor);
                    if(mouseDown && handler != null)
                    {
                        handler.onActivate();
                    }
                    mouseDown = false;
                    break;
                case MOUSE_MOVED:
                case MOUSE_DRAGGED:
                    color.set(baseColor.r*1.2f, baseColor.g*1.2f, baseColor.b*1.2f, 1.0f);
                    break;
            }
        } else {
            color.set(baseColor);
            mouseDown = false;
            return false;
        }

        return true;
    }

    public void update(float dt) {}

    public void render(float opacity) {
        shapeRenderer.setColor(color.r, color.g, color.b, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
