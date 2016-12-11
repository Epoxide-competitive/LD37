package xyz.epoxide.ld37.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.utils.Box;
import xyz.epoxide.ld37.utils.Direction;

public class World {
    
    private final Tile[][] backgroundTileMap;
    private final Tile[][] tileMap;
    private final Tile[][] foregroundTileMap;
    private final List<Entity> entityList = new ArrayList<Entity>();
    
    private final int backgroundWidth;
    private final int backgroundHeight;
    
    private final int worldWidth;
    private final int worldHeight;
    
    private final int foregroundWidth;
    private final int foregroundHeight;
    
    public World(FileHandle backgroundFile, FileHandle worldFile, FileHandle foregroundFile) {
        
        this.backgroundTileMap = loadFile(backgroundFile);
        this.backgroundWidth = this.backgroundTileMap.length;
        this.backgroundHeight = backgroundWidth > 0 ? this.backgroundTileMap[0].length : 0;
        
        this.tileMap = loadFile(worldFile);
        this.worldWidth = this.tileMap.length;
        this.worldHeight = worldWidth > 0 ? this.tileMap[0].length : 0;
        
        this.foregroundTileMap = loadFile(foregroundFile);
        this.foregroundWidth = this.foregroundTileMap.length;
        this.foregroundHeight = foregroundWidth > 0 ? this.foregroundTileMap[0].length : 0;
    }
    
    private Tile[][] loadFile (FileHandle f) {
        
        byte[] background = f.readBytes();
        Texture map = new Texture(f.toString());
        int width = map.getWidth();
        int height = map.getHeight();
        
        Tile[][] tilemap = new Tile[width][height];
        TextureData data = map.getTextureData();
        data.prepare();
        Pixmap pixels = data.consumePixmap();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = pixels.getPixel(x, (height-y)-1);
                Tile t = Tile.getTile(color);
                if (t == null){
                	t = Tile.VOID;
                }
                tilemap[x][y] = t;
            }
        }
        return tilemap;
    }
    
    public Tile[][] getBackgroundTileMap () {
        
        return backgroundTileMap;
    }
    
    public Tile[][] getTileMap () {
        
        return tileMap;
    }
    
    public Tile[][] getForegroundTileMap () {
        
        return foregroundTileMap;
    }
    
    public List<Entity> getEntityList () {
        
        return entityList;
    }
    
    public int getBackgroundWidth () {
        
        return backgroundWidth;
    }
    
    public int getBackgroundHeight () {
        
        return backgroundHeight;
    }
    
    public int getForegroundWidth () {
        
        return foregroundWidth;
    }
    
    public int getForegroundHeight () {
        
        return foregroundHeight;
    }
    
    public int getMapWidth () {
        
        return worldWidth;
    }
    
    public int getMapHeight () {
        
        return worldHeight;
    }
    
    public void onUpdate (float delta) {
        
        for (Iterator<Entity> iterator = this.entityList.iterator(); iterator.hasNext();) {
            
            Entity entity = iterator.next();
            
            if (entity.isDead()) {
                
                entity.onEntityKilled(CombatSource.STATE_BASED);
                iterator.remove();
            }
            
            else {
                
                entity.onUpdate(delta);
            }
        }
    }
    
    public List<Entity> getEntities () {
        
        return this.entityList;
    }
    
    public boolean spawnEntity (Entity entity, float x, float y) {
        
        if (entity.canSpawnHere(x, y)) {
            
            entity.setPosition(x, y);
            entityList.add(entity);
            entity.onEntitySpawned();
            return true;
        }
        
        return false;
    }
    
    private int fromByteArray (byte[] bytes, int start) {
        
        return bytes[start] << 24 | (bytes[start + 1] & 0xFF) << 16 | (bytes[start + 2] & 0xFF) << 8 | (bytes[start + 3] & 0xFF);
    }
    
    public Tile getTileInDirection(Entity entity, Direction dir) {
        
        return getTileInDirection(entity.getX(), entity.getY(), dir);
    }
    
    public Tile getTileInDirection(float x, float y, Direction dir) {
        
        if (inBounds(x, y, false, dir) && x + dir.x >= 0 && y + dir.y >= 0) {
            
            return backgroundTileMap[(int) (Math.round(x) + dir.x)][(int) (Math.ceil(y) + dir.y)];
        }
        
        return Tile.VOID;
    }
    
    public boolean inBounds (float x, float y, boolean useForeground, Direction dir) {
        
        return inBounds(x, y, useForeground) && inBounds(x + dir.x, y + dir.y, useForeground);
    }
    
    public boolean inBounds (float x, float y, boolean useForeground) {
        
        return x >= 0 && y >= 0 && (useForeground ? (x < this.foregroundWidth && y < this.foregroundHeight) : (x < this.backgroundWidth && y < this.backgroundHeight));
    }
}
