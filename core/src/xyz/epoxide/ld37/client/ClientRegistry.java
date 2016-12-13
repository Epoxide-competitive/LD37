package xyz.epoxide.ld37.client;

import java.util.HashMap;
import java.util.Map;

import xyz.epoxide.ld37.client.render.entity.RenderEntity;
import xyz.epoxide.ld37.client.render.entity.RenderEntityBoss;
import xyz.epoxide.ld37.client.render.entity.RenderEntityBullet;
import xyz.epoxide.ld37.client.render.entity.RenderEntityFire;
import xyz.epoxide.ld37.client.render.entity.RenderEntityGolem;
import xyz.epoxide.ld37.client.render.entity.RenderEntityGun;
import xyz.epoxide.ld37.client.render.entity.RenderEntityItem;
import xyz.epoxide.ld37.client.render.entity.RenderEntityLightning;
import xyz.epoxide.ld37.client.render.entity.RenderEntityMask;
import xyz.epoxide.ld37.client.render.entity.RenderEntityParticle;
import xyz.epoxide.ld37.client.render.entity.RenderEntityPlayer;
import xyz.epoxide.ld37.client.render.entity.RenderEntityRadish;
import xyz.epoxide.ld37.client.render.entity.RenderEntitySlime;
import xyz.epoxide.ld37.client.render.entity.RenderEntitySprout;
import xyz.epoxide.ld37.client.render.entity.RenderEntitySword;
import xyz.epoxide.ld37.client.render.entity.RenderEntityWisp;
import xyz.epoxide.ld37.client.render.entity.RenderEntityYouWin;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityBoss;
import xyz.epoxide.ld37.entity.EntityBullet;
import xyz.epoxide.ld37.entity.EntityFire;
import xyz.epoxide.ld37.entity.EntityGolem;
import xyz.epoxide.ld37.entity.EntityGun;
import xyz.epoxide.ld37.entity.EntityItem;
import xyz.epoxide.ld37.entity.EntityLightning;
import xyz.epoxide.ld37.entity.EntityMask;
import xyz.epoxide.ld37.entity.EntityParticle;
import xyz.epoxide.ld37.entity.EntityPitManager;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.entity.EntityRadish;
import xyz.epoxide.ld37.entity.EntitySlime;
import xyz.epoxide.ld37.entity.EntitySprout;
import xyz.epoxide.ld37.entity.EntitySword;
import xyz.epoxide.ld37.entity.EntityWisp;
import xyz.epoxide.ld37.entity.EntityYouWin;

public class ClientRegistry {
    
    public static Map<Class<? extends Entity>, RenderEntity> entityRenderMap = new HashMap<Class<? extends Entity>, RenderEntity>();
    
    static {
        registerEntityRender(EntityPlayer.class, new RenderEntityPlayer());
        registerEntityRender(EntitySword.class, new RenderEntitySword());
        registerEntityRender(EntitySprout.class, new RenderEntitySprout());
        registerEntityRender(EntityRadish.class, new RenderEntityRadish());
        registerEntityRender(EntityParticle.class, new RenderEntityParticle());
        registerEntityRender(EntityGun.class, new RenderEntityGun());
        registerEntityRender(EntityBullet.class, new RenderEntityBullet());
        registerEntityRender(EntityPitManager.class, new RenderEntity());
        registerEntityRender(EntityItem.class, new RenderEntityItem());
        registerEntityRender(EntityGolem.class, new RenderEntityGolem());
        registerEntityRender(EntityMask.class, new RenderEntityMask());
        registerEntityRender(EntitySlime.class, new RenderEntitySlime());
        registerEntityRender(EntityWisp.class, new RenderEntityWisp());
        registerEntityRender(EntityFire.class, new RenderEntityFire());
        registerEntityRender(EntityBoss.class, new RenderEntityBoss());
        registerEntityRender(EntityLightning.class, new RenderEntityLightning());
        registerEntityRender(EntityYouWin.class, new RenderEntityYouWin());
    }
    
    public static void registerEntityRender (Class<? extends Entity> entityClass, RenderEntity renderEntity) {
        
        entityRenderMap.put(entityClass, renderEntity);
    }
}
