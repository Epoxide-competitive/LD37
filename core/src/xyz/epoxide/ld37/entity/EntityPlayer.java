package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.world.World;

public class EntityPlayer extends Entity {
    
    public EntityPlayer(World world) {
        
        super(world);
        this.setY(10);
        this.animFrames = 8;
        this.cycleSpeed = 0.0625f;
    }
}
