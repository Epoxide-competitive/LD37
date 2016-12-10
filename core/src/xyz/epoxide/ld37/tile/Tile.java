package xyz.epoxide.ld37.tile;

import xyz.epoxide.commons.collections.BiMap;
import xyz.epoxide.commons.registry.Identifier;

public class Tile {
    public static BiMap<Tile, Integer> tileColorMap = new BiMap<Tile, Integer>();
    public static final Tile VOID = new Tile(new Identifier("ld37:void"), -1);
    public static final Tile STONE = new Tile(new Identifier("ld37:stone"), -16777216);
    public static final Tile WATER = new Tile(new Identifier("ld37:water"), -4714223);

    public final Identifier id;
    private final int color;

    public Tile(Identifier id, int color) {
        this.id = id;
        this.color = color;
        this.tileColorMap.put(this, color);
    }

    public static Tile getTile(int color) {
        return tileColorMap.getInverse().get(color);
    }
}
