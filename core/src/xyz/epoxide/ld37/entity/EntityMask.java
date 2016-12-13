package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntityMask extends EntityEnemy {
	int internalCount = 0;

    public EntityMask(World world) {
        super(world);
        this.hasGravity = false;
        this.animFrames = 1;
        this.cycleSpeed = 0.2f;
        this.setMaxHealth(4.0f);
        this.setHealth(4.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.animType = 0;
    	EntityPlayer player = LD37.INSTANCE.entityPlayer;
    	if (Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY())) < 10.0f){
    		this.animType = 1;
            
            if (this.getMotionX() >= -8.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
        			this.addMotionX(-42.0f*delta);
        		}
            }
            if (this.getMotionX() <= 8.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
        			this.addMotionX(42.0f*delta);
        		}
            }
            
            if (this.getMotionY() >= -8.0f){
            	if (LD37.INSTANCE.entityPlayer.getY() < this.getY()){
        			this.addMotionY(-42.0f*delta);
        		}
            }
            if (this.getMotionY() <= 8.0f){
            	if (LD37.INSTANCE.entityPlayer.getY() > this.getY()){
        			this.addMotionY(42.0f*delta);
        		}
            }
    	}
    }
}
