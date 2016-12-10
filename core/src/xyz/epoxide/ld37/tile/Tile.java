package xyz.epoxide.ld37.tile;

import xyz.epoxide.commons.collections.BiMap;
import xyz.epoxide.commons.registry.Identifier;

public class Tile {
    public static BiMap<Tile, Integer> tileColorMap = new BiMap<Tile, Integer>();

    public static final Tile VOID = new Tile(new Identifier("ld37:void"), -1);
    public static final Tile STONE_PILLAR_BASE = new Tile(new Identifier("ld37:stone"), -16777216).setUV(1.0f / 256.0f, 48.0f / 256.0f, 7.0f / 256.0f, 64.0f / 256.0f);
    public static final Tile WATER = new Tile(new Identifier("ld37:water"), -4714223);

    public final Identifier id;
    private final int color;

    public float u, v, u2, v2;

    public Tile(Identifier id, int color) {
        this.id = id;
        this.color = color;
        tileColorMap.put(this, color);
    }

    public static Tile getTile(int color) {
        return tileColorMap.getInverse().get(color);
    }

    private Tile setUV(float u, float v, float u2, float v2) {
        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }
}
