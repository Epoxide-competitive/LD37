package xyz.epoxide.ld37;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import xyz.epoxide.ld37.client.render.RenderManager;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.input.InputHandler;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.world.World;

public class LD37 extends ApplicationAdapter {
    private static final boolean DEBUG = true;
    public static LD37 INSTANCE;
    public static final float tileWidth = 32.0f;
    
    public static int worldX = 11;
    public static int worldY = 0;
    
    private SpriteBatch batch;
    private BitmapFont font;
    public OrthographicCamera camera;
    private RenderManager renderManager;
    
    public EntityPlayer entityPlayer;
    public World world;
    
    public static final String ID = "ld37";
    
    
    private static double STEP = 1d / 6w0d;
    private static double prevTime;
    private static double accumulator = 0;
    
    @Override
    public void create () {
        
        LD37.INSTANCE = this;
        Gdx.graphics.setWindowedMode(640, 400);
        Gdx.input.setInputProcessor(new InputHandler());
        
        for (Controller controller : Controllers.getControllers()) {
            
            Gdx.app.log("INFO", controller.getName());
        }
        
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.renderManager = new RenderManager();
        Tile.init();
        this.world = new World();
        this.entityPlayer = new EntityPlayer(this.world);
        this.entityPlayer.setX(24);
        this.entityPlayer.setY(16);
        world.spawnEntity(entityPlayer, entityPlayer.getX(), entityPlayer.getY());
        world.loadTiles();
    }
    
    @Override
    public void render () {
        
        double currentTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(currentTime - prevTime, 0.25);
        final float delta = (float) frameTime;
        
        prevTime = currentTime;
        accumulator += frameTime;
        
        while (accumulator >= STEP) {
            
            accumulator -= STEP;
            update(delta);
        }
        
        // TODO Setup launch which are asset loading
        // TODO Setup Gui Rendering
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        this.batch.setProjectionMatrix(this.camera.combined);
        
        this.renderManager.renderGame(this.batch, delta);
        
        /*if (DEBUG) {
            
            this.batch.begin();
            this.font.draw(this.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, Gdx.graphics.getHeight() - 10);
            this.font.draw(this.batch, "GL_RENDERER = " + Gdx.gl.glGetString(GL20.GL_RENDERER), 10, Gdx.graphics.getHeight() - 30);
            this.font.draw(this.batch, "GL_VENDOR = " + Gdx.gl.glGetString(GL20.GL_VENDOR), 10, Gdx.graphics.getHeight() - 50);
            this.font.draw(this.batch, "GL_VERSION = " + Gdx.gl.glGetString(GL20.GL_VERSION), 10, Gdx.graphics.getHeight() - 70);
            
            this.font.draw(this.batch, "Player X = " + this.entityPlayer.getX(), 10, Gdx.graphics.getHeight() - 100);
            this.font.draw(this.batch, "Player Y = " + this.entityPlayer.getY(), 10, Gdx.graphics.getHeight() - 120);
            this.font.draw(this.batch, "Player On Ground = " + this.entityPlayer.onGround(), 10, Gdx.graphics.getHeight() - 140);
            this.font.draw(this.batch, "Background Dimensions: " + this.world.getMapWidth() + "," + this.world.getMapHeight(), 10, Gdx.graphics.getHeight() - 160);
            
            this.batch.end();
        }*/
    }
    
    /**
     * Updates game logic
     */
    private void update (float delta) {
        
        for (KeyBind key : KeyBind.REGISTRY) {
            
            if (key.isPressed()) {
                
                key.onUpdate(delta);
            }
        }
        
        if (this.world != null) {
            
            this.world.onUpdate(delta);
        }
    }
    
    @Override
    public void dispose () {
        
        this.batch.dispose();
        this.font.dispose();
    }
    
    /**
     * Save the games data and puts the user to the pause gui
     */
    @Override
    public void pause () {
        
        super.pause();
    }
    
    @Override
    public void resize (int width, int height) {
        
        this.camera.setToOrtho(false, width, height);
        this.renderManager.resize();
    }
}
