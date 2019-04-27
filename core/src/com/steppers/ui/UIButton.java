package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;

public class UIButton extends UIElement {

    ShapeRenderer shapeRenderer;
    Color color;

    public UIButton(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        color = new Color(0.6f, 0.6f, 0.6f, 1.0f);
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        if(isMouseOver(x, y)) {
            switch (event) {
                case MOUSE_DOWN:
                    color.set(0.8f, 0.8f, 0.8f, 1.0f);
                    break;
                case MOUSE_UP:
                    color.set(0.6f, 0.6f, 0.6f, 1.0f);
                    break;
            }
        } else {
            color.set(0.6f, 0.6f, 0.6f, 1.0f);
        }

        return true;
    }

    public void update(float dt) {}

    public void render(float opacity) {
        shapeRenderer.setColor(color.r, color.g, color.b, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
