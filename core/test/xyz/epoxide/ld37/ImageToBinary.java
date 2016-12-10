package xyz.epoxide.ld37;

import xyz.epoxide.ld37.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ImageToBinary {

    private static HashMap<Integer, Tile> colorTile;

    public static void main(String[] args) {
        ImageToBinary.colorTile = new HashMap<Integer, Tile>();
        ImageToBinary.colorTile.put(-16777216, Tile.STONE);
        ImageToBinary.colorTile.put(-4714223, Tile.WATER);
        ImageToBinary.colorTile.put(-1, Tile.VOID);

        File backgroundImageFile = new File("background.png");
        File backgroundFile = new File("background.map");

        fileToImage(backgroundImageFile, backgroundFile);
    }

    public static void fileToImage(File imageFile, File mapFile) {
        try {
            BufferedImage img = ImageIO.read(imageFile);
            int width = img.getWidth();
            int height = img.getHeight();

            FileOutputStream fos = new FileOutputStream(mapFile);
            fos.write(toByteArray(width));
            fos.write(toByteArray(height));

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = img.getRGB(x, y);
//                    Tile tile = ImageToBinary.colorTile.get(color);
//                    if (tile == null) {
//                        byte b = (byte) (color & 255);
//                        byte g = (byte) ((color >> 8) & 255);
//                        byte r = (byte) ((color >> 16) & 255);
//                        System.err.println(String.format("Not valid color %sr %sg %sb(%s), at %s, %s", r, g, b, color, x, y));
//                        System.exit(-1);
//                    }
//                    fos.write(toByteArray(tile.id.toString().length()));
                    fos.write(toByteArray(color));
                }
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] toByteArray(int value) {
        return new byte[]{
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value};
    }

}

