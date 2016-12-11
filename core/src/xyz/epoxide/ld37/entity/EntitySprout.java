package xyz.epoxide.ld37.entity;

import com.badlogic.gdx.Gdx;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.input.keybind.KeyJump;
import xyz.epoxide.ld37.world.World;

public class EntitySprout extends EntityLiving {
	int internalCount = 0;

    public EntitySprout(World world) {
        super(world);
        this.animFrames = 4;
        this.cycleSpeed = 0.125f;
        this.setMaxHealth(2.0f);
        this.setHealth(2.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.animType = 0;
    	if (Math.abs(LD37.INSTANCE.entityPlayer.getX()-this.getX()) < (3.0f*Gdx.graphics.getWidth())/(4.0f*LD37.tileWidth)){
    		this.animType = 1;
            
            if (this.getMotionX() >= -6.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() < this.getX()){
        			this.addMotionX(-45.0f*delta);
        		}
            }
            if (this.getMotionX() <= 6.0f){
            	if (LD37.INSTANCE.entityPlayer.getX() > this.getX()){
        			this.addMotionX(45.0f*delta);
        		}
            }
    	}
    	
    	if (this.getAnimationStage() == 3 && this.getPrevAnimationStage() == 2){
    		if (random.nextInt(4) == 0 && this.onGround){
    			this.addMotionY(15.0f);
    		}
    	}
    }
}
