package xyz.epoxide.ld37.util;

import xyz.epoxide.ld37.entity.Entity;

public class CombatSource {
    
    public static final CombatSource STATE_BASED = new CombatSource(-1, Type.STATE);
    
    private final float amount;
    private final Entity source;
    private final Type type;
    
    public CombatSource(float amount, Type type) {
        
        this(amount, type, null);
    }
    
    public CombatSource(float amount, Type type, Entity source) {
        
        this.amount = amount;
        this.source = source;
        this.type = type;
    }
    
    public float getAmount () {
        
        return amount;
    }
    
    public Entity getSource () {
        
        return source;
    }
    
    public Type getType () {
        
        return type;
    }
    
    public static enum Type {
        
        STATE("state"),
        GENERIC("generic");
        
        final String name;
        
        Type(String name) {
            
            this.name = name;
        }
        
        public CombatSource createSource (float amount, Entity source) {
            
            return new CombatSource(amount, this, source);
        }
    }
}
