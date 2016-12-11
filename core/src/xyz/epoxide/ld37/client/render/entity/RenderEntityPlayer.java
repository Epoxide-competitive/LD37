package xyz.epoxide.ld37.client.render.entity;

import static xyz.epoxide.ld37.client.render.RenderManager.SCALE_HEIGHT;
import static xyz.epoxide.ld37.client.render.RenderManager.SCALE_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.entity.Entity;

public class RenderEntityPlayer extends RenderEntity {
    
    public RenderEntityPlayer() {
    }
    
    @Override
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
        
        batch.draw(ENTITY_TEXTURE, Gdx.graphics.getWidth() / 2 - SCALE_WIDTH / 2, Gdx.graphics.getHeight() / 2 - SCALE_HEIGHT / 2, SCALE_WIDTH, SCALE_HEIGHT, 16.0f / 256.0f, 16.0f / 256.0f, 0, 0);
    }
}
