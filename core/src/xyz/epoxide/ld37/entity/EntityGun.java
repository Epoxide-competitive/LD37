package xyz.epoxide.ld37.entity;

import java.util.List;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.util.CombatSource.Type;
import xyz.epoxide.ld37.world.World;

public class EntityGun extends Entity {
    public EntityPlayer player = null;
    private boolean damageDealt = false;
    private int internalCount = 0;
    public EntityGun(World world, EntityPlayer player) {
        
        super(world);
        this.player = player;
        this.noClip = true;
        this.hasGravity = false;
        this.animFrames = 5;
        this.cycleSpeed = 0.025f;
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.setX(player.x);
    	this.setY(player.y);
    	float width = player.direction.x*4.0f;
    	if (this.getAnimationStage() == 4){
    		this.setDead(CombatSource.STATE_BASED);
    	}
    }
}
