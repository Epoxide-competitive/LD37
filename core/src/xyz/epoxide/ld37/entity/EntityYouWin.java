package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.world.World;

public class EntityYouWin extends Entity {
	public int worldX = 0, worldY = 0;
	public float alpha = 0;
    
    public EntityYouWin(World world) {
        
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
    	alpha = Math.min(4.0f, alpha+delta);
    }
}
