package xyz.epoxide.ld37.client.render;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.client.ClientRegistry;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.world.World;

public class RenderManager {
    public static final float COUNT_WIDTH = 16.0f;
    public static final float COUNT_HEIGHT = 14.0f;
    public static float SCALE_WIDTH;
    public static float SCALE_HEIGHT;
    
    private final Texture TILE_TEXTURE;
    
    public RenderManager() {
    	try {
        	OutputStream stream = new FileOutputStream("test.txt");
			stream.write("HELLO!".getBytes());
	    	stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.TILE_TEXTURE = new Texture("assets/ld37/textures/tile/tiles.png");
    }
    
    public void renderGame (SpriteBatch batch, float delta) {
        
        World world = LD37.INSTANCE.world;
        renderTiles(batch, world.getBackgroundTileMap(), delta);
        renderEntity(batch, world.getEntities(), delta);
        renderTiles(batch, world.getForegroundTileMap(), delta);
        renderParticles(batch, delta);
    }
    
    private void renderTiles (SpriteBatch batch, Tile[][] tileMap, float delta) {
        
        final EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;
        
        final float x = entityPlayer.getX();
        final float y = entityPlayer.getY();
        
        final float xRem = (float) (x - Math.floor(x));
        final float yRem = (float) (y - Math.floor(y));
        
        batch.begin();
        for (int j = (int) -(COUNT_WIDTH / 2) - 1; j < COUNT_WIDTH / 2 + 1; j++) {
            int yy = (int) (j + Math.floor(y));
            for (int i = (int) -(COUNT_HEIGHT / 2) - 1; i < COUNT_HEIGHT / 2 + 1; i++) {
                int xx = (int) (i + Math.floor(x));
                if (xx >= 0 && xx < tileMap.length && yy >= 0 && yy < tileMap[xx].length) {
                    Tile t = tileMap[xx][yy];
                    if (t != Tile.VOID)
                        batch.draw(TILE_TEXTURE, (i - xRem + COUNT_WIDTH / 2.0f) * SCALE_WIDTH, (j - yRem + COUNT_HEIGHT / 2.0f) * SCALE_HEIGHT, 16, 16, t.u2, t.v2, t.u, t.v);
                }
            }
        }
        batch.end();
    }
    
    private void renderEntity (SpriteBatch batch, List<Entity> entities, float delta) {
        
        batch.begin();
        for (Entity entity : entities) {
            ClientRegistry.entityRenderMap.get(entity.getClass()).render(batch, entity, 0, 0, delta);
        }
        batch.end();
    }
    
    private void renderParticles (SpriteBatch batch, float delta) {
        
    }
    
    public void resize () {
        
        SCALE_WIDTH = Gdx.graphics.getWidth() / COUNT_WIDTH;
        SCALE_HEIGHT = Gdx.graphics.getHeight() / COUNT_HEIGHT;
    }
}
