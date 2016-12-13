package xyz.epoxide.ld37.client.render.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityItem;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.world.World;

public class RenderEntityItem extends RenderEntity {
    
    public RenderEntityItem() {
    }
    
    @Override
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
		float baseU = (32.0f+16.0f*((EntityItem)entity).item)/256.0f;
		float baseV = 16.0f/256.0f;
    	
        EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;
        World world = LD37.INSTANCE.world;
        int rx = (int)(entityPlayer.getX()*LD37.tileWidth-Gdx.graphics.getWidth()/2);
        int ry = (int)(entityPlayer.getY()*LD37.tileWidth-Gdx.graphics.getHeight()/2);
        if (entityPlayer.getX()*LD37.tileWidth < Gdx.graphics.getWidth()/2){
        	rx = 0;
        }
        if (entityPlayer.getY()*LD37.tileWidth < Gdx.graphics.getHeight()/2){
        	ry = 0;
        }
        if (entityPlayer.getX()*LD37.tileWidth > world.getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth()/2){
        	rx = (int)(world.getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth());
        }
        if (entityPlayer.getY()*LD37.tileWidth > world.getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight()/2){
        	ry = (int)(world.getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight());
        }
    	
        if (entity.getDirection().x == -1){
        	batch.draw(ENTITY_TEXTURE, (int)(entity.getX()*LD37.tileWidth)-1.0f*LD37.tileWidth-rx, (int)(entity.getY()*LD37.tileWidth)-ry, 2.0f*LD37.tileWidth, 2.0f*LD37.tileWidth, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
        }
        else {
        	batch.draw(ENTITY_TEXTURE, (int)(entity.getX()*LD37.tileWidth) + 1.0f*LD37.tileWidth - rx, (int)(entity.getY()*LD37.tileWidth)-ry, -2.0f*LD37.tileWidth, 2.0f*LD37.tileWidth, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
        }
    }
}
