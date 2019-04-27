package com.steppers.ui;

import java.util.HashMap;

public class UIManager {

    // Singleton
    static UIManager ins = new UIManager();
    public static UIManager Get() {
        return ins;
    }

    private HashMap<String, UIScreen> screens;
    private UIScreen activeScreen, nextScreen;
    private float activeOpacity, nextOpacity, fadeSpeed;

    private UIManager() {
        screens = new HashMap<String, UIScreen>();
        activeScreen = nextScreen = null;
        activeOpacity = 1.0f;
        nextOpacity = 0.0f;
    }

    public void registerScreen(UIScreen screen, String id) {
        if(screens.containsKey(id) || screens.containsValue(screen))
        {
            System.err.format("Warning: screen(%s) already registered with UIManager!\n", id);
            return;
        }

        screens.put(id, screen);
    }

    public void unregisterScreen(UIScreen screen) {
        screens.remove(screen);
    }

    public void setActiveScreen(String screenId) {
        activeScreen = screens.get(screenId);
        activeOpacity = 1.0f;
        nextOpacity = 0.0f;
    }

    public void transitionToScreen(String screenId) {
        transitionToScreen(screenId, 1.0f);
    }

    public void transitionToScreen(String screenId, float duration) {
        nextScreen = screens.get(screenId);
        fadeSpeed = 1.0f/duration;
    }

    public UIScreen getScreen(String screenId) {
        return screens.get(screenId);
    }

    public void update(float dt) {
        if(nextScreen != null)
        {
            // Update transition
            nextOpacity += dt * fadeSpeed;
            nextOpacity = Math.min(1.0f, nextOpacity);
            activeOpacity = 1.0f - nextOpacity;

            if(nextOpacity == 1.0f)
            {
                // End the transition
                activeScreen = nextScreen;
                nextScreen = null;
                activeOpacity = 1.0f;
                nextOpacity = 0.0f;
            }
        }
    }

    public void render() {
        activeScreen.render(activeOpacity);
        if(nextScreen != null)
            nextScreen.render(nextOpacity);
    }

}
