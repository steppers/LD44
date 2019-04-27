package com.steppers.ui;

import java.util.ArrayList;

public class UIScreen {

    private ArrayList<UIElement> registered = new ArrayList<>();

    public void registerElement(UIElement element) {
        registered.add(element);
    }

    public void unregisterElement(UIElement element) {
        registered.remove(element);
    }

    public ArrayList<UIElement> getRegisteredElements() {
        return registered;
    }

    public void onTransitionedTo() {

    }

    public void update(float dt) {

    }

    public void render(float opacity) {

    }

}
