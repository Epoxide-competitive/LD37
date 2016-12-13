package xyz.epoxide.ld37.client.render.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityYouWin;

public class RenderEntityYouWin extends RenderEntity {
    
    public RenderEntityYouWin() {
    }
    
    @Override
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
    	float baseU = 128.0f/256.0f;
    	float baseV = 48.0f/256.0f;
    	
    	float rx = Gdx.graphics.getWidth() / 2 - 6.0f*LD37.tileWidth;
    	float ry = Gdx.graphics.getHeight() / 2 - 6.0f*LD37.tileWidth;
    	batch.setColor(1, 1, 1, ((EntityYouWin)entity).alpha*0.25f);
        batch.draw(PLAYER_TEXTURE, (int)rx, (int)ry, 12.0f*LD37.tileWidth, 12.0f*LD37.tileWidth, baseU, baseV+0.1875f, baseU+0.1875f, baseV);
        batch.setColor(1,1,1,1);
    }
}
