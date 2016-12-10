package xyz.epoxide.ld37.world;

import com.badlogic.gdx.files.FileHandle;
import xyz.epoxide.ld37.tile.Tile;

import java.lang.reflect.Array;

public class World {

    private Tile[][] backgroundTileMap;
    private Tile[][] foregroundTileMap;

    public World(FileHandle backgroundFile, FileHandle foregroundFile) {
        byte[] background = backgroundFile.readBytes();
        int width = 0;
        try {
            width = fromByteArray(background, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int height = 0;
        try {
            height = fromByteArray(background, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int read = 8;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int strLength = 0;
                try {
                    strLength = fromByteArray(background, read);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                read+=4;
                String s = new String(copyOfRange(background, read, read + strLength));
                read += strLength;
                s = null;
            }
        }
    }

    public Tile[][] getBackgroundTileMap() {
        return backgroundTileMap;
    }

    public Tile[][] getForegroundTileMap() {
        return foregroundTileMap;
    }

    int fromByteArray(byte[] bytes, int start) throws Exception{
        return bytes[start] << 24 | (bytes[start + 1] & 0xFF) << 16 | (bytes[start + 2] & 0xFF) << 8 | (bytes[start + 3] & 0xFF);
    }

    public static byte[] copyOfRange(byte[] original, int from, int to) {
        Class<? extends byte[]> newType = original.getClass();
        int newLength = to - from;
        if (newLength < 0) {
            throw new IllegalArgumentException(from + " > " + to);
        }
        byte[] copy = (newType == byte[].class) ? new byte[newLength] : (byte[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }
}
