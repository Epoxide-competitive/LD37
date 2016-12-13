package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityFire extends EntityEnemy {
	float internalTimer = 0;
    public EntityFire(World world) {
        super(world);
        this.hasGravity = false;
        this.animFrames = 2;
        this.cycleSpeed = 0.05f;
        this.setMaxHealth(1.0f);
        this.setHealth(1.0f);
    }
    
    @Override
    public void onUpdate (float delta) {
    	internalTimer += delta;
    	super.onUpdate(delta);
    	this.animType = 0;
    	if (internalTimer > 8){
    		this.setDead(CombatSource.STATE_BASED);
    	}
    }
}
