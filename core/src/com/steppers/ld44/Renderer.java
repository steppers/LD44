package com.steppers.ld44;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Renderer {

    // Singleton
    static Renderer ins = new Renderer();
    public static Renderer Get() {
        return ins;
    }

    private Renderer() {}

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public ShapeRenderer GetShapeRenderer() {
        return shapeRenderer;
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

}
