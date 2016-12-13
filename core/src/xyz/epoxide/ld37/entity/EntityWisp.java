package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntityWisp extends EntityEnemy {
	float internalTimer = 0;
    public EntityWisp(World world) {
        super(world);
        this.hasGravity = false;
        this.animFrames = 2;
        this.cycleSpeed = 0.05f;
        this.setMaxHealth(6.0f);
        this.setHealth(6.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	internalTimer += delta;
    	super.onUpdate(delta);
    	this.animType = 0;
    	EntityPlayer player = LD37.INSTANCE.entityPlayer;
    	if (Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY())) < 10.0f){
    		
            if (this.getMotionX() >= -10.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
        			this.addMotionX(-42.0f*delta);
        		}
            }
            if (this.getMotionX() <= 10.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
        			this.addMotionX(42.0f*delta);
        		}
            }
            
            if (this.getMotionY() >= -10.0f){
            	if (LD37.INSTANCE.entityPlayer.getY()+1.0f < this.getY()){
        			this.addMotionY(-2.0f*delta);
        		}
            }
            if (this.getMotionY() <= 10.0f){
            	if (LD37.INSTANCE.entityPlayer.getY()+1.0f > this.getY()){
        			this.addMotionY(2.0f*delta);
        		}
            }
    	}
    	if (internalTimer > 2){
    		internalTimer = 0;
    		world.spawnEntity(new EntityFire(world), getX(), getY());
    	}
    }
}
