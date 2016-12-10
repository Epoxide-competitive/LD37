package xyz.epoxide.ld37.tile;

import xyz.epoxide.commons.collections.BiMap;
import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;

public class Tile extends Registerable<Tile> {
    
    public static BiMap<Tile, Integer> tileColorMap = new BiMap<Tile, Integer>();
    
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();
    
    public static final Tile VOID = registerTile("void", new Tile(-1));
    public static final Tile STONE_PILLAR_BASE = registerTile("stone", new Tile(-16777216).setUV(1.0f / 256.0f, 48.0f / 256.0f, 7.0f / 256.0f, 64.0f / 256.0f));
    public static final Tile WATER = registerTile("water", new Tile(-4714223));
    
    private final int color;
    
    public float u, v, u2, v2;
    
    public Tile(int color) {
        this.color = color;
        tileColorMap.put(this, color);
    }
    
    private static Tile registerTile (String id, Tile tile) {
        
        tile.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(tile);
        return tile;
    }
    
    public static Tile getTile (int color) {
        
        return tileColorMap.getInverse().get(color);
    }
    
    private Tile setUV (float u, float v, float u2, float v2) {
        
        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }

    public int getColor () {
        
        return color;
    }
}
