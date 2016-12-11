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
    public static final float COUNT_HEIGHT = 16.0f;
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
    
    public enum TileLayer {
    	LAYER_BACKGROUND, LAYER_WORLD, LAYER_FOREGROUND
    }
    
    public void renderGame (SpriteBatch batch, float delta) {
        
        World world = LD37.INSTANCE.world;
        renderTiles(batch, world, world.getBackgroundTileMap(), delta, TileLayer.LAYER_BACKGROUND);
        renderEntity(batch, world.getEntities(), delta);
        renderTiles(batch, world, world.getTileMap(), delta, TileLayer.LAYER_WORLD);
        renderTiles(batch, world, world.getForegroundTileMap(), delta, TileLayer.LAYER_WORLD);
        renderParticles(batch, delta);
    }
    
    private void renderTiles (SpriteBatch batch, World world, Tile[][] tileMap, float delta, TileLayer layer) {
        final EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;
        
        int x = (int)(entityPlayer.getX()*LD37.tileWidth-Gdx.graphics.getWidth()/2);
        int y = (int)(entityPlayer.getY()*LD37.tileWidth-Gdx.graphics.getHeight()/2);
        if (entityPlayer.getX()*LD37.tileWidth < Gdx.graphics.getWidth()/2){
        	x = 0;
        }
        if (entityPlayer.getY()*LD37.tileWidth < Gdx.graphics.getHeight()/2){
        	y = 0;
        }
        if (entityPlayer.getX()*LD37.tileWidth > world.getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth()/2){
        	x = (int)(world.getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth());
        }
        if (entityPlayer.getY()*LD37.tileWidth > world.getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight()/2){
        	y = (int)(world.getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight());
        }
        
        batch.begin();
    	batch.setColor(1.0f,1.0f,1.0f,1.0f);
        if (layer == TileLayer.LAYER_BACKGROUND){
        	batch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        }
        for (int i = 0; i < tileMap.length; i++) {
        	for (int j = 0; j < tileMap[i].length; j ++){
                Tile t = tileMap[i][j];
                if (t != Tile.VOID){
                    batch.draw(TILE_TEXTURE, ((float)i*LD37.tileWidth)-x, ((float)j*LD37.tileWidth)-y, LD37.tileWidth, LD37.tileWidth, t.u2, t.v2, t.u, t.v);
                }
            }
        }
        batch.end();
    }
    
    private void renderEntity (SpriteBatch batch, List<Entity> entities, float delta) {
        
        batch.begin();
    	batch.setColor(1.0f,1.0f,1.0f,1.0f);
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
