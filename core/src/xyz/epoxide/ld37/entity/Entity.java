package xyz.epoxide.ld37.entity;

import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class Entity {
    
    public static final NamedRegistry<Class<? extends Entity>> REGISTRY = new NamedRegistry<Class<? extends Entity>>();
    
    public float x;
    public float y;
    public World world;
    
    public Entity(World world) {
        
        this.world = world;
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
