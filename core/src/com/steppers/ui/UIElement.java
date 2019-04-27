package com.steppers.ui;

import com.badlogic.gdx.math.Rectangle;

public class UIElement {

    protected Rectangle bounds;

    public UIElement(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void moveTo(float x, float y) {
        bounds.setPosition(x, y);
    }

    public void resize(float width, float height) {
        bounds.setSize(width, height);
    }

    public boolean isMouseOver(float x, float y) {
        return bounds.contains(x, y);
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) { return false; }

    public void update(float dt) {}
    public void render(float opacity) {}

}
