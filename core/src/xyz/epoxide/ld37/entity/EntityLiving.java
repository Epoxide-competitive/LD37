package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityLiving extends Entity {
    
    private float maxHealth;
    private float health;
    
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
        if (source.getSource() != null){
        	this.addMotionY(5.0f);
        	float sign = Math.signum(source.getSource().getX()-getX());
        	this.setMotionX(-6.0f*sign);
        }
    	
        this.health = Math.min(this.getHealth() - source.getAmount(), this.getMaxHealth());
        
        if (this.getHealth() < 0.25f) {
            
            this.setDead(source);
        }
    }
    
    public void heal (CombatSource source) {
        
        this.health = Math.min(this.getHealth() + source.getAmount(), this.getMaxHealth());
    }
}