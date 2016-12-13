package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityLiving extends Entity {
    
    protected float maxHealth;
    protected float health;
    public float damageTimer = 0;
    
    public EntityLiving(World world) {
        
        super(world);
    }
    
    public float getMaxHealth () {
        
        return maxHealth;
    }
    
    public void setMaxHealth (float maxHealth) {
        
        this.maxHealth = maxHealth;
    }
    
    public float getHealth () {
        
        return health;
    }
    
    public void setHealth (float health) {
        
        this.health = health;
    }
    
    public void attack (CombatSource source) {
        if (source.getSource() != null && damageTimer == 0){
        	this.addMotionY(5.0f);
        	float sign = Math.signum(source.getSource().getX()-getX());
        	this.setMotionX(-6.0f*sign);
        	this.damageTimer = 1.0f;
        }
    	
        this.health = Math.min(this.getHealth() - source.getAmount(), this.getMaxHealth());
        
        if (this.getHealth() < 0.25f) {
            
            this.setDead(source);
        }
    }
    
    public void heal (CombatSource source) {
        
        this.health = Math.min(this.getHealth() + source.getAmount(), this.getMaxHealth());
    }
    
    @Override
    public void onUpdate(float delta){
    	super.onUpdate(delta);
    	if (this.damageTimer > 0){
    		this.damageTimer -= delta;
    		if (this.damageTimer <= 0){
    			this.damageTimer = 0;
    		}
    	}
    }
}