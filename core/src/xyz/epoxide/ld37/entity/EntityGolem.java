package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntityGolem extends EntityEnemy {
	int internalCount = 0;

    public EntityGolem(World world) {
        super(world);
        this.animFrames = 4;
        this.cycleSpeed = 0.2f;
        this.setMaxHealth(16.0f);
        this.setHealth(16.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.animType = 0;
    	EntityPlayer player = LD37.INSTANCE.entityPlayer;
    	if (Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY())) < 8.0f){
    		this.animType = 1;
            
            if (this.getMotionX() >= -4.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
        			this.addMotionX(-50.0f*delta);
        		}
            }
            if (this.getMotionX() <= 4.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
        			this.addMotionX(50.0f*delta);
        		}
            }
    	}
    }
}
