package xyz.epoxide.ld37.entity;

import java.util.List;

import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityItem extends Entity {

    public final int item;
    
    public EntityItem(World world) {
        
        super(world);
        this.item = 0;
    }
    
    public EntityItem(World world, int item) {
        
        super(world);
        this.item = item;
    }
    
    @Override
    public void onUpdate(float delta){
    	super.onUpdate(delta);
    	List<Entity> entities = world.getEntities();
    	for (int i = 0; i < entities.size(); i ++){
    		Entity e = entities.get(i);
    		if (e instanceof EntityPlayer){
    			if (e.getX() >= getX()-1.0f && e.getX() <= getX()+1.0f && e.getY() >= this.y && e.getY() <= y+2.0f){
    		    	if (item == 0){
	    				((EntityPlayer)e).hasEarthKey = true;
	    			}
	    			if (item == 1){
	    				((EntityPlayer)e).hasWaterKey = true;
	    			}
	    			if (item == 2){
	    				((EntityPlayer)e).hasAirKey = true;
	    			}
	    			if (item == 3){
	    				((EntityPlayer)e).hasFireKey = true;
	    			}
	    			if (item == 4){
	    				((EntityPlayer)e).hasDoubleJump = true;
	    			}
	    			if (item == 5){
	    				((EntityPlayer)e).hasExtraDamage = true;
	    			}
	    			if (item == 6){
	    				((EntityPlayer)e).hasExtraHealth = true;
	    				((EntityPlayer)e).setMaxHealth(8.0f);
	    				((EntityPlayer)e).setHealth(8.0f);
	    			}
	    			this.onEntityKilled(CombatSource.STATE_BASED);
	    			this.setDead(CombatSource.STATE_BASED);
    			}
    		}
    	}
    }
}
