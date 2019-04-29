package com.steppers.gameui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.state.Room;
import com.steppers.ld44.Renderer;
import com.steppers.state.Map;
import com.steppers.screens.GameScreen;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UITextButton;

import java.util.ArrayList;

public class UIMapDisplay extends UIElement {

    private SpriteBatch spriteBatch;
    private UITextButton leftRoomButton = null;
    private UITextButton middleRoomButton = null;
    private UITextButton rightRoomButton = null;

    GameScreen gameScreen;
    ShapeRenderer shapeRenderer;
    Map dungeonMap;

    boolean active = true;

    public UIMapDisplay(float x, float y, float width, float height, Map dungeonMap, GameScreen gameScreen) {
        super(x, y, width, height);

        spriteBatch = Renderer.Get().GetSpriteBatch();

        this.dungeonMap = dungeonMap;
        this.gameScreen = gameScreen;

        shapeRenderer = Renderer.Get().GetShapeRenderer();
    }

    public ArrayList<UITextButton> getButtons() {
        ArrayList<UITextButton> buttons = new ArrayList<>();
        buttons.add(leftRoomButton);
        buttons.add(middleRoomButton);
        buttons.add(rightRoomButton);
        return buttons;
    }

    public void initButtons(){
        leftRoomButton = new UITextButton(bounds.x + (bounds.width/16), bounds.y + 100, 70, 70, Color.DARK_GRAY ,"1");
        leftRoomButton.setHandler(() -> {
            gameScreen.getEnemyDisplay().reset();
            dungeonMap.nextLevel(0);
        });

        middleRoomButton = new UITextButton(bounds.x + ((6*bounds.width)/16), bounds.y + 100, 70, 70, Color.DARK_GRAY, "2");
        middleRoomButton.setHandler(() -> {
            gameScreen.getEnemyDisplay().reset();
            dungeonMap.nextLevel(1);
        });

        rightRoomButton = new UITextButton(bounds.x + ((11*bounds.width)/16), bounds.y + 100, 70, 70, Color.DARK_GRAY, "3");
        rightRoomButton.setHandler(() -> {
            gameScreen.getEnemyDisplay().reset();
            dungeonMap.nextLevel(2);
        });
    }

    @Override
    public void render(float opacity) {
        if(active) {
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(bounds.x - 3, bounds.y, bounds.width + 6, bounds.height + 6);
            shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.rect(bounds.x, bounds.y+3, bounds.width, bounds.height);

            shapeRenderer.setColor(new Color(0.1f, 0.1f, 0.1f, 1f));
            shapeRenderer.rect(bounds.x + ((6 * bounds.width) / 16), bounds.y + 14, 70, 70);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(bounds.x + ((6 * bounds.width) / 16) + 3, bounds.y + 17, 64, 64);


            ArrayList<Room> nextRooms = dungeonMap.getNextLevelRooms();

            for (UIButton button : getButtons()) {
                button.render(opacity);
            }

            shapeRenderer.end();

            spriteBatch.begin();
            if (nextRooms.size() == 1) {
                spriteBatch.draw(nextRooms.get(0).getSymbol(), bounds.x + ((6 * bounds.width) / 16), bounds.y + 100);
            } else {
                spriteBatch.draw(nextRooms.get(0).getSymbol(), bounds.x + (bounds.width / 16), (bounds.y + 100));
                spriteBatch.draw(nextRooms.get(1).getSymbol(), bounds.x + ((6 * bounds.width) / 16), bounds.y + 100);
                spriteBatch.draw(nextRooms.get(2).getSymbol(), bounds.x + (11 * bounds.width / 16), (bounds.y + 100));
            }

            spriteBatch.draw(dungeonMap.getCurrentRoom().getSymbol(), bounds.x + (6 * bounds.width / 16), (bounds.y +14));
            spriteBatch.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        }
    }

    public void setActive(boolean active){
        this.active = active;
    }

}
