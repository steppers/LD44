package com.steppers.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIScreen;

public class MMScreen extends UIScreen {

    ShapeRenderer shapeRenderer;

    UIButton button;

    public MMScreen() {
        shapeRenderer = Renderer.Get().GetShapeRenderer();

        button = new UIButton(50, 50, 100, 60);
        button.setAlignment(UIElement.Alignment.ALIGN_C);
        button.convertToPercentagePos();
        registerElement(button);
    }

    @Override
    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        button.render(opacity);
        shapeRenderer.end();
    }
}
