package xyz.epoxide.ld37.entity;

import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.utils.Box;
import xyz.epoxide.ld37.utils.Direction;
import xyz.epoxide.ld37.world.World;

public class Entity {
    
    public static final NamedRegistry<Class<? extends Entity>> REGISTRY = new NamedRegistry<Class<? extends Entity>>();
    
    protected float x;
    protected float y;
    protected float motionX;
    protected float motionY;
    
    protected World world;
    
    protected Direction direction;
    
    protected boolean noClip = false;
    protected boolean killed;
    protected boolean hasGravity = true;
    protected boolean onGround;
    protected int animCycle = 0;
    protected int animFrames = 0;
    protected float cycleTime = 0;
    protected float cycleSpeed = 8.0f/256.0f;
    
    public Entity(World world) {
        
        this.world = world;
        this.world.spawnEntity(this, this.getX(), this.getY());
    }
    
    public Direction getDirection(){
    	return direction;
    }
    
    public void onEntitySpawned () {
        
    }
    
    public void onEntityKilled (CombatSource source) {
        
    }
    
    public int getAnimationStage(){
    	return animCycle;
    }
    
    public void onUpdate (float delta) {
    	cycleTime += delta;
    	if (cycleTime > cycleSpeed){
    		animCycle ++;
    		cycleTime = 0;
    	}
    	if (animCycle >= animFrames){
    		animCycle = 0;
    	}
        this.onGround = false;
        this.direction = (this.getMotionX() == 0) ? (this.direction == null ? Direction.STILL : this.direction) : (this.getMotionX() > 0) ? Direction.RIGHT : Direction.LEFT;
        
        this.setX(this.getX() + this.getMotionX()*delta);
        this.setY(this.getY() + this.getMotionY()*delta);
        
        if (this.getMotionX() != 0) {
            
            if (this.getMotionX() < 0.00001 && this.getMotionX() > -0.00001) {
                
                this.setMotionX(0);
            }
            
            else {
                
                this.setMotionX(this.getMotionX() * 0.5f);
            }
        }
        if (this.hasGravity){
            this.addMotionY(-0.02f);
        }
        if (this.getY() < 0){
        	this.onGround = true;
            this.setY(0);
        }
        
        if (!this.noClip){
        	Tile[][] tiles = world.getTileMap();
        	int baseX = (int)getX();
        	int baseY = (int)getY();
        	for (int i = -3; i < 4; i ++){
        		for (int j = -3; j < 4; j ++){
        			if (baseX+i >= 0 && baseX+i < tiles.length){
        				if (baseY+j >= 0 && baseY+j < tiles[baseX+i].length){
        					Tile t = tiles[baseX+i][baseY+j];
        					if (t != Tile.VOID){
        						if (getMotionY() < 0 && this.x >= baseX+i && this.x <= baseX+i+1f && this.y >= baseY+j && this.y <= baseY+j+1f){
        							this.onGround = true;
        							setMotionY(0);
        							this.y = baseY+j+1;
        						}
        						if (getMotionY() > 0 && this.x >= baseX+i && this.x <= baseX+i+1f && this.y+2 >= baseY+j && this.y+2 <= baseY+j+1f){
        							setMotionY(0);
        							this.y = baseY+j-2;
        						}
        						if (getMotionX() < 0 && this.x-0.5f >= baseX+i && this.x-0.5f <= baseX+i+1f && this.y+0.7f >= baseY+j && this.y+0.7f <= baseY+j+1f){
        							setMotionX(0);
        							this.x = baseX+i+1.5f;
        						}
        						if (getMotionX() > 0 && this.x+0.5f >= baseX+i && this.x+0.5f <= baseX+i+1f && this.y+0.7f >= baseY+j && this.y+0.7f <= baseY+j+1f){
        							setMotionX(0);
        							this.x = baseX+i-0.5f;
        						}
        						if (getMotionX() < 0 && this.x-0.5f >= baseX+i && this.x-0.5f <= baseX+i+1f && this.y+1.3f >= baseY+j && this.y+1.3f <= baseY+j+1f){
        							setMotionX(0);
        							this.x = baseX+i+1.5f;
        						}
        						if (getMotionX() > 0 && this.x+0.5f >= baseX+i && this.x+0.5f <= baseX+i+1f && this.y+1.3f >= baseY+j && this.y+1.3f <= baseY+j+1f){
        							setMotionX(0);
        							this.x = baseX+i-0.5f;
        						}
        					}
        				}
        			}
        		}
        	}
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
    
    public void setOnGround(boolean grounded){
    	this.onGround = grounded;
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
    
    public void setHasGravity(boolean gravity){
    	this.hasGravity = gravity;
    }
    
    public boolean getHasGravity(){
    	return this.hasGravity;
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
