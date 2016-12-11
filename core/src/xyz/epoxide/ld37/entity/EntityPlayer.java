package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.input.keybind.KeyJump;
import xyz.epoxide.ld37.world.World;

public class EntityPlayer extends EntityLiving {
    
    public EntityPlayer(World world) {
        
        super(world);
        this.setMaxHealth(5);
        this.setHealth(5);
        this.animFrames = 8;
        this.cycleSpeed = 0.0625f;
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	if (this.x+1.0f < 0){
    		LD37.worldX --;
    		LD37.INSTANCE.world.refresh();
    		this.setX(LD37.INSTANCE.world.getMapWidth()-2.0f);
    	}
    	if (this.x-1.0f > LD37.INSTANCE.world.getMapWidth()){
    		LD37.worldX ++;
    		LD37.INSTANCE.world.refresh();
    		this.setX(2.0f);
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
}
