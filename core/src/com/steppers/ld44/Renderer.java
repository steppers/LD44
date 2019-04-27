package com.steppers.ld44;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Renderer {

    // Singleton
    static Renderer ins = new Renderer();
    public static Renderer Get() {
        return ins;
    }

    private Renderer() {}

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private OrthographicCamera camera = new OrthographicCamera();

    public ShapeRenderer GetShapeRenderer() {
        return shapeRenderer;
    }

    public OrthographicCamera GetCamera() {
        return camera;
    }

    public void SetupCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float aspect_ratio = w / h;

        camera.setToOrtho(false, aspect_ratio * 512, 512);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

}
