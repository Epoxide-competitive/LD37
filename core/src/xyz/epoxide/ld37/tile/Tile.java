package xyz.epoxide.ld37.tile;

import xyz.epoxide.commons.registry.Identifier;

public class Tile {
    public static final Tile VOID = new Tile(new Identifier("ld37:void"));
    public static final Tile STONE = new Tile(new Identifier("ld37:stone"));
    public static final Tile WATER = new Tile(new Identifier("ld37:water"));

    public final Identifier id;

    public Tile(Identifier id) {
        this.id = id;
    }
}
