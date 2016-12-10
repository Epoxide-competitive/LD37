package xyz.epoxide.ld37.client;

import java.util.HashMap;
import java.util.Map;

import xyz.epoxide.ld37.client.render.entity.RenderEntity;
import xyz.epoxide.ld37.client.render.entity.RenderEntityPlayer;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityPlayer;

public class ClientRegistry {
    
    public static Map<Class<? extends Entity>, RenderEntity> entityRenderMap = new HashMap<Class<? extends Entity>, RenderEntity>();
    
    static {
        registerEntityRender(EntityPlayer.class, new RenderEntityPlayer());
    }
    
    public static void registerEntityRender (Class<? extends Entity> entityClass, RenderEntity renderEntity) {
        
        entityRenderMap.put(entityClass, renderEntity);
    }
}
