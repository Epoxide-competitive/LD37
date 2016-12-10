package xyz.epoxide.ld37.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.files.FileHandle;

import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.tile.Tile;

public class World {
    
    private Tile[][] backgroundTileMap;
    private Tile[][] foregroundTileMap;
    private List<Entity> entityList = new ArrayList<Entity>();
    
    public World(FileHandle backgroundFile, FileHandle foregroundFile) {
        this.backgroundTileMap = loadFile(backgroundFile);
        this.foregroundTileMap = loadFile(foregroundFile);
    }
    
    private Tile[][] loadFile (FileHandle f) {
        
        byte[] background = f.readBytes();
        int width = fromByteArray(background, 0);
        int height = fromByteArray(background, 4);
        
        Tile[][] tilemap = new Tile[width][height];
        int read = 8;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = fromByteArray(background, read);
                Tile t = Tile.getTile(color);
                tilemap[x][y] = t;
                read += 4;
            }
        }
        return tilemap;
    }
    
    public Tile[][] getBackgroundTileMap () {
        
        return backgroundTileMap;
    }
    
    public Tile[][] getForegroundTileMap () {
        
        return foregroundTileMap;
    }
    
    public void onUpdate () {
        
        for (Iterator<Entity> iterator = this.entityList.iterator(); iterator.hasNext();) {
            
            Entity entity = iterator.next();
            
            if (entity.isDead()) {
                
                entity.onEntityKilled();
                iterator.remove();
            }
            
            else {
                
                entity.onUpdate();
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
}
