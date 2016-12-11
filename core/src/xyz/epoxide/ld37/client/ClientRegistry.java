package xyz.epoxide.ld37.client;

import java.util.HashMap;
import java.util.Map;

import xyz.epoxide.ld37.client.render.entity.RenderEntity;
import xyz.epoxide.ld37.client.render.entity.RenderEntityBullet;
import xyz.epoxide.ld37.client.render.entity.RenderEntityGun;
import xyz.epoxide.ld37.client.render.entity.RenderEntityParticle;
import xyz.epoxide.ld37.client.render.entity.RenderEntityPlayer;
import xyz.epoxide.ld37.client.render.entity.RenderEntitySprout;
import xyz.epoxide.ld37.client.render.entity.RenderEntitySword;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityBullet;
import xyz.epoxide.ld37.entity.EntityGun;
import xyz.epoxide.ld37.entity.EntityParticle;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.entity.EntitySprout;
import xyz.epoxide.ld37.entity.EntitySword;

public class ClientRegistry {
    
    public static Map<Class<? extends Entity>, RenderEntity> entityRenderMap = new HashMap<Class<? extends Entity>, RenderEntity>();
    
    static {
        registerEntityRender(EntityPlayer.class, new RenderEntityPlayer());
        registerEntityRender(EntitySword.class, new RenderEntitySword());
        registerEntityRender(EntitySprout.class, new RenderEntitySprout());
        registerEntityRender(EntityParticle.class, new RenderEntityParticle());
        registerEntityRender(EntityGun.class, new RenderEntityGun());
        registerEntityRender(EntityBullet.class, new RenderEntityBullet());
    }
    
    public static void registerEntityRender (Class<? extends Entity> entityClass, RenderEntity renderEntity) {
        
        entityRenderMap.put(entityClass, renderEntity);
    }
}
