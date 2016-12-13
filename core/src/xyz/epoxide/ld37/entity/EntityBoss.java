package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntityBoss extends EntityEnemy {
	int internalCount = 0;

    public EntityBoss(World world) {
        super(world);
        this.animFrames = 4;
        this.cycleSpeed = 0.125f;
        this.setMaxHealth(72.0f);
        this.setHealth(72.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	//if (Math.abs(LD37.INSTANCE.entityPlayer.getX()-this.getX()) < (3.0f*Gdx.graphics.getWidth())/(4.0f*LD37.tileWidth)){
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
    	//}
    	
    	if (this.getAnimationStage() == 3 && this.getPrevAnimationStage() == 2 && LD37.INSTANCE.entityPlayer.getY() > getY()){
    		if (random.nextInt(3) == 0){
    			this.addMotionY(15.0f);
    		}
    		if (random.nextInt(4) == 0){
    			world.spawnEntity(new EntityLightning(world), getX(), getY());
    		}
    	}
    }
}
