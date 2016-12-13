package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntitySlime extends EntityEnemy {
	int internalCount = 0;
	float internalTimer = 0;

    public EntitySlime(World world) {
        super(world);
        this.animFrames = 2;
        this.cycleSpeed = 0.25f;
        this.setMaxHealth(6.0f);
        this.setHealth(6.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.internalTimer += delta;
    	this.animType = 0;
    	EntityPlayer player = LD37.INSTANCE.entityPlayer;
    	if (this.onGround){
    		this.setMotionX(0);
    	}
    	if (Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY())) < 16.0f){
    		this.animType = 1;
            
            if (this.getMotionX() >= -8.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
        			this.addMotionX(-50.0f*delta);
        		}
            }
            if (this.getMotionX() <= 8.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
        			this.addMotionX(50.0f*delta);
        		}
            }
            if (this.internalTimer > 3){
            	this.internalTimer = 0;
            	this.addMotionY(25.0f);
            }
    	}
    }
}
