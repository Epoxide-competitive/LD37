package xyz.epoxide.ld37.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.epoxide.ld37.tile.Tile;

import java.io.File;

public class World {

    private Tile[][] backgroundTileMap;
    private Tile[][] foregroundTileMap;

    public World(){

    }

    public Tile[][] getBackgroundTileMap() {
        return backgroundTileMap;
    }

    public Tile[][] getForegroundTileMap() {
        return foregroundTileMap;
    }
}
