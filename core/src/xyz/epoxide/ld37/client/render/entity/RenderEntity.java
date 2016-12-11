package xyz.epoxide.ld37.client.render.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.epoxide.ld37.entity.Entity;

public class RenderEntity {
    public static final Texture PLAYER_TEXTURE = new Texture("assets/ld37/textures/char/char.png");
    public static final Texture ENTITY_TEXTURE = new Texture("assets/ld37/textures/char/entity.png");
    
    public RenderEntity() {
        
    }
    
    public void render (SpriteBatch batch, Entity entity, int x, int y, float delta) {
        
    }
}
