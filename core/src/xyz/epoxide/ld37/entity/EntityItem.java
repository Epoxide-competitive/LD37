package xyz.epoxide.ld37.entity;

import xyz.epoxide.ld37.item.Item;
import xyz.epoxide.ld37.world.World;

public class EntityItem extends Entity {

    private final Item item;
    
    public EntityItem(World world) {
        
        super(world);
        this.item = null;
    }
    
    public EntityItem(World world, Item item) {
        
        super(world);
        this.item = item;
    }
    
    public Item getItem() {
        
        return this.item;
    }
}
