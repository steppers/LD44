package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gamestate.Room;
import com.steppers.ld44.Renderer;
import com.steppers.gamestate.Map;

import java.util.ArrayList;

public class UIMapDisplay extends UIElement {

    private SpriteBatch spriteBatch;
    private UITextButton leftRoomButton = null;
    private UITextButton middleRoomButton = null;
    private UITextButton rightRoomButton = null;

    ShapeRenderer shapeRenderer;
    Map dungeonMap;

    public UIMapDisplay(float x, float y, float width, float height, Map dungeonMap) {
        super(x, y, width, height);

        spriteBatch = Renderer.Get().GetSpriteBatch();

        this.dungeonMap = dungeonMap;

        leftRoomButton = new UITextButton(x + (width/16), y - 80, 70, 70, Color.DARK_GRAY ,"1");
        leftRoomButton.setHandler(() -> {
            dungeonMap.nextLevel(0);
        });

        middleRoomButton = new UITextButton(x + ((6*width)/16), y - 80, 70, 70, Color.DARK_GRAY, "2");
        middleRoomButton.setHandler(() -> {
            dungeonMap.nextLevel(1);
        });

        rightRoomButton = new UITextButton(x + ((11*width)/16), y - 80, 70, 70, Color.DARK_GRAY, "3");
        rightRoomButton.setHandler(() -> {
            dungeonMap.nextLevel(2);
        });

        shapeRenderer = Renderer.Get().GetShapeRenderer();
    }

    public ArrayList<UITextButton> getButtons() {
        ArrayList<UITextButton> buttons = new ArrayList<>();
        buttons.add(leftRoomButton);
        buttons.add(middleRoomButton);
        buttons.add(rightRoomButton);
        return buttons;
    }

    public void render(float opacity) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect( bounds.x - 3, bounds.y + 3, bounds.width + 6, - bounds.height - 6);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect( bounds.x, bounds.y, bounds.width, - bounds.height);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect( bounds.x + ((6*bounds.width)/16), bounds.y - 170, 70, 70);


        ArrayList<Room> nextRooms = dungeonMap.getNextLevelRooms();

        for(UIButton button : getButtons()){
            button.render(opacity);
        }

        shapeRenderer.end();

        spriteBatch.begin();
        if(nextRooms.size() == 1) {
            spriteBatch.draw(nextRooms.get(0).getSymbol(), bounds.x + ((6*bounds.width)/16), bounds.y - 80);
        } else {
            spriteBatch.draw(nextRooms.get(0).getSymbol(), bounds.x + (bounds.width/16), (bounds.y - 80));
            spriteBatch.draw(nextRooms.get(1).getSymbol(), bounds.x + ((6*bounds.width)/16), bounds.y - 80);
            spriteBatch.draw(nextRooms.get(2).getSymbol(), bounds.x + (11*bounds.width/16), (bounds.y - 80));
        }
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

}
