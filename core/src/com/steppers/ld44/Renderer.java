package com.steppers.ld44;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;

public class Renderer {

    // Singleton
    static Renderer ins = new Renderer();
    public static Renderer Get() {
        return ins;
    }

    private Renderer() {}

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private OrthographicCamera camera = new OrthographicCamera();
    private SpriteBatch spriteBatch = new SpriteBatch();

    private GlyphLayout glyphLayout = new GlyphLayout();
    private BitmapFont font12;
    private BitmapFont font16;
    private BitmapFont font20;
    private BitmapFont font24;
    private BitmapFont font96;

    private Texture background;
    private Texture bloodTex;
    private Texture sacrificeTex;
    private Texture bloodOverlayTex;
    private HashMap<String, Texture> textures;

    private Color bloodColor;

    public ShapeRenderer GetShapeRenderer() {
        return shapeRenderer;
    }
    public SpriteBatch GetSpriteBatch() { return spriteBatch; }

    public GlyphLayout GetGlyphLayout() { return glyphLayout; }
    public BitmapFont GetFont12() { return font12; }
    public BitmapFont GetFont16() { return font16; }
    public BitmapFont GetFont20() { return font20; }
    public BitmapFont GetFont24() { return font24; }
    public BitmapFont GetFont96() { return font96; }

    public OrthographicCamera GetCamera() {
        return camera;
    }

    public Texture GetBackgroundTexture() {
        return background;
    }
    public Texture GetBloodTexture() {
        return bloodTex;
    }
    public Texture GetSacrificeTexture() { return sacrificeTex; }
    public Texture GetBloodOverlayTexture() { return bloodOverlayTex; }
    public Texture GetTexture(String id) { return textures.get(id); }

    public Color GetBloodColor() { return bloodColor; }

    public void Setup() {
        SetupCamera();
        SetupFonts();
        SetupTextures();

        bloodColor = new Color(0.8f, 0, 0, 1.0f);
    }

    private void SetupFonts() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("Intruder.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        freeTypeFontParameter.size = 12;
        fontGenerator.generateData(freeTypeFontParameter);
        font12 = fontGenerator.generateFont(freeTypeFontParameter);


        freeTypeFontParameter.size = 16;
        fontGenerator.generateData(freeTypeFontParameter);
        font16 = fontGenerator.generateFont(freeTypeFontParameter);


        freeTypeFontParameter.size = 20;
        fontGenerator.generateData(freeTypeFontParameter);
        font20 = fontGenerator.generateFont(freeTypeFontParameter);


        freeTypeFontParameter.size = 24;
        fontGenerator.generateData(freeTypeFontParameter);
        font24 = fontGenerator.generateFont(freeTypeFontParameter);


        freeTypeFontParameter.size = 96;
        fontGenerator.generateData(freeTypeFontParameter);
        font96 = fontGenerator.generateFont(freeTypeFontParameter);
    }

    public void SetupCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float aspect_ratio = w / h;

        camera.setToOrtho(false, aspect_ratio * 512, 512);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    private void SetupTextures() {
        textures = new HashMap<>();

        background = new Texture("StoneWallTileableSmall.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bloodTex = new Texture("blood_droplet.png");
        sacrificeTex = new Texture("sacrifice.png");
        bloodOverlayTex = new Texture("blood_overlay.png");

        textures.put("being_horse", new Texture("PixelHorse.png"));
        textures.put("being_cow", new Texture("PixelCow.png"));
        textures.put("being_sheep", new Texture("PixelSheep.png"));
        textures.put("being_owl", new Texture("PixelOwl.png"));
        textures.put("being_chicken", new Texture("PixelChicken.png"));

        textures.put("being_blood_mage", new Texture("PixelBloodMage.png"));
        textures.put("being_mage", new Texture("PixelMage.png"));
        textures.put("being_kraken", new Texture("PixelKraken.png"));
        textures.put("being_skeleton", new Texture("PixelSkeleton.png"));
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

}
