package xyz.epoxide.ld37.entity;

import java.util.List;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.util.CombatSource.Type;
import xyz.epoxide.ld37.world.World;

public class EntitySword extends Entity {
    public EntityPlayer player = null;
    private boolean damageDealt = false;
    private int internalCount = 0;
    public EntitySword(World world, EntityPlayer player) {
        
        super(world);
        this.player = player;
        this.noClip = true;
        this.hasGravity = false;
        this.animFrames = 9;
        this.cycleSpeed = 0.025f;
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.setX(player.x);
    	this.setY(player.y);
    	float width = player.direction.x*4.0f;
    	if (this.getAnimationStage() == 8){
    		this.setDead(CombatSource.STATE_BASED);
    	}
    	if (this.getAnimationStage() >= 2 && this.getAnimationStage() <= 6){
	    	List<Entity> entities = world.getEntities();
	    	for (int i = 0; i < entities.size(); i ++){
	    		Entity e = entities.get(i);
	    		if (e instanceof EntityLiving && !damageDealt && !(e instanceof EntityPlayer)){
		    		if (e.getX() >= Math.min(x, x+width) && e.getX() <= Math.max(x, x+width) && e.getY() >= this.y && e.getY() <= y+2.0f){
		    			((EntityLiving) e).attack(new CombatSource(1.0f,Type.GENERIC,this.player));
		    			damageDealt = true;
		    			for (int j = 0; j < 1; j ++){
		    				world.addParticle(new EntityParticle(world,0), e.getX()+(random.nextFloat()*0.5f-0.25f), e.getY()+(random.nextFloat()*0.5f-0.25f));
		    			}
		    		}
	    		}
	    	}
    	}
    }
}
