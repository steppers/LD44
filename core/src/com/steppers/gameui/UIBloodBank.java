package com.steppers.gameui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

public class UIBloodBank extends UIElement {

    ShapeRenderer shapeRenderer;

    public UIBloodBank(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
    }

    public void update(float dt) {}

    public void render(float opacity) {
        shapeRenderer.setColor(0.10f, 0.1f, 0.1f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width-3, bounds.height-3);
    }
}
