package com.steppers.ui;

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
        bounds = new Rectangle(x, y, width, height);
        alignment = Alignment.ALIGN_BL;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void resetAlignment() {
        switch (this.alignment) {
            case ALIGN_TL:
                bounds.y += bounds.height;
                break;
            case ALIGN_TC:
                bounds.y += bounds.height;
                bounds.x += bounds.width / 2;
                break;
            case ALIGN_TR:
                bounds.y += bounds.height;
                bounds.x += bounds.width;
                break;
            case ALIGN_CL:
                bounds.y += bounds.height / 2;
                break;
            case ALIGN_C:
                bounds.y += bounds.height / 2;
                bounds.x += bounds.width / 2;
                break;
            case ALIGN_CR:
                bounds.y += bounds.height / 2;
                bounds.x += bounds.width;
                break;
            case ALIGN_BL:
                break;
            case ALIGN_BC:
                bounds.x += bounds.width / 2;
                break;
            case ALIGN_BR:
                bounds.x += bounds.width;
                break;
        }
        this.alignment = Alignment.ALIGN_BL;
    }

    public void setAlignment(Alignment alignment) {
        resetAlignment();
        switch (alignment) {
            case ALIGN_TL:
                bounds.y -= bounds.height;
                break;
            case ALIGN_TC:
                bounds.y -= bounds.height;
                bounds.x -= bounds.width / 2;
                break;
            case ALIGN_TR:
                bounds.y -= bounds.height;
                bounds.x -= bounds.width;
                break;
            case ALIGN_CL:
                bounds.y -= bounds.height / 2;
                break;
            case ALIGN_C:
                bounds.y -= bounds.height / 2;
                bounds.x -= bounds.width / 2;
                break;
            case ALIGN_CR:
                bounds.y -= bounds.height / 2;
                bounds.x -= bounds.width;
                break;
            case ALIGN_BL:
                break;
            case ALIGN_BC:
                bounds.x -= bounds.width / 2;
                break;
            case ALIGN_BR:
                bounds.x -= bounds.width;
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
        Alignment align = this.alignment;
        resetAlignment();

        bounds.setPosition(x, y);

        setAlignment(align);
    }

    public void resize(float width, float height) {
        Alignment align = this.alignment;
        resetAlignment();

        bounds.setSize(width, height);

        setAlignment(align);
    }

    public boolean isMouseOver(float x, float y) {
        return bounds.contains(x, y);
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) { return false; }

    public void update(float dt) {}
    public void render(float opacity) {}
    public void renderText(float opacity) {}

}
