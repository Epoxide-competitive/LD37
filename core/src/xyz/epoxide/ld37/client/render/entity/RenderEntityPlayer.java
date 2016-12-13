package xyz.epoxide.ld37.client.render.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityPlayer;

public class RenderEntityPlayer extends RenderEntity {
    
    public RenderEntityPlayer() {
    }
    
    @Override
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
    	float baseU = 0;
    	float baseV = 0;
    	if (entity.getAnimationType() == 0){
    		baseU = 0;
    		baseV = 0;
    	}
    	if (entity.getAnimationType() == 1){
    		baseU = (16.0f*((entity.getAnimationStage()/2) % 4))/256.0f;
    		baseV = 16.0f/256.0f;
    	}
    	if (entity.getAnimationType() == 2){
    		baseU = (16.0f*((entity.getAnimationStage()) % 8))/256.0f;
    		baseV = 32.0f/256.0f;
    	}
    	if (entity.getAnimationType() == 3){
    		if (entity.getAnimationStage() == 0){
    			baseU = 32.0f/256.0f;
    		}
    		else {
    			baseU = 48.0f/256.0f;
    		}
    		baseV = 0.0f/256.0f;
    	}
    	float rx = Gdx.graphics.getWidth() / 2 - 1.0f*LD37.tileWidth;
    	float ry = Gdx.graphics.getHeight() / 2;
    	if (entity.getX()*LD37.tileWidth < Gdx.graphics.getWidth()/2){
    		rx = (entity.getX()-1.0f)*LD37.tileWidth;
    	}
    	if (entity.getY()*LD37.tileWidth < Gdx.graphics.getHeight()/2){
    		ry = (entity.getY())*LD37.tileWidth;
    	}
    	if (entity.getX()*LD37.tileWidth > entity.getWorld().getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth()/2){
    		rx = (entity.getX()-1.0f)*LD37.tileWidth-(entity.getWorld().getMapWidth()*LD37.tileWidth-Gdx.graphics.getWidth());
    	}
    	if (entity.getY()*LD37.tileWidth > entity.getWorld().getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight()/2){
    		ry = (entity.getY())*LD37.tileWidth-(entity.getWorld().getMapHeight()*LD37.tileWidth-Gdx.graphics.getHeight());
    	}
    	if (((EntityPlayer)entity).deathTimer == 0){
	        if (entity.getDirection().x == -1){
	        	batch.draw(PLAYER_TEXTURE, (int)rx, (int)ry, 2.0f*LD37.tileWidth, 2.0f*LD37.tileWidth, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
	        }
	        else {
	        	batch.draw(PLAYER_TEXTURE, (int)rx + 2.0f*LD37.tileWidth, (int)ry, -2.0f*LD37.tileWidth, 2.0f*LD37.tileWidth, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
	        }
    	}
    }
}
