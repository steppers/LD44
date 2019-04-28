package com.steppers.gameui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.steppers.gamestate.FollowerRoom;
import com.steppers.gamestate.GameState;
import com.steppers.gamestate.Map;
import com.steppers.gamestate.MonsterRoom;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

public class UIEnemyDisplay extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private GameState gameState;

    int offset = 0;

    public UIEnemyDisplay(float x, float y, float width, float height, GameState gameState) {
        super(x, y, width, height);

        this.gameState = gameState;

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();
    }

    public void render(float opacity) {
        if(gameState.getDungeonMap().getCurrentRoom().getRoomType() >= 2) {
            if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 2) {
                shapeRenderer.setColor(1, 0, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x, bounds.y);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x, bounds.y);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }

                offset++;
                if (offset > 360) {
                    offset = 0;
                }

                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x, bounds.y, bounds.getWidth() / 2.4f);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x, bounds.y, bounds.getWidth() / 2.5f);

                shapeRenderer.end();

                spriteBatch.begin();

                spriteBatch.draw(((MonsterRoom) gameState.getDungeonMap().getCurrentRoom()).getEnemy().getIcon(), bounds.x - 50, bounds.y - 90, 100, 100);

                spriteBatch.end();

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            } else if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 3) {
                shapeRenderer.setColor(0, 0.8f, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x, bounds.y);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x, bounds.y);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }

                offset++;
                if (offset > 360) {
                    offset = 0;
                }

                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x, bounds.y, bounds.getWidth() / 2.4f);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x, bounds.y, bounds.getWidth() / 2.5f);

                shapeRenderer.end();

                spriteBatch.begin();

                spriteBatch.draw(((FollowerRoom) gameState.getDungeonMap().getCurrentRoom()).getFollower().getIcon(), bounds.x - 40, bounds.y - 80, 80, 80);

                spriteBatch.end();

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            }
        }
    }
}
