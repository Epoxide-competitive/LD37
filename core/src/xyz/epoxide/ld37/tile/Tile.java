package xyz.epoxide.ld37.tile;

import xyz.epoxide.commons.collections.BiMap;
import xyz.epoxide.commons.registry.IRegisterable.Registerable;
import xyz.epoxide.commons.registry.Identifier;
import xyz.epoxide.commons.registry.NamedRegistry;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.utils.Box;
import xyz.epoxide.ld37.world.World;

public class Tile extends Registerable<Tile> {
    
    public static BiMap<Tile, Integer> tileColorMap = new BiMap<Tile, Integer>();
    
    public static final NamedRegistry<Tile> REGISTRY = new NamedRegistry<Tile>();
    
    public static final Tile VOID = registerTile("void", new Tile(-1));
    public static final Tile SPAWN_SPROUT = registerTile("sprout", new TileSpawn(0x000010FF,0));
    public static final Tile SPAWN_RADISH = registerTile("radish", new TileSpawn(0x000020FF,1));
    public static final Tile SPAWN_PITMAN = registerTile("pitman", new TileSpawn(0x000030FF,2));
    
    public static final Tile EARTH_GATE = registerTile("earthgate", (new TileGate(0x000040FF,0)).setUV(128.0f/256.0f, 0, 136.0f/256.0f, 8.0f/256.0f));
    public static final Tile WATER_GATE = registerTile("watergate", (new TileGate(0x000050FF,1)).setUV(136.0f/256.0f, 0, 144.0f/256.0f, 8.0f/256.0f));
    public static final Tile AIR_GATE = registerTile("airgate", (new TileGate(0x000060FF,2)).setUV(136.0f/256.0f, 8.0f/256.0f, 144.0f/256.0f, 16.0f/256.0f));
    public static final Tile FIRE_GATE = registerTile("firegate", (new TileGate(0x000070FF,3)).setUV(128.0f/256.0f, 8.0f/256.0f, 136.0f/256.0f, 16.0f/256.0f));

    public static final Tile SPAWN_EARTHKEY = registerTile("earthkey", new TileSpawn(0x000080FF,3));
    public static final Tile SPAWN_WATERKEY = registerTile("waterkey", new TileSpawn(0x000090FF,4));
    public static final Tile SPAWN_SLIME = registerTile("slime", new TileSpawn(0x0000A0FF,7));
    public static final Tile SPAWN_DOUBLEJUMP = registerTile("doublejump", new TileSpawn(0x0000B0FF,8));
    public static final Tile SPAWN_EXTRADAMAGE = registerTile("extradamage", new TileSpawn(0x0000C0FF,9));
    public static final Tile SPAWN_EXTRAHEALTH = registerTile("extrahealth", new TileSpawn(0x0000D0FF,10));
    public static final Tile SPAWN_AIRKEY = registerTile("airkey", new TileSpawn(0x0000E0FF,5));
    public static final Tile SPAWN_FIREKEY = registerTile("firekey", new TileSpawn(0x0000F0FF,6));
    public static final Tile SPAWN_MASK = registerTile("mask", new TileSpawn(0x2000FFFF,11));
    public static final Tile SPAWN_GOLEM = registerTile("golem", new TileSpawn(0x4000FFFF,12));
    public static final Tile SPAWN_WISP = registerTile("wisp", new TileSpawn(0x6000FFFF,13));
    public static final Tile SPAWN_BOSS = registerTile("boss", new TileSpawn(0x8000FFFF,14));
    
    public static final Box DEFAULT_BOUNDS = new Box(0,0,1,1);
    
    public static void init(){
    	for (int i = 0; i < 128; i += 8){
    		for (int j = 0; j < 256; j += 8){
    			registerTile(""+i+"_"+j,new Tile((i << 24) + (j << 16) + (0 << 8) + 0xFF).setUV(i/256.0f, j/256.0f, (i+8.0f)/256.0f, (j+8.0f)/256.0f));
    		}
    	}
    }
    // public static final Tile WATER = registerTile("water", new Tile(-4714223));
    
    private final int color;
    
    public float u, v, u2, v2;
    
    public Tile(int color) {
        this.color = color;
        tileColorMap.put(this, color);
    }
    
    public void onLoad(World world, int x, int y){
    	if (this.u < 0.5f && (Math.round(u*256.0f)-8) % 16 == 0 && (Math.round(v*256.0f)-8) % 16 == 0
    || this.u == (72.0f/256.0f) && this.v == 136.0f/256.0f){
	    	int color = tileColorMap.get(this);
	    	int r = color >> 24;
	    	int g = (color - (r << 24)) >> 16;
	    	int b = (color - ((r << 24)+(g << 16))) >> 8;
	    	int a = (color - ((r << 24)+(g << 16)+(b << 8)));
	    	if (x-1 > 0){
	    		if (world.getForegroundTileMap()[x-1][y] == Tile.VOID && tileColorMap.get(world.getTileMap()[x-1][y]) < color){
	    			boolean doEnd = false;
	    			if (y-1 > 0){
	    				if (world.getTileMap()[x-1][y-1].getIdentifier().getName().equals(getIdentifier().getName())){
	    					world.getForegroundTileMap()[x-1][y] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+8) << 16) + (b << 8) + a);
	    					doEnd = true;
	    				}
	    			}
	    			if (y+1 < world.getForegroundHeight() && !doEnd){
	    				if (world.getTileMap()[x-1][y+1].getIdentifier().getName().equals(getIdentifier().getName())){
	    					world.getForegroundTileMap()[x-1][y] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+16) << 16) + (b << 8) + a);
	    					doEnd = true;
			    		}
	    			}
	    			if (!doEnd){
	    				world.getForegroundTileMap()[x-1][y] = tileColorMap.getInverse().get(((r + 8) << 24) + (g << 16) + (b << 8) + a);
	    			}
	    		}
	    	}
	    	if (x+1 < world.getForegroundWidth()){
	    		if (world.getForegroundTileMap()[x+1][y] == Tile.VOID && tileColorMap.get(world.getTileMap()[x+1][y]) < color){
	    			boolean doEnd = false;
	    			if (y-1 > 0){
	    				if (world.getTileMap()[x+1][y-1].getIdentifier().getName().equals(getIdentifier().getName())){
	    					world.getForegroundTileMap()[x+1][y] = tileColorMap.getInverse().get(((r + 8) << 24) + ((g+8) << 16) + (b << 8) + a);
	    					doEnd = true;
	    				}
	    			}
	    			if (y+1 < world.getForegroundHeight() && !doEnd){
	    				if (world.getTileMap()[x+1][y+1].getIdentifier().getName().equals(getIdentifier().getName())){
	    					world.getForegroundTileMap()[x+1][y] = tileColorMap.getInverse().get(((r + 8) << 24) + ((g+16) << 16) + (b << 8) + a);
	    					doEnd = true;
	    				}
	    			}
	    			if (!doEnd){
	    				world.getForegroundTileMap()[x+1][y] = tileColorMap.getInverse().get(((r - 8) << 24) + (g << 16) + (b << 8) + a);
	    			}
	    		}
	    	}
	    	if (y+1 < world.getForegroundHeight()){
	    		if (world.getForegroundTileMap()[x][y+1] == Tile.VOID && tileColorMap.get(world.getTileMap()[x][y+1]) < color){
	    			boolean doEnd = false;
	    			if (x-1 > 0){
	    				if (world.getTileMap()[x-1][y+1] == this){
	    					world.getForegroundTileMap()[x][y+1] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+16) << 16) + (b << 8) + a);
	    					doEnd = true;
	    		    	}
	    			}
	    			if (x+1 < world.getForegroundWidth() && !doEnd){
	    				if (world.getTileMap()[x+1][y+1] == this){
	    					world.getForegroundTileMap()[x][y+1] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+8) << 16) + (b << 8) + a);
	    					doEnd = true;
	    		    	}
	    			}
	    			if (!doEnd) {
	    				world.getForegroundTileMap()[x][y+1] = tileColorMap.getInverse().get(((r) << 24) + ((g-8) << 16) + (b << 8) + a);
	    		    }
	    		}
	    	}
	    	if (y-1 > 0){
	    		if (world.getForegroundTileMap()[x][y-1] == Tile.VOID && tileColorMap.get(world.getTileMap()[x][y-1]) < color){
	    			boolean doEnd = false;
	    			if (x+1 < world.getForegroundWidth()){
	    				if (world.getTileMap()[x+1][y-1] == this){
	    					world.getForegroundTileMap()[x][y-1] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+16) << 16) + (b << 8) + a);
	    					doEnd = true;
	    		    	}
	    			}
	    			if (x-1 > 0 && !doEnd){
	    				if (world.getTileMap()[x-1][y-1] == this){
	    					world.getForegroundTileMap()[x][y-1] = tileColorMap.getInverse().get(((r + 16) << 24) + ((g+8) << 16) + (b << 8) + a);
	    					doEnd = true;
	    		    	}
	    			}
	    			if (!doEnd) {
	    				world.getForegroundTileMap()[x][y-1] = tileColorMap.getInverse().get(((r) << 24) + ((g+8) << 16) + (b << 8) + a);
	    		    }
	    		}
	    	}
    	}
    }
    
    private static Tile registerTile (String id, Tile tile) {
        
        tile.setIdentifier(new Identifier(LD37.ID, id));
        REGISTRY.registerValue(tile);
        return tile;
    }
    
    public static Tile getTile (int color) {
        
        return tileColorMap.getInverse().get(color);
    }
    
    public Box getBox(){
    	return DEFAULT_BOUNDS; 
    }
    
    protected Tile setUV (float u, float v, float u2, float v2) {
        
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
