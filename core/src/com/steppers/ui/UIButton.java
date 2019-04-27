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
    ButtonHandler handler;

    boolean mouseDown;

    public UIButton(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        color = new Color(0.6f, 0.6f, 0.6f, 1.0f);
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
                    color.set(0.8f, 0.8f, 0.8f, 1.0f);
                    mouseDown = true;
                    break;
                case MOUSE_UP:
                    color.set(0.6f, 0.6f, 0.6f, 1.0f);
                    if(mouseDown && handler != null)
                    {
                        handler.onActivate();
                    }
                    mouseDown = false;
                    break;
            }
        } else {
            color.set(0.6f, 0.6f, 0.6f, 1.0f);
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
