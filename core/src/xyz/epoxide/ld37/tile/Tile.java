package xyz.epoxide.ld37.tile;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    public Texture texture;

    public Tile(String s) {
        this.texture = new Texture("assets/ld37/textures/" + s);
    }
}
