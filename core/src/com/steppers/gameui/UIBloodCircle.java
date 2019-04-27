package com.steppers.gameui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.steppers.gamestate.Follower;
import com.steppers.ld44.Renderer;
import com.steppers.ui.UIElement;

import java.util.ArrayList;

public class UIBloodCircle extends UIElement {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private ArrayList<Follower> followers;
    private ArrayList<UIBeingCard> beingCards;

    public UIBloodCircle(float x, float y, float width, float height) {
        super(x, y, width, height);

        shapeRenderer = Renderer.Get().GetShapeRenderer();
        spriteBatch = Renderer.Get().GetSpriteBatch();

        followers = new ArrayList<>();
        beingCards = new ArrayList<>();
    }

    public void addFollower(Follower follower) {
        followers.add(follower);
        UIBeingCard card = new UIBeingCard(0,0,40,40);
        card.setAlignment(Alignment.ALIGN_C);
        card.setBeing(follower);
        beingCards.add(card);
    }

    public void removeFollower(Follower follower) {
        followers.remove(follower);
        for (UIBeingCard bc : beingCards) {
            if(bc.getBeing() == follower)
            {
                beingCards.remove(bc);
                break;
            }
        }
    }

    public void render(float opacity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, opacity);

        Vector2 v1 = new Vector2();
        Vector2 v2 = new Vector2();
        for(int i = 0; i < followers.size(); ++i)
        {
            v1.set(0, bounds.height/2);
            v1.setAngle(i * (360.0f / followers.size()) + 90);
            v1.add(bounds.x, bounds.y);
            for(int j = i; j < followers.size(); ++j)
            {
                v2.set(0, bounds.height/2);
                v2.setAngle(j * (360.0f / followers.size()) + 90);
                v2.add(bounds.x, bounds.y);
                shapeRenderer.rectLine(v1, v2, 3);
            }
        }

        for(int i = 0; i < beingCards.size(); ++i)
        {
            v1.set(0, bounds.height/2);
            v1.setAngle(i * (360.0f / followers.size()) + 90);
            v1.add(bounds.x, bounds.y);

            beingCards.get(i).moveTo(v1.x, v1.y);
            beingCards.get(i).render(opacity);
        }

        shapeRenderer.end();
    }
}
