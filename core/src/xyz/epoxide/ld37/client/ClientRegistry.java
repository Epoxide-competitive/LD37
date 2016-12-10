package xyz.epoxide.ld37.client;

import xyz.epoxide.ld37.client.render.entity.RenderEntity;
import xyz.epoxide.ld37.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class ClientRegistry {

    public static Map<Class<? extends Entity>, RenderEntity> entityRenderMap = new HashMap<>();

    public static void registerEntityRender(Class<? extends Entity> entityClass, RenderEntity renderEntity) {
        entityRenderMap.put(entityClass, renderEntity);
    }
}
