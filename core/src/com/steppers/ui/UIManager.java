package com.steppers.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.steppers.ld44.Renderer;

import java.util.HashMap;

public class UIManager implements InputProcessor {

    // Singleton
    static UIManager ins = new UIManager();
    public static UIManager Get() {
        return ins;
    }

    private HashMap<String, UIScreen> screens;
    private UIScreen activeScreen, nextScreen;
    private String activeScreenId, nextScreenId;
    private float opacity, fadeSpeed;
    private boolean fadeOver;

    private UIManager() {
        screens = new HashMap<String, UIScreen>();
        activeScreen = nextScreen = null;
        opacity = 1.0f;
        activeScreenId = "";
        nextScreenId = "";
    }

    public void registerScreen(UIScreen screen, String id) {
        if(screens.containsKey(id) || screens.containsValue(screen))
        {
            System.err.format("Warning: screen(%s) already registered with UIManager!\n", id);
            return;
        }

        screens.put(id, screen);
    }

    public void unregisterScreen(String screenId) {
//        if(screens.containsKey(screenId))
            screens.remove(screenId);
    }

    public void setActiveScreen(String screenId) {
        activeScreen = screens.get(screenId);
        opacity = 1.0f;
        activeScreenId = screenId;
    }

    public void transitionToScreen(String screenId) {
        transitionToScreen(screenId, 1.0f, true);
    }

    public void transitionToScreen(String screenId, float duration) {
        transitionToScreen(screenId, duration, true);
    }

    public void transitionToScreen(String screenId, float duration, boolean fadeOver) {
        nextScreen = screens.get(screenId);
        fadeSpeed = 1.0f/duration;
        nextScreenId = screenId;
        this.fadeOver = fadeOver;
        opacity = 0.0f;
    }

    public UIScreen getScreen(String screenId) {
        return screens.get(screenId);
    }
    public UIScreen getActiveScreen() {
        return activeScreen;
    }

    public void update(float dt) {
        if(nextScreen != null) {
            // Update transition
            opacity += dt * fadeSpeed;
            opacity = Math.min(1.0f, opacity);

            if(opacity == 1.0f)
            {
                // End the transition
                activeScreen = nextScreen;
                nextScreen = null;

                String from = activeScreenId;
                activeScreenId = nextScreenId;
                nextScreenId = "";
                activeScreen.onTransitionedFrom(from);
            }
        }

        activeScreen.update(dt);
        if(nextScreen != null)
            nextScreen.update(dt);
    }

    public void render() {
        // Enable blending
        Renderer.Get().GetSpriteBatch().enableBlending();
        Renderer.Get().GetSpriteBatch().setBlendFunction(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        if(nextScreen != null) {
            if(fadeOver) {
                activeScreen.render(1.0f);
                nextScreen.render(opacity);
            } else {
                nextScreen.render(1.0f);
                activeScreen.render(1-opacity);
            }
        } else {
            activeScreen.render(1.0f);
        }
    }

    Vector3 tp = new Vector3();
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;

        if(nextScreen == null)
        {
            Renderer.Get().GetCamera().unproject(tp.set(screenX, screenY, 0));
            for (UIElement e: activeScreen.getRegisteredElements()) {
                if(e.handleMouseEvent(tp.x, tp.y, MouseEvent.MOUSE_DOWN))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;

        if(nextScreen == null)
        {
            Renderer.Get().GetCamera().unproject(tp.set(screenX, screenY, 0));
            for (UIElement e: activeScreen.getRegisteredElements()) {
                if(e.handleMouseEvent(tp.x, tp.y, MouseEvent.MOUSE_UP))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // ignore if its not first touch pointer
        if (pointer > 0) return false;

        if(nextScreen == null)
        {
            Renderer.Get().GetCamera().unproject(tp.set(screenX, screenY, 0));
            for (UIElement e: activeScreen.getRegisteredElements()) {
                if(e.handleMouseEvent(tp.x, tp.y, MouseEvent.MOUSE_DRAGGED))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if(nextScreen == null)
        {
            Renderer.Get().GetCamera().unproject(tp.set(screenX, screenY, 0));
            for (UIElement e: activeScreen.getRegisteredElements()) {
                if(e.handleMouseEvent(tp.x, tp.y, MouseEvent.MOUSE_MOVED))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void onScreenResized() {
        for (UIScreen s : screens.values()) {
            s.onScreenResize();
        }
    }
}
