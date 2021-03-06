package com.steppers.gameui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.steppers.state.FollowerRoom;
import com.steppers.state.GameState;
import com.steppers.state.MonsterRoom;
import com.steppers.ld44.Renderer;
import com.steppers.ui.MouseEvent;
import com.steppers.ui.UIElement;

public class UIEnemyDisplay extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private GameState gameState;
    private UIBloodCircle bloodCircle;

    boolean empty = false;

    int offset = 0;
    float colorOffset = 0;
    float colorMultiplier = 0;

    public UIEnemyDisplay(float x, float y, float width, float height, GameState gameState, UIBloodCircle bloodCircle) {
        super(x, y, width, height);

        this.gameState = gameState;
        this.bloodCircle = bloodCircle;

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        if(isMouseOver(x, y)) {
            switch (event) {
                case MOUSE_DOWN:
                    break;
                case MOUSE_UP:
                    if(!empty && gameState.getDungeonMap().getCurrentRoom().getRoomType() == 3) {
                        empty = true;
                        gameState.getCharacter().addFollower(((FollowerRoom) gameState.getDungeonMap().getCurrentRoom()).getFollower());
                        bloodCircle.addFollower(((FollowerRoom) gameState.getDungeonMap().getCurrentRoom()).getFollower());
                    }
                    break;
                case MOUSE_MOVED:
                    break;
                case MOUSE_DRAGGED:
                    break;
            }
        } else {
            return false;
        }

        return true;
    }

    public void render(float opacity) {
        if(gameState.getDungeonMap().getCurrentRoom().getRoomType() >= 0) {
            if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 2) {
                shapeRenderer.setColor(1, 0, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }

                offset++;
                if (offset > 360) {
                    offset = 0;
                }

                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y + bounds.height/2, bounds.getWidth() / 2.4f, 64);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y+ bounds.height/2, bounds.getWidth() / 2.5f, 64);

                shapeRenderer.end();

                spriteBatch.begin();

                spriteBatch.draw(((MonsterRoom) gameState.getDungeonMap().getCurrentRoom()).getEnemy().getType().getIcon(), bounds.x - 50 + + bounds.width/2, bounds.y - 90 + bounds.height/2, 100, 100);

                spriteBatch.end();

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            } else if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 3) {
                shapeRenderer.setColor(0, 0.8f, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }

                offset++;
                if (offset > 360) {
                    offset = 0;
                }

                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y + bounds.height/2, bounds.getWidth() / 2.4f, 64);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y+ bounds.height/2, bounds.getWidth() / 2.5f, 64);

                shapeRenderer.end();

                if(!empty) {

                    spriteBatch.begin();

                    spriteBatch.draw(((FollowerRoom) gameState.getDungeonMap().getCurrentRoom()).getFollower().getType().getIcon(), bounds.x - 40 + bounds.width/2, bounds.y - 80 + bounds.height/2, 80, 80);

                    spriteBatch.end();

                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            } else if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 1) {
                shapeRenderer.setColor(0.6f, 0.6f, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }


                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y + bounds.height/2, bounds.getWidth() / 2.4f, 64);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x+bounds.width/2, bounds.y+ bounds.height/2, bounds.getWidth() / 2.5f, 64);

            } else if(gameState.getDungeonMap().getCurrentRoom().getRoomType() == 0) {
                shapeRenderer.setColor(0.6f + (float) (Math.sin(colorOffset))*0.4f, 0f, 0, opacity);

                Vector2 v1 = new Vector2();
                Vector2 v2 = new Vector2();
                for (int i = 0; i < 5; ++i) {
                    v1.set(0, bounds.height / 2);
                    v1.setAngle(i * (360.0f / 5) + 54 + offset);
                    v1.add(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
                    for (int j = i; j < 5; ++j) {
                        v2.set(0, bounds.height / 2);
                        v2.setAngle(j * (360.0f / 5) + 54 + offset);
                        v2.add(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
                        shapeRenderer.rectLine(v1, v2, 3);
                    }
                }

                colorOffset += 0.03f;

                offset++;
                if (offset > 360) {
                    offset = 0;
                }

                shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1f));
                shapeRenderer.circle(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, bounds.getWidth() / 2.4f, 64);

                shapeRenderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
                shapeRenderer.circle(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, bounds.getWidth() / 2.5f, 64);

                shapeRenderer.end();

                if (!empty) {

                    spriteBatch.begin();

                    spriteBatch.draw(new Texture("PixelAltar.png"), bounds.x - 55 + bounds.width / 2, bounds.y - 100 + bounds.height / 2, 110, 110);

                    spriteBatch.end();

                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            }
        }
    }

    public void reset(){
        this.empty = false;
    }
}
