package com.steppers.gameui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.steppers.gamestate.Being;
import com.steppers.gamestate.Character;
import com.steppers.gamestate.Follower;
import com.steppers.ld44.Renderer;
import com.steppers.ui.MouseEvent;
import com.steppers.ui.UIElement;

import java.util.ArrayList;

public class UIBloodCircle extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private ArrayList<Being> bloodSources;
    private ArrayList<UIBeingCard> beingCards;
    private UIBeingCard characterCard;

    private float expandProgress;
    private UIBeingCard expandedCard;
    private int expandDirection;

    public UIBloodCircle(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        bloodSources = new ArrayList<>();
        beingCards = new ArrayList<>();

        expandProgress = 0.0f;
        expandedCard = null;
        expandDirection = 0;
    }

    public void setCharacter(Character character) {
        bloodSources.add(character);
        characterCard = new UIBeingCard(bounds.x,bounds.y,45,45);
        characterCard.setAlignment(Alignment.ALIGN_C);
        characterCard.setBeing(character);
    }

    public void addFollower(Follower follower) {
        bloodSources.add(follower);
        UIBeingCard card = new UIBeingCard(0,0,45,45);
        card.setAlignment(Alignment.ALIGN_C);
        card.setBeing(follower);
        beingCards.add(card);
    }

    public void removeFollower(Follower follower) {
        bloodSources.remove(follower);
        for (UIBeingCard bc : beingCards) {
            if(bc.getBeing() == follower)
            {
                beingCards.remove(bc);
                break;
            }
        }
    }

    public boolean handleMouseEvent(float x, float y, MouseEvent event) {
        if(expandedCard == null) {
            for (UIBeingCard card : beingCards) {
                if (event == MouseEvent.MOUSE_UP && card.isMouseOver(x, y)) {
                    // Expand card view
                    expandedCard = card;
                    expandProgress = 0.0f;
                    expandDirection = 1;
                    return true;
                }
            }
            if (event == MouseEvent.MOUSE_UP && characterCard.isMouseOver(x, y)) {
                // Expand card view
                expandedCard = characterCard;
                expandProgress = 0.0f;
                expandDirection = 1;
                return true;
            }
        } else if(expandProgress == 1.0f){
            for (UIBeingCard card : beingCards) {
                if (event == MouseEvent.MOUSE_UP && card.isMouseOver(x, y)) {
                    // Minimise card view
                    expandedCard = card;
                    expandDirection = -1;
                    return true;
                }
            }
            if (event == MouseEvent.MOUSE_UP && characterCard.isMouseOver(x, y)) {
                // Minimise card view
                expandedCard = characterCard;
                expandDirection = -1;
                return true;
            }
        }
        return false;
    }

    public void update(float dt) {
        if(expandedCard != null) {
            if(expandDirection == 1) {
                expandProgress += 3f * dt;
                expandProgress = Math.min(expandProgress, 1.0f);

                if(expandProgress == 1.0f) {
                    expandDirection = 0;
                }
            } else if(expandDirection == -1) {
                expandProgress -= 3f * dt;
                expandProgress = Math.max(expandProgress, 0.0f);
            }
        }
    }

    public void render(float opacity) {
        // Enable alpha blending
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.15f, 0.15f, 0.15f, opacity * 0.6f);
        shapeRenderer.circle(bounds.x, bounds.y, bounds.height / 2);

        shapeRenderer.setColor(0.9f, 0, 0, opacity);

        Vector2 v1 = new Vector2();
        Vector2 v2 = new Vector2();
        for(int i = 0; i < beingCards.size(); ++i)
        {
            v1.set(0, bounds.height/2);
            v1.setAngle(i * (360.0f / beingCards.size()) - 90);
            v1.add(bounds.x, bounds.y);
            beingCards.get(i).moveTo(v1.x, v1.y);
            beingCards.get(i).resize(45, 45);

            for(int j = i; j < beingCards.size(); ++j)
            {
                v2.set(0, bounds.height/2);
                v2.setAngle(j * (360.0f / beingCards.size()) - 90);
                v2.add(bounds.x, bounds.y);
                shapeRenderer.rectLine(v1, v2, 3);
            }
        }

        for(int i = 0; i < beingCards.size(); ++i) {
            if(beingCards.get(i) != expandedCard)
                beingCards.get(i).render(opacity);
        }

        if(expandedCard != characterCard)
            characterCard.render(opacity);

        characterCard.moveTo(bounds.x, bounds.y);
        characterCard.resize(45, 45);

        shapeRenderer.end();
    }

    public void renderText(float opacity) {
        for(int i = 0; i < beingCards.size(); ++i)
        {
            if(beingCards.get(i) != expandedCard)
                beingCards.get(i).renderText(opacity);
        }
        if(expandedCard != characterCard)
            characterCard.renderText(opacity);
        spriteBatch.end();

        // Enable alpha blending
        Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(expandedCard != null)
        {
            float newSize = 45 + (bounds.height) * expandProgress;
            Alignment align = expandedCard.getAlignment();
            expandedCard.resetAlignment();
            Rectangle cardBounds = expandedCard.getBounds();
            float newX = cardBounds.x + (bounds.x - cardBounds.x) * expandProgress;
            float newY = cardBounds.y + (bounds.y - cardBounds.y) * expandProgress;
            expandedCard.resize(newSize, newSize);
            expandedCard.moveTo(newX, newY);
            expandedCard.setAlignment(align);
            expandedCard.render(opacity);

            if(expandProgress == 0.0f) {
                expandDirection = 0;
                expandedCard = null;
            }
        }
        shapeRenderer.end();

        spriteBatch.begin();
        if(expandedCard != null)
            expandedCard.renderText(opacity);
    }
}
