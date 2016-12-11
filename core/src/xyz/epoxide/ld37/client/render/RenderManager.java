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
import xyz.epoxide.ld37.entity.EntityParticle;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.world.World;

public class RenderManager {
    public static final float COUNT_WIDTH = 16.0f;
    public static final float COUNT_HEIGHT = 16.0f;
    public static float SCALE_WIDTH;
    public static float SCALE_HEIGHT;
    
    private final Texture TILE_TEXTURE;
    
    public final Texture PLAYER_TEXTURE;
    
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
        this.PLAYER_TEXTURE = new Texture("assets/ld37/textures/char/char.png");
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
        
        batch.begin();
        for (int i = 0; i < LD37.INSTANCE.entityPlayer.getMaxHealth(); i ++){
        	if (i == 0){
        		batch.draw(PLAYER_TEXTURE, 1.0f*(LD37.tileWidth/8.0f), Gdx.graphics.getHeight()-11.0f*(LD37.tileWidth/8.0f), 9.0f*(LD37.tileWidth/8.0f), 10.0f*(LD37.tileWidth/8.0f), 80.0f/256.0f, 0.0f/256.0f, 89.0f/256.0f, 10.0f/256.0f);
        	}
        	else if (i+1 >= LD37.INSTANCE.entityPlayer.getMaxHealth()){
        		batch.draw(PLAYER_TEXTURE, 1.0f*(LD37.tileWidth/8.0f)+((i-1)*7.0f+9.0f)*(LD37.tileWidth/8.0f), Gdx.graphics.getHeight()-11.0f*(LD37.tileWidth/8.0f), 8.0f*(LD37.tileWidth/8.0f), 10.0f*(LD37.tileWidth/8.0f), 96.0f/256.0f, 0.0f/256.0f, 104.0f/256.0f, 10.0f/256.0f);
            }
        	else {
        		batch.draw(PLAYER_TEXTURE, 1.0f*(LD37.tileWidth/8.0f)+((i-1)*7.0f+9.0f)*(LD37.tileWidth/8.0f), Gdx.graphics.getHeight()-11.0f*(LD37.tileWidth/8.0f), 7.0f*(LD37.tileWidth/8.0f), 10.0f*(LD37.tileWidth/8.0f), 89.0f/256.0f, 0.0f/256.0f, 96.0f/256.0f, 10.0f/256.0f);
            }
        	if (i > LD37.INSTANCE.entityPlayer.getHealth()){
        		batch.draw(PLAYER_TEXTURE, 1.0f*(LD37.tileWidth/8.0f)+((i-1)*7.0f+9.0f)*(LD37.tileWidth/8.0f), Gdx.graphics.getHeight()-9.0f*(LD37.tileWidth/8.0f), 6.0f*(LD37.tileWidth/8.0f), 6.0f*(LD37.tileWidth/8.0f), 70.0f/256.0f, 10.0f/256.0f, 76.0f/256.0f, 16.0f/256.0f);
            }
        	else {
        		batch.draw(PLAYER_TEXTURE, 1.0f*(LD37.tileWidth/8.0f)+((i-1)*7.0f+9.0f)*(LD37.tileWidth/8.0f), Gdx.graphics.getHeight()-9.0f*(LD37.tileWidth/8.0f), 6.0f*(LD37.tileWidth/8.0f), 6.0f*(LD37.tileWidth/8.0f), 64.0f/256.0f, 10.0f/256.0f, 70.0f/256.0f, 16.0f/256.0f);
            }
        }
        batch.end();
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
        	if (entity.canRender()){
        		ClientRegistry.entityRenderMap.get(entity.getClass()).render(batch, entity, 0, 0, delta);
        	}
        }
        batch.end();
    }
    
    private void renderParticles (SpriteBatch batch, float delta) {

        batch.begin();
    	batch.setColor(1.0f,1.0f,1.0f,1.0f);
        for (EntityParticle entity : LD37.INSTANCE.world.getParticleList()) {
            ClientRegistry.entityRenderMap.get(entity.getClass()).render(batch, entity, 0, 0, delta);
        }
        batch.end();
    }
    
    public void resize () {
        
        SCALE_WIDTH = Gdx.graphics.getWidth() / COUNT_WIDTH;
        SCALE_HEIGHT = Gdx.graphics.getHeight() / COUNT_HEIGHT;
    }
}
