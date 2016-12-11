package xyz.epoxide.ld37.entity;

import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.utils.Direction;
import xyz.epoxide.ld37.world.World;

public class Entity {
    
    public static final NamedRegistry<Class<? extends Entity>> REGISTRY = new NamedRegistry<Class<? extends Entity>>();
    
    private float x;
    private float y;
    private float motionX;
    private float motionY;
    
    private World world;
    
    private Direction direction;
    
    private boolean killed;
    private boolean onGround;
    
    public Entity(World world) {
        
        this.world = world;
        this.world.spawnEntity(this, this.getX(), this.getY());
    }
    
    public void onEntitySpawned () {
        
    }
    
    public void onEntityKilled (CombatSource source) {
        
    }
    
    public void onUpdate (float delta) {
        
        this.direction = (this.getMotionX() == 0) ? Direction.STILL : (this.getMotionX() > 0) ? Direction.RIGHT : Direction.LEFT;
        
        this.setX(this.getX() + this.getMotionX());
        this.setY(this.getY() + this.getMotionY());
        
        if (this.getMotionX() != 0) {
            
            if (this.getMotionX() < 0.00001 && this.getMotionX() > -0.00001) {
                
                this.setMotionX(0);
            }
            
            else {
                
                this.setMotionX(this.getMotionX() * 0.5f);
            }
        }
        
        Tile tile = this.getWorld().getTileBelow(this, Direction.DOWN);
        this.onGround = tile != Tile.VOID;
        if (onGround) {
            this.setMotionY(0);
        }
        else {
            if (this.getY() > 0)
                this.setMotionY(-delta);
            else
                this.setY(0);
        }
    }
    
    public void setDead (CombatSource source) {
        
        this.killed = true;
        this.onEntityKilled(source);
    }
    
    public void revive () {
        
        this.killed = false;
    }
    
    public boolean isDead () {
        
        return this.killed;
    }
    
    public boolean onGround () {
        
        return onGround;
    }
    
    public boolean canSpawnHere (float x, float y) {
        
        return true;
    }
    
    public float getMotionX () {
        
        return motionX;
    }
    
    public void addMotionX (float motionX) {
        
        this.motionX += motionX;
    }
    
    public void setMotionX (float motionX) {
        
        this.motionX = motionX;
    }
    
    public float getMotionY () {
        
        return motionY;
    }
    
    public void setMotionY (float motionY) {
        
        this.motionY = motionY;
    }
    
    public void addMotionY (float motionY) {
        
        this.motionY += motionY;
    }
    
    public void setPosition (float x, float y) {
        
        this.x = x;
        this.y = y;
    }
    
    public void setX (float x) {
        
        this.x = x;
    }
    
    public float getX () {
        
        return this.x;
    }
    
    public void setY (float y) {
        
        this.y = y;
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
    
    public World getWorld () {
        
        return this.world;
    }
    
    public void setWorld (World world) {
        
        this.world = world;
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
