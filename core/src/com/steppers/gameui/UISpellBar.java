package com.steppers.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;
import com.steppers.ui.MouseEvent;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;

import java.util.HashMap;

public class UISpellBar extends UIElement {

    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;

    private HashMap<String, UIButton> spellButtons;

    public UISpellBar(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        spellButtons = new HashMap<>();
        spellButtons.put("Heal", new UIButton(0, 0, 32, 32, new Texture("spell_heal.png")));
        spellButtons.put("Fireball", new UIButton(0, 0, 32, 32, new Texture("spell_fireball.png")));
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        for(UIButton sb : spellButtons.values()) {
            if(sb.handleMouseEvent(x, y, event))
                return true;
        }
        return false;
    }

    public void render(float opacity) {
        shapeRenderer.setColor(0.10f, 0.1f, 0.1f, opacity);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, opacity);
        shapeRenderer.rect( bounds.x+2, bounds.y - 2, bounds.width-4, bounds.height-1);

        int i = 0;
        for(UIButton sb : spellButtons.values()) {
            sb.moveTo(bounds.x + 4 + 32*i, bounds.y + 2);
            sb.render(opacity);
            ++i;
        }
    }

    public void renderText(float opacity) {
        for(UIButton sb : spellButtons.values()) {
            sb.renderText(opacity);
        }
    }

}
