package xyz.epoxide.ld37.client.render.entity;

import static xyz.epoxide.ld37.client.render.RenderManager.SCALE_HEIGHT;
import static xyz.epoxide.ld37.client.render.RenderManager.SCALE_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.utils.Direction;

public class RenderEntityPlayer extends RenderEntity {
    
    public RenderEntityPlayer() {
    }
    
    @Override
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
    	int animType = 0;
    	float baseU = 0;
    	float baseV = 0;
    	if (Math.abs(entity.getMotionX()) > 0.05f){
    		animType = 1;
    	}
    	if (animType == 0){
    		baseU = 0;
    		baseV = 0;
    	}
    	if (animType == 1){
    		baseU = (16.0f*(float)((entity.getAnimationStage()/2) % 4))/256.0f;
    		baseV = 16.0f/256.0f;
    	}
    	
        if (entity.getDirection().x == -1){
        	batch.draw(ENTITY_TEXTURE, Gdx.graphics.getWidth() / 2 - 16.0f, Gdx.graphics.getHeight() / 2, 32.0f, 32.0f, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
        }
        else {
        	batch.draw(ENTITY_TEXTURE, Gdx.graphics.getWidth() / 2 + 16.0f, Gdx.graphics.getHeight() / 2, -32.0f, 32.0f, baseU+0.0625f, baseV+0.0625f, baseU, baseV);
        }
    }
}
