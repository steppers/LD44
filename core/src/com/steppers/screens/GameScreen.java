package com.steppers.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.steppers.gamestate.Character;
import com.steppers.gamestate.Follower;
import com.steppers.gamestate.GameState;
import com.steppers.gamestate.Map;
import com.steppers.gameui.UIBloodBank;
import com.steppers.gameui.UIBloodCircle;
import com.steppers.gameui.UIEnemyDisplay;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIButton;
import com.steppers.ui.UIElement;
import com.steppers.ui.UIManager;
import com.steppers.ui.UIMapDisplay;
import com.steppers.ui.UIScreen;
import com.steppers.ui.UITextButton;

public class GameScreen extends UIScreen {

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    UIButton backButton;
    UIBloodBank bloodBank;
    UIBloodCircle bloodCircle;

    Texture background;

    UIMapDisplay mapDisplay;
    UIEnemyDisplay enemyDisplay;


    public GameScreen(GameState gameState) {
        shapeRenderer = Renderer.Get().GetShapeRenderer();
        batch = Renderer.Get().GetSpriteBatch();

        backButton = new UITextButton(5, 5, 60, 50, "Back");
        backButton.setHandler(() -> {
            UIManager.Get().transitionToScreen("main_menu", 0.3f);
        });
        registerElement(backButton);

        mapDisplay = new UIMapDisplay(650, 160, 260, 180, gameState.getDungeonMap());
        for(UIButton button : mapDisplay.getButtons()){
            registerElement(button);
        }

        background = Renderer.Get().GetBackgroundTexture();

        bloodBank = new UIBloodBank(0, 512, 0, 36);
        bloodBank.setAlignment(UIElement.Alignment.ALIGN_TL);

        enemyDisplay = new UIEnemyDisplay(50, 100, 200, 200, gameState);
        enemyDisplay.convertToPercentagePos();

        bloodCircle = new UIBloodCircle(50, 44, 300, 300);
        bloodCircle.convertToPercentagePos();
        Character character = gameState.getCharacter();
        bloodCircle.setCharacter(character);
        for(Follower f : character.getFollowers()) {
            bloodCircle.addFollower(f);
        }

        bloodCircle.addFollower(new Follower(3));
        bloodCircle.addFollower(new Follower(5));
        bloodCircle.addFollower(new Follower(2));
        bloodCircle.addFollower(new Follower(8));
        bloodCircle.addFollower(new Follower(3));
    }

    @Override
    public void render(float opacity) {
        batch.begin();
        batch.setColor(1,1,1, opacity);
        batch.draw(background,0,0, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        // Enable alpha blending again
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backButton.render(opacity);
        bloodBank.render(opacity);
        mapDisplay.render(opacity);
        enemyDisplay.render(opacity);
        shapeRenderer.end();

        bloodCircle.render(opacity);

        batch.begin();
        backButton.renderText(opacity);
        bloodBank.renderText(opacity);
        bloodCircle.renderText(opacity);
        batch.end();
    }
}