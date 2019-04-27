package com.steppers.gameui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.steppers.gamestate.Follower;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

import java.util.ArrayList;

public class UIBloodCircle extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private ArrayList<Follower> followers;

    public UIBloodCircle(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();
    }

    public void AddFollower(Follower follower) {
        followers.add(follower);
    }

    public void RemoveFollower(Follower follower) {
        followers.remove(follower);
    }

    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, opacity);

        Vector2 v1 = new Vector2();
        Vector2 v2 = new Vector2();
        for(int i = 0; i < 5; ++i)
        {
            v1.set(0, bounds.height/2);
            v1.setAngle(i * (360.0f / 5) + 54);
            v1.add(bounds.x, bounds.y);
            for(int j = i; j < 5; ++j)
            {
                v2.set(0, bounds.height/2);
                v2.setAngle(j * (360.0f / 5) + 54);
                v2.add(bounds.x, bounds.y);
                shapeRenderer.rectLine(v1, v2, 3);
            }
        }

        shapeRenderer.end();
    }
}
