package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gamestate.BeingGenerator;
import com.steppers.gamestate.Character;
import com.steppers.gamestate.Follower;
import com.steppers.gamestate.GameState;
import com.steppers.gameui.UIBloodBank;
import com.steppers.gameui.UIBloodCircle;
import com.steppers.gameui.UIEnemyDisplay;
import com.steppers.gameui.UISpellBar;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.gameui.UIMapDisplay;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    UIButton backButton;
    UIBloodBank bloodBank;
    UIBloodCircle bloodCircle;
    UISpellBar spellBar;

    Texture background;

    UIMapDisplay mapDisplay;
    UIEnemyDisplay enemyDisplay;


    public GameScreen(GameState gameState) {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        batch = Renderer.Get().GetSpriteBatch();

        backButton = new UITextButton(5, 5, 70, 50, "Menu");
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);

        background = Renderer.Get().GetBackgroundTexture();

        bloodBank = new UIBloodBank(0, 512, 0, 36);
        bloodBank.setAlignment(UIElement.Alignment.ALIGN_TL);

        bloodCircle = new UIBloodCircle(50, 44, 300, 300);
        bloodCircle.convertToPercentagePos();
        Character character = gameState.getCharacter();
        bloodCircle.setCharacter(character);
        for(Follower f : character.getFollowers()) {
            bloodCircle.addFollower(f);
        }
        registerElement(bloodCircle);

        spellBar = new UISpellBar(50, 0, 160 + 4 + (6*2), 32 + 2 + 4);
        spellBar.setAlignment(UIElement.Alignment.ALIGN_BC);
        spellBar.convertToPercentagePos();

        enemyDisplay = new UIEnemyDisplay(50, 100, 220, 220, gameState, bloodCircle);
        enemyDisplay.convertToPercentagePos();
        enemyDisplay.setAlignment(UIElement.Alignment.ALIGN_C);
        registerElement(enemyDisplay);

        mapDisplay = new UIMapDisplay(99, 0, 260, 180, gameState.getDungeonMap(), this);
        mapDisplay.setAlignment(UIElement.Alignment.ALIGN_BR);
        mapDisplay.convertToPercentagePos();
        mapDisplay.initButtons();
        for(UIButton button : mapDisplay.getButtons()){
            //button.convertToPercentagePos();
            registerElement(button);
        }

        // Add initial followers
        bloodCircle.addFollower(BeingGenerator.generateFollower(2));
        bloodCircle.addFollower(BeingGenerator.generateFollower(3));
        bloodCircle.addFollower(BeingGenerator.generateFollower(2));
    }

    @Override
    public void update(float dt) {
        bloodCircle.update(dt);
    }

    @Override
    public void render(float opacity) {
        batch.begin();
        batch.setColor(1,1,1, opacity);
        batch.draw(background,0,0, 0, 0, (int)Renderer.Get().GetCamera().viewportWidth, (int)Renderer.Get().GetCamera().viewportHeight);
        batch.end();

        // Enable alpha blending again
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backButton.render(opacity);
        bloodBank.render(opacity);
        mapDisplay.render(opacity);
        enemyDisplay.render(opacity);
        spellBar.render(opacity);
        shapeRenderer.end();

        bloodCircle.render(opacity);

        batch.begin();
        backButton.renderText(opacity);
        bloodBank.renderText(opacity);
        bloodCircle.renderText(opacity);
        batch.end();
    }

    public UIEnemyDisplay getEnemyDisplay(){
        return enemyDisplay;
    }

    @Override
    public void onScreenResize() {
        backButton.onScreenResize();
        bloodBank.onScreenResize();
        mapDisplay.onScreenResize();
        for(UIButton button : mapDisplay.getButtons()){
            unregisterElement(button);
        }
        mapDisplay.initButtons();
        for(UIButton button : mapDisplay.getButtons()){
            registerElement(button);
        }
        enemyDisplay.onScreenResize();
        spellBar.onScreenResize();
        bloodCircle.onScreenResize();
    }
}