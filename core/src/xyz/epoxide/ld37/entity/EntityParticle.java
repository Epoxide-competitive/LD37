package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.item.Item;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityParticle extends Entity {

    public EntityParticle(World world, int type) {
        super(world);
        this.noClip = true;
        this.hasGravity = false;
        switch (type){
	        case 0: {
	        	this.animType = 0;
	        	this.cycleSpeed = 0.0625f;
	        	this.animFrames = 3;
	        	break;
	        }
	        case 1: {
	        	this.animType = 1;
	        	this.cycleSpeed = 0.0625f;
	        	this.animFrames = 3;
	        	break;
	        }
        }
    }
    
    public void onUpdate(float delta) {
        super.onUpdate(delta);
        if (this.getAnimationStage() == animFrames-1){
        	setDead(CombatSource.STATE_BASED);
        }
    }
}
