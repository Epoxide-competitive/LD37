package xyz.epoxide.ld37.entity;

import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class Entity {
    
    public static final NamedRegistry<Class<? extends Entity>> REGISTRY = new NamedRegistry<Class<? extends Entity>>();
    
    private float x;
    private float y;
    public World world;
    private boolean killed;
    
    public Entity(World world) {
        
        this.world = world;
    }
    
    public void onEntitySpawned () {
        
    }
    
    public void onEntityKilled () {
        
    }
    
    public void onUpdate () {
        
    }
    
    public void setDead () {
        
        this.killed = true;
        this.onEntityKilled();
    }
    
    public void revive () {
        
        this.killed = false;
    }
    
    public boolean isDead () {
        
        return this.killed;
    }
    
    public boolean canSpawnHere (float x, float y) {
        
        return true;
    }
    
    public void setPosition (float x, float y) {
        
        this.x = x;
        this.y = y;
    }
    
    public float getX () {
        
        return this.x;
    }
    
    public float getY () {
        
        return this.y;
    }
    
    public double getDistance (Entity entity) {
        
        return getDistance(entity.getX(), entity.getY());
    }
    
    public double getDistance (float x, float y) {
        
        return Math.sqrt(Math.pow((x - this.getX()), 2) + Math.pow((y - this.getY()), 2));
    }
    
    public Identifier getId () {
        
        return REGISTRY.getIdentifier(this.getClass());
    }
    
    public static void registerEntity (String name, Class<? extends Entity> clazz) {
        
        REGISTRY.registerValue(new Identifier(LD37.ID, name), clazz);
    }
    
    public static Entity createEntityById (String id, World world) {
        
        return createEntityById(new Identifier(id), world);
    }
    
    public static Entity createEntityById (Identifier id, World world) {
        
        Entity entity = null;
        
        try {
            
            final Class<? extends Entity> clazz = REGISTRY.getValue(id);
            
            if (clazz != null) {
                
                entity = (Entity) clazz.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
            }
            
        }
        
        catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return entity;
    }
}
