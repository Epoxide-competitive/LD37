package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.world.World;

public class EntityPitManager extends Entity {
	public int worldX = 0, worldY = 0;
    
    public EntityPitManager(World world) {
        
        super(world);
        this.hasGravity = false;
        this.noClip = true;
        this.canRender = false;
        this.worldX = LD37.worldX;
        this.worldY = LD37.worldY;
    }
    
    @Override
    public void onUpdate(float delta){
    	super.onUpdate(delta);
    	if (LD37.worldX == worldX && LD37.worldY == worldY){
	    	for (int i = 0; i < world.getEntities().size(); i ++){
	    		if (world.getEntities().get(i) instanceof EntityLiving){
	    			if (((EntityLiving)world.getEntities().get(i)).getY() < this.getY()-2.0f){
	    				((EntityLiving)world.getEntities().get(i)).attack(new CombatSource(999.0f,CombatSource.Type.GENERIC));
	    			}
	    		}
	    	}
    	}
    }
}
