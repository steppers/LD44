package com.steppers.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.steppers.ld44.Renderer;

public class UIElement {

    public enum Alignment {
        ALIGN_TL,
        ALIGN_TC,
        ALIGN_TR,
        ALIGN_CL,
        ALIGN_C,
        ALIGN_CR,
        ALIGN_BL,
        ALIGN_BC,
        ALIGN_BR
    }

    protected Rectangle bounds;
    protected Alignment alignment;

    public UIElement(float x, float y, float width, float height) {
        this(x, y, width, height, false, false);
    }

    public UIElement(float x, float y, float width, float height, boolean percentagePos, boolean percentageSize) {
        bounds = new Rectangle(x, y, width, height);
        alignment = Alignment.ALIGN_TL;

        if(percentagePos)
            convertToPercentagePos();
        if(percentageSize)
            convertToPercentageSize();
    }

    private void resetAlignment() {
        switch (this.alignment) {
            case ALIGN_TL:
                break;
            case ALIGN_TC:
                bounds.x += bounds.width / 2;
                break;
            case ALIGN_TR:
                bounds.x += bounds.width;
                break;
            case ALIGN_CL:
                bounds.y += bounds.height / 2;
                break;
            case ALIGN_C:
                bounds.x += bounds.width / 2;
                bounds.y += bounds.height / 2;
                break;
            case ALIGN_CR:
                bounds.x += bounds.width;
                bounds.y += bounds.height / 2;
                break;
            case ALIGN_BL:
                bounds.y += bounds.height;
                break;
            case ALIGN_BC:
                bounds.x += bounds.width / 2;
                bounds.y += bounds.height;
                break;
            case ALIGN_BR:
                bounds.x += bounds.width;
                bounds.y += bounds.height;
                break;

        }
        this.alignment = Alignment.ALIGN_TL;
    }

    public void setAlignment(Alignment alignment) {
        resetAlignment();
        switch (alignment) {
            case ALIGN_TL:
                break;
            case ALIGN_TC:
                bounds.x -= bounds.width / 2;
                break;
            case ALIGN_TR:
                bounds.x -= bounds.width;
                break;
            case ALIGN_CL:
                bounds.y -= bounds.height / 2;
                break;
            case ALIGN_C:
                bounds.x -= bounds.width / 2;
                bounds.y -= bounds.height / 2;
                break;
            case ALIGN_CR:
                bounds.x -= bounds.width;
                bounds.y -= bounds.height / 2;
                break;
            case ALIGN_BL:
                bounds.y -= bounds.height;
                break;
            case ALIGN_BC:
                bounds.x -= bounds.width / 2;
                bounds.y -= bounds.height;
                break;
            case ALIGN_BR:
                bounds.x -= bounds.width;
                bounds.y -= bounds.height;
                break;

        }
        this.alignment = alignment;
    }

    public void convertToPercentagePos() {
        Alignment align = this.alignment;
        resetAlignment();

        float w = Renderer.Get().GetCamera().viewportWidth;
        float h = Renderer.Get().GetCamera().viewportHeight;

        bounds.setX(bounds.x * (w / 100.0f));
        bounds.setY(bounds.y * (h / 100.0f));

        setAlignment(align);
    }

    public void convertToPercentageSize() {
        Alignment align = this.alignment;
        resetAlignment();

        float w = Renderer.Get().GetCamera().viewportWidth;
        float h = Renderer.Get().GetCamera().viewportHeight;

        bounds.setWidth(bounds.width * (w / 100.0f));
        bounds.setHeight(bounds.height * (h / 100.0f));

        setAlignment(align);
    }

    public void moveTo(float x, float y) {
        bounds.setPosition(x, y);
    }

    public void resize(float width, float height) {
        bounds.setSize(width, height);
    }

    public boolean isMouseOver(float x, float y) {
        return bounds.contains(x, y);
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) { return false; }

    public void update(float dt) {}
    public void render(float opacity) {}

}
