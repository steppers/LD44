package com.steppers.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.ld44.Renderer;

import java.util.ArrayList;

public class UIMapDisplay extends UIElement {

    private UITextButton leftRoomButton = null;
    private UITextButton middleRoomButton = null;
    private UITextButton rightRoomButton = null;

    ShapeRenderer shapeRenderer;

    public UIMapDisplay(float x, float y, float width, float height) {
        super(x, y, width, height);
        leftRoomButton = new UITextButton(x + (width/16), y + 30, width/4, 40, Color.DARK_GRAY ,"1");
        middleRoomButton = new UITextButton(x + ((6*width)/16), y + 30, width/4, 40, Color.DARK_GRAY, "2");
        rightRoomButton = new UITextButton(x + ((11*width)/16), y + 30, width/4, 40, Color.DARK_GRAY, "3");

        shapeRenderer = Renderer.Get().GetShapeRenderer();
    }

    public ArrayList<UITextButton> getButtons() {
        ArrayList<UITextButton> buttons = new ArrayList<>();
        buttons.add(leftRoomButton);
        buttons.add(middleRoomButton);
        buttons.add(rightRoomButton);
        return buttons;
    }


}
