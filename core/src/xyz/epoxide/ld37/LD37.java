package xyz.epoxide.ld37;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.epoxide.ld37.client.RenderManager;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.input.InputHandler;
import xyz.epoxide.ld37.world.World;

public class LD37 extends ApplicationAdapter {
    public static LD37 INSTANCE;

    private SpriteBatch batch;
    private BitmapFont font;
    public OrthographicCamera camera;
    private RenderManager renderManager;

    public EntityPlayer entityPlayer;
    public World world;

    @Override
    public void create() {
        LD37.INSTANCE = this;

        Gdx.input.setInputProcessor(new InputHandler());
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.renderManager = new RenderManager();
        this.entityPlayer = new EntityPlayer();
        this.world = new World();
        this.entityPlayer.world = this.world;
    }

    @Override
    public void render() {
        final float delta = Gdx.graphics.getDeltaTime();
        // TODO Setup Limited Updates
        this.update(delta);
        // TODO Setup launch which are asset loading
        // TODO Setup Gui Rendering

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.setProjectionMatrix(this.camera.combined);

        this.renderManager.renderGame(this.batch);

        this.batch.begin();
        this.font.draw(this.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, Gdx.graphics.getHeight() - 10);
        this.batch.end();
    }

    /**
     * Updates game logic
     */
    private void update(float delta) {

    }

    @Override
    public void dispose() {

        this.batch.dispose();
        this.font.dispose();
    }

    /**
     * Save the games data and puts the user to the pause gui
     */
    @Override
    public void pause() {

        super.pause();
    }

    @Override
    public void resize(int width, int height) {

        this.camera.setToOrtho(false, width, height);
    }
}
