package com.steppers.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIScreen;

public class BloodScreen extends UIScreen {

    SpriteBatch spriteBatch;

    public BloodScreen() {
        spriteBatch = Renderer.Get().GetSpriteBatch();
    }

    @Override
    public void onTransitionedFrom(String previous) {
        UIManager.Get().transitionToScreen(previous, 1.0f, false);
    }

    @Override
    public void render(float opacity) {
        spriteBatch.begin();
        spriteBatch.setColor(1,1,1, opacity);
        spriteBatch.draw(Renderer.Get().GetBloodOverlayTexture(),0,0, (int)Renderer.Get().GetCamera().viewportWidth, (int)Renderer.Get().GetCamera().viewportHeight);
        spriteBatch.end();
    }

}
