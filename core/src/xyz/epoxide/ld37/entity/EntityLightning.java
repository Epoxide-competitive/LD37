package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityLightning extends EntityEnemy {
	float internalTimer = 0;
    public EntityLightning(World world) {
        super(world);
        this.hasGravity = false;
        this.noClip = true;
        this.animFrames = 4;
        this.cycleSpeed = 0.0625f;
        this.setMaxHealth(1.0f);
        this.setHealth(1.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	internalTimer += delta;
    	super.onUpdate(delta);
    	if (this.getMotionX() >= -12.0f){
        	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
    			this.addMotionX(-55.0f*delta);
    		}
        }
        if (this.getMotionX() <= 12.0f){
        	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
    			this.addMotionX(55.0f*delta);
    		}
        }
        
        if (this.getMotionY() >= -12.0f){
        	if (LD37.INSTANCE.entityPlayer.getY() < this.getY()){
    			this.addMotionY(-15.0f*delta);
    		}
        }
        if (this.getMotionY() <= 12.0f){
        	if (LD37.INSTANCE.entityPlayer.getY() > this.getY()){
    			this.addMotionY(15.0f*delta);
    		}
        }
    	this.animType = 0;
    	if (internalTimer > 1.5){
    		this.setDead(CombatSource.STATE_BASED);
    	}
    }
}
