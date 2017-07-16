package xyz.epoxide.ld37.item;

import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.commons.registry.Registerable;

import xyz.epoxide.ld37.LD37;

public class Item  extends Registerable<Item> {
    
    public static final NamedRegistry<Item> REGISTRY = new NamedRegistry<Item>();
    
    private static Item registerItem (String id, Item item) {
        
        item.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(item);
        return item;
    }
}