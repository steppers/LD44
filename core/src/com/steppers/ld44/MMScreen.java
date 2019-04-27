package com.steppers.ld44;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MMScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    public MMScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, opacity);
        shapeRenderer.rect( 150, 100, 100, 100);
        shapeRenderer.end();
    }
}
