package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.input.keybind.KeyJump;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityPlayer extends EntityLiving {
    public float deathTimer = 0;
    public int maxJumps = 1;
    public int jumps = 1;
    
    public boolean hasDoubleJump = false, hasExtraHealth = false, hasExtraDamage = false, hasEarthKey = false, hasWaterKey = false, hasFireKey = false, hasAirKey = false;
    
    public EntityPlayer(World world) {
        
        super(world);
        this.setMaxHealth(5);
        this.setHealth(5);
        this.animFrames = 8;
        this.cycleSpeed = 0.0625f;
    }
    
    @Override
    public void onUpdate (float delta) {
    	if (this.deathTimer <= 0){
    		super.onUpdate(delta);
    		if (hasExtraHealth){
    			this.setMaxHealth(8.0f);
    		}
    		else {
    			this.setMaxHealth(5.0f);
    		}
    		if (hasDoubleJump){
    			this.maxJumps = 2;
    		}
    		else {
    			this.maxJumps = 10;
    		}
    		if (this.onGround){
    			this.jumps = maxJumps;
    		}
    		if (this.x < 0){
	    		LD37.worldX --;
	    		LD37.INSTANCE.world.refresh();
	    		this.setX(LD37.INSTANCE.world.getMapWidth()-2.0f);
	    	}
	    	if (this.x > LD37.INSTANCE.world.getMapWidth()){
	    		LD37.worldX ++;
	    		LD37.INSTANCE.world.refresh();
	    		this.setX(2.0f);
	    	}
    		if (this.y < 0){
    			boolean isBottomless = false;
    			for (int i = 0; i < world.getEntities().size(); i ++){
    				if (world.getEntities().get(i) instanceof EntityPitManager){
    					isBottomless = true;
    				}
    			}
    			if (!isBottomless){
		    		LD37.worldY --;
		    		LD37.INSTANCE.world.refresh();
		    		this.setY(LD37.INSTANCE.world.getMapHeight()-2.0f);
    			}
	    	}
	    	
	    	if (animType == 2 && animCycle == 8){
	    		animType = 0;
	    		animCycle = 0;
	    		animFrames = 8;
	            this.cycleSpeed = 0.0625f;
	    	}
	    	
	    	if (animType == 3 && animCycle == 4){
	    		animType = 0;
	    		animCycle = 0;
	    		animFrames = 8;
	            this.cycleSpeed = 0.0625f;
	    	}
	
	    	if (animType == 0 && Math.abs(getMotionX()) > 0.05f){
	    		animType = 1;
	    	}
	    	if (animType == 1 && Math.abs(getMotionX()) < 0.05f){
	    		animType = 0;
	    	}
	    	
	    	if (this.getMotionY() > 0){
	        	boolean holdingJump = false;
	    		for (KeyBind key : KeyBind.REGISTRY) {
	                if (key instanceof KeyJump && key.isPressed()) {
	                    holdingJump = true;
	                }
	            }
	    		if (!holdingJump){
	    			this.addMotionY(-60.0f*delta);
	    		}
	    	}
	    	
	    	if (this.getAnimationType() == 3 && this.onGround){
	    		this.setMotionX(0);
	    	}
    	}
    	else {
    		this.deathTimer -= delta;
    		if (this.deathTimer <= 0){
    			this.deathTimer = 0;
    			this.setHealth(getMaxHealth());
    			LD37.worldX = 11;
    			LD37.worldY = 0;
    			world.refresh();
    			this.x = 24;
    			this.y = 16;
    		}
    	}
    }
    
    @Override
    public void attack (CombatSource source) {
        if (source.getSource() != null && damageTimer == 0 && deathTimer <= 0){
        	this.addMotionY(5.0f);
        	float sign = Math.signum(source.getSource().getX()-getX());
        	this.setMotionX(-6.0f*sign);
        	this.damageTimer = 1.0f;
        }
    	
        this.health = Math.min(this.getHealth() - source.getAmount(), this.getMaxHealth());
        
        if (this.getHealth() < 0.25f && this.deathTimer <= 0) {
        	this.deathTimer = 5.0f;
        }
    }
}
